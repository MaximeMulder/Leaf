package leaf;

import java.util.ArrayList;
import java.util.List;

import leaf.language_leaf.*;
import leaf.structure.*;

@SuppressWarnings("unchecked")
public class Visitor extends Walker {
	private Object register;
	private List<Object> registers;
	
	public Visitor() {
		this.register = null;
		this.registers = new ArrayList<Object>();
	}
	
	private Object push(Object object, Node node) {
		this.registers.add(object);
		this.visit(node);
		return this.registers.remove(this.registers.size() - 1);
	}
	
	private Object pop() {
		return this.registers.get(this.registers.size() - 1);
	}
	
	private Object get(Node node) {
		this.visit(node);
		Object register = this.register;
		this.register = null;
		return register;
	}
	
	private void set(Object object) {
		this.register = object;
	}
	
	public Program program() {
		return (Program) this.register;
	}
	
	public void visit(Node tree) {
		tree.apply(this);
	}
	
	@Override
	public void caseNumber(NNumber node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseString(NString node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseIdentifier(NIdentifier node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary1(NOperatorBinary1 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary2(NOperatorBinary2 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary3(NOperatorBinary3 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary4(NOperatorBinary4 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary5(NOperatorBinary5 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperatorBinary6(NOperatorBinary6 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseFile(NFile node) {
		this.set(new Program((List<Statement>) this.push(new ArrayList<Statement>(), node.get_Statements())));
	}
	
	@Override
	public void caseStatement(NStatement node) {
		((List<Statement>) this.pop()).add(new Statement((Expression) this.get(node.get_Expression())));
	}
	
	@Override
	public void caseBlock(NBlock node) {
		this.set(new Block((List<Statement>) this.push(new ArrayList<Statement>(), node.get_Statements())));
	}
	
	@Override
	public void caseExpression_If(NExpression_If node) {
		this.set(new If(
			(Expression) this.get(node.get_Condition()),
			(Expression) this.get(node.get_Body()),
			(Expression) this.get(node.get_Else())
		));
	}
	
	@Override
	public void caseExpression_Loop(NExpression_Loop node) {
		this.set(new Loop((Expression) this.get(node.get_Body())));
	}
	
	@Override
	public void caseExpression_While(NExpression_While node) {
		this.set(new While((Expression) this.get(node.get_Condition()), (Expression) this.get(node.get_Body())));
	}
	
	@Override
	public void caseExpression_WhileBlock(NExpression_WhileBlock node) {
		this.set(new While((Expression) this.get(node.get_Condition()), (Expression) this.get(node.get_Body())));
	}
	
	@Override
	public void caseExpression_DoWhile(NExpression_DoWhile node) {
		this.set(new DoWhile((Expression) this.get(node.get_Body()), (Expression) this.get(node.get_Condition())));
	}
	
	@Override
	public void caseExpression_For(NExpression_For node) {
		this.set(new For(
			(Declaration) this.get(node.get_Element()),
			(Expression) this.get(node.get_Array()),
			(Expression) this.get(node.get_Body())
		));
	}
	
	@Override
	public void caseExpression_ForBlock(NExpression_ForBlock node) {
		this.set(new For(
			(Declaration) this.get(node.get_Element()),
			(Expression) this.get(node.get_Array()),
			(Expression) this.get(node.get_Body())
		));
	}
	
	@Override
	public void caseExpression_Declaration(NExpression_Declaration node) {
		this.set(new Declaration(
			(String) this.get(node.get_Identifier()),
			null
		));
	}
	
	@Override
	public void caseExpression_DeclarationType(NExpression_DeclarationType node) {
		this.set(new Declaration(
			(String) this.get(node.get_Identifier()),
			(Expression) this.get(node.get_Type())
		));
	}
	
	@Override
	public void outExpression_Return(NExpression_Return node) {
		this.set(new Return((Expression) this.get(node.get_Expression())));
	}
	
	@Override
	public void outExpression_Break(NExpression_Break node) {
		this.set(new Break((Expression) this.get(node.get_Expression())));
	}
	
	@Override
	public void outExpression_Continue(NExpression_Continue node) {
		this.set(new Continue((Expression) this.get(node.get_Expression())));
	}
	
	@Override
	public void caseExpression_Null(NExpression_Null node) {
		this.set(new Null());
	}
	
	@Override
	public void caseExpression_True(NExpression_True node) {
		this.set(new True());
	}
	
	@Override
	public void caseExpression_False(NExpression_False node) {
		this.set(new False());
	}
	
	@Override
	public void caseExpression_Number(NExpression_Number node) {
		this.set(new Decimal((String) this.get(node.get_Content())));
	}
	
	@Override
	public void caseExpression_String(NExpression_String node) {
		this.set(new Text((String) this.get(node.get_Content())));
	}
	
	@Override
	public void caseExpression_Variable(NExpression_Variable node) {
		this.set(new Identifier((String) this.get(node.get_Content())));
	}
	
	@Override
	public void caseExpression_Array(NExpression_Array node) {
		this.set(new Array((List<Expression>) this.push(new ArrayList<Expression>(), node.get_Arguments())));
	}
	
	@Override
	public void caseExpression_Chain(NExpression_Chain node) {
		this.set(new Chain((Expression) this.get(node.get_Expression()), (String) this.get(node.get_Identifier())));
	}
	
	@Override
	public void caseExpression_Call(NExpression_Call node) {
		this.set(new Call(
			(Expression) this.get(node.get_Expression()),
			(List<Expression>) this.push(new ArrayList<Expression>(), node.get_Arguments())
		));
	}
	
	@Override
	public void caseExpression_Access(NExpression_Access node) {
		this.set(new Access(
			(Expression) this.get(node.get_Expression()),
			(List<Expression>) this.push(new ArrayList<Expression>(), node.get_Arguments())
		));
	}
	
	@Override
	public void caseArgument(NArgument node) {
		((List<Expression>) this.pop()).add((Expression) this.get(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Assignment(NExpression_Assignment node) {
		this.set(new Assignment((Expression) this.get(node.get_Reference()), (Expression) this.get(node.get_Expression())));
	}
	
	@Override
	public void caseExpression_OperationPre1(NExpression_OperationPre1 node) {
		this.set(new OperationPre(
			(Expression) this.get(node.get_Expression()),
			"&"
		));
	}
	
	@Override
	public void caseExpression_OperationPre2(NExpression_OperationPre2 node) {
		this.set(new OperationPre(
			(Expression) this.get(node.get_Expression()),
			"*"
		));
	}
	
	@Override
	public void caseExpression_OperationPost1(NExpression_OperationPost1 node) {
		this.set(new OperationPost(
			(Expression) this.get(node.get_Expression()),
			"&"
		));
	}
	
	@Override
	public void caseExpression_OperationPost2(NExpression_OperationPost2 node) {
		this.set(new OperationPost(
			(Expression) this.get(node.get_Expression()),
			"?"
		));
	}
	
	@Override
	public void caseExpression_OperationBinary1(NExpression_OperationBinary1 node) {
		this.set(new OperationBinary(
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right()),
			(String) this.get(node.get_Operator())
		));
	}
	
	@Override
	public void caseExpression_OperationBinary2(NExpression_OperationBinary2 node) {
		this.set(new OperationBinary(
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right()),
			(String) this.get(node.get_Operator())
		));
	}
	
	@Override
	public void caseExpression_OperationBinary3(NExpression_OperationBinary3 node) {
		this.set(new OperationBinary(
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right()),
			(String) this.get(node.get_Operator())
		));
	}
	
	@Override
	public void caseExpression_OperationBinary4(NExpression_OperationBinary4 node) {
		this.set(new OperationBinary(
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right()),
			(String) this.get(node.get_Operator())
		));
	}
	
	@Override
	public void caseExpression_OperationBinary5(NExpression_OperationBinary5 node) {
		this.set(new OperationBinary(
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right()),
			(String) this.get(node.get_Operator())
		));
	}
	
	@Override
	public void caseFunction(NFunction node) {
		this.set(new Function(
			(String) this.get(node.get_Name()),
			(Expression) this.get(node.get_Type()),
			(List<Declaration>) this.push(new ArrayList<Declaration>(), node.get_Parameters()),
			(Expression) this.get(node.get_Block())
		));
	}
	
	@Override
	public void caseParameter(NParameter node) {
		((List<Declaration>) this.pop()).add((Declaration) this.get(node.get_Variable()));
	}

	@Override
	public void caseClass(NClass node) {
		this.set(new Type(
			(String) this.get(node.get_Name()),
			(Expression) this.get(node.get_Parent()),
			(List<Function>) this.push(new ArrayList<Function>(), node.get_Members())
		));
	}
	
	@Override
	public void caseMember_Method(NMember_Method node) {
		((List<Function>) this.pop()).add((Function) this.get(node.get_Function()));
	}
	
	@Override
	public void caseVariable(NVariable node) {
		this.set(new Declaration(
			(String) this.get(node.get_Identifier()),
			(Expression) this.get(node.get_Type())
		));
	}
}
