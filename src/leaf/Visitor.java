package leaf;

import java.util.ArrayList;
import java.util.List;

import leaf.language_leaf.*;
import leaf.structure.*;

@SuppressWarnings("unchecked")
public class Visitor extends Walker {
	private Object register;
	
	public Visitor() {
		this.register = null;
	}
	
	private Object get() {
		Object register = this.register;
		this.register = null;
		return register;
	}
	
	private Object get(Node node) {
		this.visit(node);
		return this.get();
	}
	
	public Program program() {
		return (Program) this.register;
	}
	
	public void visit(Node tree) {
		tree.apply(this);
	}
	
	@Override
	public void caseIdentifier(NIdentifier node) {
		this.register = node.getText();
	}
	
	@Override
	public void caseFile(NFile node) {
		this.register = new ArrayList<Statement>();
		this.register = new Program((List<Statement>) this.get(node.get_Statements()));
	}
	
	@Override
	public void caseStatement_Expression(NStatement_Expression node) {
		List<Statement> statements = (List<Statement>) this.get();
		statements.add(new Statement((Expression) this.get(node.get_Expression())));
		this.register = statements;
	}
	
	@Override
	public void caseStructure_If(NStructure_If node) {
		this.register = new If(
			(Expression) this.get(node.get_Condition()),
			(Expression) this.get(node.get_Block()),
			(Expression) this.get(node.get_ElseOption())
		);
	}
	
	@Override
	public void caseStructure_Loop(NStructure_Loop node) {
		this.register = new Loop((Expression) this.get(node.get_Block()));
	}
	
	@Override
	public void caseStructure_While(NStructure_While node) {
		this.register = new While((Expression) this.get(node.get_Condition()), (Expression) this.get(node.get_Block()));
	}
	
	@Override
	public void caseBlock(NBlock node) {
		this.register = new ArrayList<Statement>();
		this.register = new Block((List<Statement>) this.get(node.get_Statements()));
	}
	
	@Override
	public void caseExpression_Null(NExpression_Null node) {
		this.register = new Null();
	}
	
	@Override
	public void caseExpression_True(NExpression_True node) {
		this.register = new True();
	}
	
	@Override
	public void caseExpression_False(NExpression_False node) {
		this.register = new False();
	}
	
	@Override
	public void caseExpression_Number(NExpression_Number node) {
		this.register = new Decimal(node.get_Number().getText());
	}
	
	@Override
	public void caseExpression_String(NExpression_String node) {
		String string = node.get_String().getText();
		this.register = new Text(string.substring(1, string.length() - 1));
	}
	
	@Override
	public void caseExpression_Variable(NExpression_Variable node) {
		this.register = new Identifier((String) this.get(node.get_Identifier()));
	}
	
	@Override
	public void outExpression_Return(NExpression_Return node) {
		this.register = new Return((Expression) this.get(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Break(NExpression_Break node) {
		this.register = new Break((Expression) this.get(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Continue(NExpression_Continue node) {
		this.register = new Continue((Expression) this.get(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Chain(NExpression_Chain node) {
		this.register = new Chain((Expression) this.get(node.get_Expression()), (String) this.get(node.get_Identifier()));
	}
	
	@Override
	public void caseExpression_Call(NExpression_Call node) {
		Expression expression = (Expression) this.get(node.get_Expression());
		this.register = new ArrayList<Expression>();
		List<Expression> arguments = (List<Expression>) this.get(node.get_Arguments());
		this.register = new Call(expression, arguments);
	}
	
	@Override
	public void caseArgument(NArgument node) {
		List<Expression> arguments = (List<Expression>) this.get();
		arguments.add((Expression) this.get(node.get_Expression()));
		this.register = arguments;
	}
	
	@Override
	public void caseExpression_Assignment(NExpression_Assignment node) {
		this.register = new Assignment((Expression) this.get(node.get_Reference()), (Expression) this.get(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Operation1(NExpression_Operation1 node) {
		this.register = new Operation(
			node.get_Operator().getText(),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		);
	}
	
	@Override
	public void caseExpression_Operation2(NExpression_Operation2 node) {
		this.register = new Operation(
			node.get_Operator().getText(),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		);
	}
	
	@Override
	public void caseExpression_Operation3(NExpression_Operation3 node) {
		this.register = new Operation(
			node.get_Operator().getText(),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		);
	}
	
	@Override
	public void caseExpression_Operation4(NExpression_Operation4 node) {
		this.register = new Operation(
			node.get_Operator().getText(),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		);
	}
	
	@Override
	public void caseExpression_Operation5(NExpression_Operation5 node) {
		this.register = new Operation(
			node.get_Operator().getText(),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		);
	}
	
	@Override
	public void caseFunction(NFunction node) {
		String name = (String) this.get(node.get_FunctionName());
		this.register = new ArrayList<String>();
		List<String> parameters = (List<String>) this.get(node.get_Parameters());
		Expression body = (Expression) this.get(node.get_Block());
		this.register = new Function(name, parameters, body);
	}
	
	@Override
	public void caseParameter(NParameter node) {
		List<String> parameters = (List<String>) this.get();
		parameters.add((String) this.get(node.get_Identifier()));
		this.register = parameters;
	}

	@Override
	public void caseClass(NClass node) {
		String name = (String) this.get(node.get_ClassName());
		String parent = (String) this.get(node.get_ClassParent());
		this.register = new ArrayList<Function>();
		List<Function> methods = (List<Function>) this.get(node.get_ClassStatements());
		this.register = new Type(name, parent, methods);
	}
	
	@Override
	public void caseClassMember_Method(NClassMember_Method node) {
		List<Function> methods = (List<Function>) this.get();
		methods.add((Function) this.get(node.get_Function()));
		this.register = methods;
	}
}
