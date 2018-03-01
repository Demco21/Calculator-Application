import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Controller 
{
	public Controller(){};
	
	public String execute(LinkedList<String> infixTokens)
	{
		String result;
		result = calculateExpression(infixTokens);
		return result;
	}
	
	/* Calculates the value of an infix expression after first converting the expression to postfix */
	private String calculateExpression(LinkedList<String> infixTokens)
	{
		LinkedList<String> postfixTokens = toPostfix(infixTokens);
		
		Stack<Double> operands = new Stack<Double>();
		double op1 = 0.0;
		double op2 = 0.0;
		String result;
		
		for(int i = 0; i < postfixTokens.size(); i++)
		{
			String token = postfixTokens.get(i);
			switch(token)
			{
				/* Performs arithmetic on the postfix expression */
				case "-":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 - op1);
							break;
							
				case "+":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 + op1);
							break;
							
				case "/":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 / op1);
							break;
							
				case "*":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 * op1);
							break;
							
				case "^":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(Math.pow(op2, op1));
							break;
							
							/* stringToDouble method returns NaN if an exception is thrown when converting
							 * to a double. In this case, return null and "invalid input" error is printed. */		
				default:	Double value = stringToDouble(token);
							if(!(value == Double.NaN))
								operands.push(value);
							else
								return null;
			}
		}
		/* If this point is reached, then the top of the stack is the result after calculations are performed */
		result = Double.toString(operands.pop());
		
		/* If the stack is not empty after popping the result, something went wrong.
		 * In this case return null and print "invalid input" error. */
		if(operands.empty())
			return result;
		else
			return null;
	}
	
	/* Converts infix notation to postfix notation */
	private LinkedList<String> toPostfix(LinkedList<String> infixTokens)
	{
		LinkedList<String> postfixTokens = new LinkedList<String>();
		Stack<String> operators = new Stack<String>();
		
		for(int i = 0; i < infixTokens.size(); i++)
		{
			String token = infixTokens.get(i);
			
			/* add operands to postfixTokens as they arrive */
			if(getPrecedence(token) == 0)
			{
				postfixTokens.add(token);
			}
			
			/* if stack is empty or contains a "(" on top, push incoming operator unless incoming symbol is ")" */
			else if((operators.empty() || operators.peek().equals("(")) && !token.equals(")"))
				operators.push(token);
			
			/* if the incoming symbol is a "(" push it to the stack */
			else if(token.equals("("))
				operators.push(token);
			
			/* if the incoming symbol is ")" pop the stack and add operators to postExpTokens until "(" is reached then discard "("*/
			else if(token.equals(")"))
			{
				while(!(operators.peek().equals("(")))
				{
					postfixTokens.add(operators.pop());
				}
				operators.pop();
			}
			
			/* if the incoming symbol has higher precedence than the top of the stack, push it to the stack */
			else if(getPrecedence(token) > getPrecedence(operators.peek()))
				operators.push(token);
			
			/* if the incoming symbol has equal precedence to the top of the stack, add top of stack symbol to postfixTokens
			 * and pop the top of the stack then push incoming symbol. This uses left to right association. */
			else if(getPrecedence(token) == getPrecedence(operators.peek()))
			{
				postfixTokens.add(operators.pop());
				operators.push(token);
			}
			
			/* if the incoming symbol has lesser precedence than the top of the stack, add top of stack symbol to postfixTokens
			 * and pop the top of the stack. Continue doing this until top of stack is no longer of lesser precedence.*/
			else if(getPrecedence(token) < getPrecedence(operators.peek()))
			{
				do{
					postfixTokens.add(operators.pop());
				}while(!operators.empty() && getPrecedence(token) < getPrecedence(operators.peek()));
				operators.push(token);
			}
		}/* end for loop */
		
		/* Once the end of the expression is reached, pop the rest of the stack and add the symbols to postfixTokens */
		while(!operators.empty())
		{
			postfixTokens.add(operators.pop());
		}

		return postfixTokens;
	}
	
	public LinkedList<String> editParenContent(LinkedList<String> infixTokens)
	{
		int index = infixTokens.size()-1;
		int count = 1;
		while(count > 0)
		{
			String token = infixTokens.get(--index);
			if(token.equals("("))
				count--;
			else if(token.equals(")"))
				count++;
		}
		int size = infixTokens.size();
		for(int i = ++index; i < size; i++)
			infixTokens.removeLast();
		return infixTokens;
	}
	
	public String editParenContent(String infixTokens)
	{
		int index = infixTokens.length()-1;
		int count = 1;
		while(count > 0)
		{
			Character token = infixTokens.charAt(--index);
			if(token == '(')
				count--;
			else if(token == ')')
				count++;
		}
		String result = infixTokens.substring(0, index+1);
		return result;
	}
	
	public LinkedList<String> validateParentheses(LinkedList<String> infixTokens)
	{
		Set<Integer> validParenIndex = new HashSet<>();
		int outterIndex = infixTokens.size()-1;
		while(outterIndex >= 0)
		{
			String token1 = infixTokens.get(outterIndex);
			if(token1.equals("(") && !validParenIndex.contains(outterIndex))
				infixTokens.remove(outterIndex);
			else if(token1.equals(")"))
			{
				validParenIndex.add(outterIndex);
				int count = 1;
				int innerIndex = outterIndex;
				while(count > 0)
				{
					String token2 = infixTokens.get(--innerIndex);
					if(token2.equals("("))
						count--;
					else if(token2.equals(")"))
						count++;
				}
				validParenIndex.add(innerIndex);
			}
			outterIndex--;
		}
		return infixTokens;
	}
	
	/* Assigns an integer value for order of precedence of operands and returns the value */
	private int getPrecedence(String operator)
	{
		switch(operator)
		{
			case "-": return 1;
			case "+": return 1;
			case "/": return 2;
			case "*": return 2;
			case "^": return 3;
			case "(": return -1;
			case ")": return -1;
			default : return 0;
		}
	}
	
	/* Converts a string to a double */
	private double stringToDouble(String operand)
	{
		double value;
		try 
		{
			value = Double.parseDouble(operand);
		}
		catch (NumberFormatException e) 
		{
			return Double.NaN;
		}
		return value;
	}
}
