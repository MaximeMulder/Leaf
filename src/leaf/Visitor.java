package leaf;

import java.util.ArrayList;
import java.util.List;

import leaf.exception.*;
import leaf.exception.Error;
import leaf.language_leaf.*;
import leaf.structure.*;

public class Visitor extends Walker {
	private Engine engine;
	private Data data;
	private Value self;
	private String name;
	private List<Value> arguments;
	private List<String> parameters;
	
	public Visitor(Engine engine) {
		this.engine = engine;
		this.data = null;
		this.self = null;
		this.arguments = null;
		this.parameters = null;
	}
	
	private Data getData(Node node) throws Error {
		node.apply(this);
		Data data = this.data;
		this.data = null;
		return data;
	}
	
	private Value getValue(Node node) throws Error {
		Data data = this.getData(node);
		if (data == null) {
			throw new ErrorUndefined();
		}
		
		Value value = this.engine.read(data);
		if (value == null) {
			throw new ErrorUndefined();
		}
		
		return value;
	}
	
	private void setData(Data data) {
		this.data = data;
	}
	
	private Value getSelf() {
		Value self = this.self;
		this.self = null;
		return self;
	}
	
	private void setSelf(Value self) {
		this.self = self;
	}
	
	private String getName(Node node) {
		node.apply(this);
		String name = this.name;
		this.name = null;
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void addArgument(Value value) {
		this.arguments.add(value);
	}
	
	private void addParameter(String parameter) {
		this.parameters.add(parameter);
	}
	
	private List<String> getParameters() {
		List<String> parameters = this.parameters;
		this.parameters = null;
		return parameters;
	}
	
	public void visit(Node tree) {
		tree.apply(this);
	}
	
	@Override
	public void caseIdentifier(NIdentifier node) {
		this.setName(node.getText());
	}
	
	@Override
	public void outStatement_Expression(NStatement_Expression node) {
		this.setData(null);
	}
	
	@Override
	public void caseStructure_If(NStructure_If node) {
		if (this.engine.castBoolean(this.getValue(node.get_Condition())).getPrimitive()) {
			this.visit(node.get_Block());
		} else {
			this.visit(node.get_ElseOption());
		}
	}
	
	@Override
	public void caseStructure_Loop(NStructure_Loop node) {
		try {
			while (true) {
				try {
					this.visit(node.get_Block());
				} catch (ControlContinue control) {}
			}
		} catch (ControlBreak control) {}
	}
	
	@Override
	public void caseStructure_While(NStructure_While node) {
		try {
			while (this.engine.castBoolean(this.getValue(node.get_Condition())).getPrimitive()) {
				try {
					this.visit(node.get_Block());
				} catch (ControlContinue control) {}
			}
		} catch (ControlBreak control) {}
	}
	
	@Override
	public void caseBlock(NBlock node) {
		this.engine.pushScope();
		this.visit(node.get_Statements());
		this.engine.popScope();
	}
	
	@Override
	public void caseExpression_Null(NExpression_Null node) {
		this.setData(this.engine.newNull());
	}
	
	@Override
	public void caseExpression_True(NExpression_True node) {
		this.setData(this.engine.newTrue());
	}
	
	@Override
	public void caseExpression_False(NExpression_False node) {
		this.setData(this.engine.newFalse());
	}
	
	@Override
	public void caseExpression_Number(NExpression_Number node) {
		this.setData(this.engine.newInteger(Integer.parseInt(node.get_Number().getText())));
	}
	
	@Override
	public void caseExpression_String(NExpression_String node) {
		String string = node.get_String().getText();
		this.setData(this.engine.newString(string.substring(1, string.length() - 1)));
	}
	
	@Override
	public void caseExpression_Variable(NExpression_Variable node) {
		this.setData(this.engine.getVariable(this.getName(node.get_Identifier())));
	}
	
	@Override
	public void outExpression_Return(NExpression_Return node) {
		throw new ControlReturn(this.getValue(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Break(NExpression_Break node) {
		throw new ControlBreak(this.getValue(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Continue(NExpression_Continue node) {
		throw new ControlContinue(this.getValue(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Chain(NExpression_Chain node) {
		Value value = this.getValue(node.get_Expression());
		String name = this.getName(node.get_Identifier());
		Data data = null;
		if (value instanceof ValueInstance) {
			data = this.engine.getAttribute(this.engine.castInstance(value), name);
			if (data != null) {
				this.setData(data);
				return;
			}
		}
		
		data = this.engine.getMethod(value, name);
		if (data != null) {
			this.setData(data);
			this.setSelf(value);
			return;
		}
	}
	
	@Override
	public void caseExpression_Call(NExpression_Call node) {
		Value expression = this.getValue(node.get_Expression());
		List<Value> arguments = this.arguments;
		this.arguments = new ArrayList<Value>();
		Value self = this.getSelf();
		if (self != null) {
			this.addArgument(self);
		}
		
		this.visit(node.get_Arguments());
		this.setData(this.engine.functionCall(this.engine.castFunction(expression), this.arguments));
		this.arguments = arguments;
	}
	
	@Override
	public void caseExpression_Assignment(NExpression_Assignment node) {
		this.engine.write(this.getData(node.get_Reference()), this.getValue(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Operation1(NExpression_Operation1 node) {
		this.operationBinary(node.get_Operator(), node.get_Left(), node.get_Right());
	}
	
	@Override
	public void caseExpression_Operation2(NExpression_Operation2 node) {
		this.operationBinary(node.get_Operator(), node.get_Left(), node.get_Right());
	}
	
	@Override
	public void caseExpression_Operation3(NExpression_Operation3 node) {
		this.operationBinary(node.get_Operator(), node.get_Left(), node.get_Right());
	}
	
	@Override
	public void caseExpression_Operation4(NExpression_Operation4 node) {
		this.operationBinary(node.get_Operator(), node.get_Left(), node.get_Right());
	}
	
	@Override
	public void caseExpression_Operation5(NExpression_Operation5 node) {
		this.operationBinary(node.get_Operator(), node.get_Left(), node.get_Right());
	}
	
	@Override
	public void caseArgument(NArgument node) {
		this.addArgument(this.engine.read(this.getValue(node.get_Expression())));
	}
	
	@Override
	public void caseFunction(NFunction node) {
		this.parameters = new ArrayList<String>();
		this.visit(node.get_Parameters());
		ValueFunction function = this.engine.newFunction(this.getParameters(), node.get_Block());
		this.setData(function);
		String name = this.getName(node.get_FunctionName());
		if (name != null) {
			this.engine.setVariable(name, function);
		}
	}
	
	@Override
	public void caseParameter(NParameter node) {
		this.addParameter(this.getName(node.get_Identifier()));
	}
	
	private void operationBinary(Node operator, Node left, Node right) {
		this.setData(this.engine.operation(operator.getText(), this.getValue(left), this.getValue(right)));
	}
}
