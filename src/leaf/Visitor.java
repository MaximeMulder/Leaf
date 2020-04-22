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
	public void caseOperator1(NOperator1 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperator2(NOperator2 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperator3(NOperator3 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperator4(NOperator4 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperator5(NOperator5 node) {
		this.set(node.getText());
	}
	
	@Override
	public void caseOperator6(NOperator6 node) {
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
			(Expression) this.get(node.get_Block()),
			(Expression) this.get(node.get_Else())
		));
	}
	
	@Override
	public void caseExpression_Loop(NExpression_Loop node) {
		this.set(new Loop((Expression) this.get(node.get_Block())));
	}
	
	@Override
	public void caseExpression_While(NExpression_While node) {
		this.set(new While((Expression) this.get(node.get_Condition()), (Expression) this.get(node.get_Block())));
	}
	
	@Override
	public void caseExpression_For(NExpression_For node) {
		this.set(new For(
			(String) this.get(node.get_Identifier()),
			(Expression) this.get(node.get_Expression()),
			(Expression) this.get(node.get_Block())
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
	public void caseExpression_Operation1(NExpression_Operation1 node) {
		this.set(new Operation(
			(String) this.get(node.get_Operator()),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		));
	}
	
	@Override
	public void caseExpression_Operation2(NExpression_Operation2 node) {
		this.set(new Operation(
			(String) this.get(node.get_Operator()),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		));
	}
	
	@Override
	public void caseExpression_Operation3(NExpression_Operation3 node) {
		this.set(new Operation(
			(String) this.get(node.get_Operator()),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		));
	}
	
	@Override
	public void caseExpression_Operation4(NExpression_Operation4 node) {
		this.set(new Operation(
			(String) this.get(node.get_Operator()),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		));
	}
	
	@Override
	public void caseExpression_Operation5(NExpression_Operation5 node) {
		this.set(new Operation(
			(String) this.get(node.get_Operator()),
			(Expression) this.get(node.get_Left()),
			(Expression) this.get(node.get_Right())
		));
	}
	
	@Override
	public void caseFunction(NFunction node) {
		this.set(new Function(
			(String) this.get(node.get_Name()),
			(List<String>) this.push(new ArrayList<String>(), node.get_Parameters()),
			(Expression) this.get(node.get_Block())
		));
	}
	
	@Override
	public void caseParameter(NParameter node) {
		((List<String>) this.pop()).add((String) this.get(node.get_Identifier()));
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
}
