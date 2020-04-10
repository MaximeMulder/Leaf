package leaf;

import java.util.ArrayList;
import java.util.List;

import leaf.exception.*;
import leaf.factory.FactoryValues;
import leaf.language_leaf.*;
import leaf.structure.*;

public class Visitor extends Walker {
	private Engine engine;
	private FactoryValues values;
	
	private IValue value;
	private String name;
	private ValueClass type;
	private Value self;
	private List<Value> arguments;
	private List<String> parameters;
	
	public Visitor(Engine engine) {
		this.engine = engine;
		this.values = this.engine.getValues();
		this.value = null;
		this.name = null;
		this.self = null;
		this.arguments = null;
		this.parameters = new ArrayList<String>();;
	}
	
	private IValue getValue(Node node) {
		node.apply(this);
		IValue value = this.value;
		this.value = null;
		if (value == null) {
			throw new ErrorUndefined();
		}
		
		return value;
	}
	
	private Value readValue(Node node) {
		Value value = this.getValue(node).read();
		if (value == null) {
			throw new ErrorUndefined();
		}
		
		return value;
	}
	
	private void setValue(IValue value) {
		this.value = value;
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
	
	private ValueClass pushType(ValueClass type) {
		ValueClass old = this.type;
		this.type = type;
		return old;
	}
	
	private void popType(ValueClass type) {
		this.type = type;
	}
	
	private Value getSelf() {
		Value self = this.self;
		this.self = null;
		return self;
	}
	
	private void setSelf(Value self) {
		this.self = self;
	}
	
	private void addArgument(Value value) {
		this.arguments.add(value);
	}
	
	private void addParameter(String parameter) {
		this.parameters.add(parameter);
	}
	
	private List<String> getParameters() {
		List<String> parameters = this.parameters;
		this.parameters = new ArrayList<String>();
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
		this.setValue(null);
	}
	
	@Override
	public void caseStructure_If(NStructure_If node) {
		if (this.readValue(node.get_Condition()).castBoolean().getPrimitive()) {
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
			while (this.readValue(node.get_Condition()).castBoolean().getPrimitive()) {
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
		this.setValue(this.values.getNull());
	}
	
	@Override
	public void caseExpression_True(NExpression_True node) {
		this.setValue(this.values.getBooleanTrue());
	}
	
	@Override
	public void caseExpression_False(NExpression_False node) {
		this.setValue(this.values.getBooleanFalse());
	}
	
	@Override
	public void caseExpression_Number(NExpression_Number node) {
		this.setValue(this.values.getInteger(Integer.parseInt(node.get_Number().getText())));
	}
	
	@Override
	public void caseExpression_String(NExpression_String node) {
		String string = node.get_String().getText();
		this.setValue(this.values.getString(string.substring(1, string.length() - 1)));
	}
	
	@Override
	public void caseExpression_Variable(NExpression_Variable node) {
		this.setValue(this.engine.getVariable(this.getName(node.get_Identifier())));
	}
	
	@Override
	public void outExpression_Return(NExpression_Return node) {
		throw new ControlReturn(this.readValue(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Break(NExpression_Break node) {
		throw new ControlBreak(this.readValue(node.get_Expression()));
	}
	
	@Override
	public void outExpression_Continue(NExpression_Continue node) {
		throw new ControlContinue(this.readValue(node.get_Expression()));
	}
	
	@Override
	public void caseExpression_Chain(NExpression_Chain node) {
		Value value = this.readValue(node.get_Expression());
		String name = this.getName(node.get_Identifier());
		ValueFunction method = this.engine.getMethod(value, name);
		if (method != null) {
			this.setValue(method);
			this.setSelf(value);
			return;
		}
		
		if (value instanceof ValueInstance) {
			ValueInstance instance = value.castInstance();
			Variable attribute = instance.getAttribute(name);
			if (attribute == null) {
				attribute = this.values.getVariable(name);
				instance.setAttribute(attribute);
			}

			this.setValue(attribute);
		}
	}
	
	@Override
	public void caseExpression_Call(NExpression_Call node) {
		Value expression = this.readValue(node.get_Expression());
		List<Value> arguments = this.arguments;
		this.arguments = new ArrayList<Value>();
		Value self = this.getSelf();
		if (self != null) {
			this.addArgument(self);
		}
		
		this.visit(node.get_Arguments());
		this.setValue(this.engine.functionCall(expression.castFunction(), this.arguments));
		this.arguments = arguments;
	}
	
	@Override
	public void caseExpression_Assignment(NExpression_Assignment node) {
		this.getValue(node.get_Reference()).write(this.readValue(node.get_Expression()));
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
		this.addArgument(this.readValue(node.get_Expression()));
	}
	
	@Override
	public void caseFunction(NFunction node) {
		this.visit(node.get_Parameters());
		String name = this.getName(node.get_FunctionName());
		ValueFunction function = this.values.getFunction(name, this.getParameters(), node.get_Block());
		if (name != null) {
			this.engine.setVariable(name, function);
		}

		this.setValue(function);
	}
	
	@Override
	public void caseParameter(NParameter node) {
		this.addParameter(this.getName(node.get_Identifier()));
	}

	@Override
	public void caseClass(NClass node) {
		String name = this.getName(node.get_ClassName());
		ValueClass type = this.pushType(this.values.getType(name));
		this.visit(node.get_ClassStatements());
		if (name != null) {
			this.engine.setVariable(name, this.type);
		}
		
		this.setValue(this.type);
		this.popType(type);
	}
	
	@Override
	public void caseClassMember_Method(NClassMember_Method node) {
		ValueFunction function = this.readValue(node.get_Function()).castFunction();
		this.type.addMethod(function.getName(), function);
	}
	
	private void operationBinary(Node operator, Node left, Node right) {
		this.setValue(this.engine.operation(operator.getText(), this.readValue(left), this.readValue(right)));
	}
}
