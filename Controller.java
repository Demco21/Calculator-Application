import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Controller 
{
	private LinkedList<String> infixTokens = new LinkedList<String>(); 	/* Stores each token of the expression input by the user - this is paralleled by the top text field */
	private Calculator calculator = Calculator.getInstance();			/* reference to the calculator GUI */	
	private int parenOffset = 0;										/* parentheses offset used to limit the amount of closed parentheses input to be less than or equal to the amount of open parentheses */
	private Boolean justClickedEQ = false;								/* boolen value set true after the user clicks the BUTTON_EQ and false after clicking ANY other button */
	
	/* Constructor */
	protected Controller(){}
	
	/*
	 * 
	 * 
	 * ARITHMETIC CALCULATIONS BELOW
	 * 
	 * 
	 */
	
	/* executes the calculation on the infix expression */
	protected String execute(LinkedList<String> infixTokens)
	{
		String result;
		result = calculateExpression(infixTokens);
		return result;
	}
	
	/* Calculates the value of an infix expression after first converting the expression to postfix */
	private String calculateExpression(LinkedList<String> infixTokens)
	{
		/* convert expression to postfix notation */
		LinkedList<String> postfixTokens = toPostfix(infixTokens);
		
		/* stack to store the operands of the postfix expression */
		Stack<Float> operands = new Stack<Float>();
		float op1 = 0.0f;
		float op2 = 0.0f;
		String result;
		
		for(int i = 0; i < postfixTokens.size(); i++)
		{
			String token = postfixTokens.get(i);
			
			/* switch statement decides how to handle each token in the postfix expression */
			switch(token)
			{
				/* when subtraction is reached then subtract the last two operands and push the result */
				case "-":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 - op1);
							break;
				
				/* when addition is reached then add the last two operands and push the result */
				case "+":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 + op1);
							break;
							
				/* when division is reached then divide the last two operands and push the result */			
				case "/":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 / op1);
							break;
							
				/* when multiplication is reached then multiply the last two operands and push the result */							
				case "*":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push(op2 * op1);
							break;
				/* when exponent is reached then calculate the first operand to the power of the second and push the result */
				case "^":	op1 = operands.pop();
							op2 = operands.pop();
							operands.push((float) Math.pow(op2, op1));
							break;
							
							/* default is reached when we find an operand, in this case we push to the operands stack */
							/* stringToDouble method returns NaN if an exception is thrown when converting to a double. */		
				default:	Float value = stringToFloat(token);
							if(!(value == Float.NaN))
								operands.push(value);
							else
								return null;
			}
		}
		/* If this point is reached, then the top of the stack is the result after calculations are performed */
		/* Store as a String */
		result = Float.toString(operands.pop());
		
		/* If the stack is not empty after popping the result, something went wrong.
		 * In this case return null */
		if(operands.empty())
			return result;
		else
			return null;
	}
	
	/* Converts infix notation to postfix notation */
	private LinkedList<String> toPostfix(LinkedList<String> infixTokens)
	{
		/* stores the new postfix expression, this is the return value */
		LinkedList<String> postfixTokens = new LinkedList<String>();
		
		/* stack to store the operators of the infix expression */
		Stack<String> operators = new Stack<String>();
		
		for(int i = 0; i < infixTokens.size(); i++)
		{
			String token = infixTokens.get(i);
			
			/* operands have a precedence of zero, add operands to the return value as they arrive */
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
			
			/* if the incoming symbol is ")" pop the stack and add operators to return value until "(" is reached then discard "("*/
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
			
			/* if the incoming symbol has equal precedence to the top of the stack, add top of stack symbol to the return value
			 * and pop the top of the stack then push incoming symbol. This uses left to right association. */
			else if(getPrecedence(token) == getPrecedence(operators.peek()))
			{
				postfixTokens.add(operators.pop());
				operators.push(token);
			}
			
			/* if the incoming symbol has lesser precedence than the top of the stack, add top of stack symbol to the return value
			 * and pop the top of the stack. Continue doing this until top of stack is no longer of lesser precedence.*/
			else if(getPrecedence(token) < getPrecedence(operators.peek()))
			{
				do{
					postfixTokens.add(operators.pop());
				}while(!operators.empty() && getPrecedence(token) < getPrecedence(operators.peek()));
				operators.push(token);
			}
		}/* end for loop */
		
		/* Once the end of the expression is reached, pop the rest of the stack and add the symbols to the return value */
		while(!operators.empty())
		{
			postfixTokens.add(operators.pop());
		}

		return postfixTokens;
	}
	
	/*
	 * 
	 * 
	 * ACTION METHODS BELOW
	 * 
	 * 
	 */
	
	/* handles clicked digits buttons.
	 * as the user clicks digits, the digits will append as a string to the bottom text field.
	 * this is how the user builds their operand */
	protected void operandAction(String operand)
	{
		/* if user clicks a digit after just calculating an expression, handle input as a brand new expression */
		if(justClickedEQ)
			clearAction();
		/* only append zero to the bottom text field if the current value of bottom text field is not equal to zero */
		if(operand.equals(Calculator.DIGIT_ZERO))
		{
			if(!isBotZero())
				calculator.setBotText(calculator.getBotText() + Calculator.DIGIT_ZERO);
		}
		/* if user enters digit right after a right parentheses, then backtrack to the last open parentheses and start from there */
		else if(isLastTokenRP())
		{
			infixTokens = editParenContent(infixTokens);
			String topText = calculator.getTopText();
			String newTopText = editParenContent(topText);
			calculator.setTopText(newTopText);
			calculator.setBotText(operand);
			recalcParenOffset();
		}
		/* if the bottom is zero then remove it and start with the current digit */
		else if(isBotZero())
		{
			calculator.setBotText(operand);
		}
		/* else just append the digit to the bottom text field */
		else
			calculator.setBotText(calculator.getBotText() + operand);
		justClickedEQ = false;
	}
	
	/* handles clicked operators button.
	 * as user clicks operators, the current operand in the bottom text field is added
	 * to the infix tokens list and appended as a string to the top text field.
	 * the same goes for the operand that the user just clicked. */
	protected void operatorAction(String operator)
	{
		/* isOperatorLocked() returns true if the last token of the infix expression is already an operator
		 * in this case we do not want to keep adding operators as this would be invalid input. */
		if(!isOperatorLocked())
		{
			/* if the user has entered a decimal with no digits after it, remove the decimal */
			if(isDanglingDecimal())
				removeDanglingDecimal();
			
			String newTopText = new String();
			String botText = calculator.getBotText();

			/* if the top text field is the result of a recently calculated expression OR a right parentheses */
			if(justClickedEQ || getLastInfixToken().equals(Calculator.RIGHT_PAREN))
				/* append only the operator to the top text field */
				newTopText = calculator.getTopText() + operator;
			else
			{
				/* all other cases append the bottom text and the operator to the top text field and add the bottom text to the infix tokens */
				newTopText = calculator.getTopText() + calculator.getBotText() + operator;
				infixTokens.add(botText);
			}

			/* always add the operator to the infix tokens, set the new top text, and reset the bottom text to zero */
			infixTokens.add(operator);
			calculator.setTopText(newTopText);
			calculator.setBotText(Calculator.DIGIT_ZERO);
		}
		
		/* if isOperatorLocked() returns true, then the last token of the infix expression is already an operator
		 * in this case we instead remove the last operator from the tokens and append the new operator 
		 * and do the same for the top text field */
		else
		{
			/* remove the last character of the top text and replace it with the new operator
			 * likewise, remove the last token of infixTokens and replace it with the new operator */
			String topText = calculator.getTopText();
			int index = topText.length()-1;
			String newTopText = topText.substring(0, index) + operator;
			infixTokens.removeLast();
			infixTokens.add(operator);
			calculator.setTopText(newTopText);
		}
		justClickedEQ = false;
	}
	
	/* handles clicked left parentheses button */
	protected void leftParenAction()
	{
		/* if the last infix token is a right parentheses */
		if(isLastTokenRP())
		{
			/* find the concurrent left parentheses and remove all the content after it 
			 * do this for the infix tokens list AND the top text field */
			infixTokens = editParenContent(infixTokens);
			String topText = calculator.getTopText();
			String newTopText = editParenContent(topText);
			calculator.setTopText(newTopText);
			calculator.setBotText(Calculator.DIGIT_ZERO);
			parenOffset = recalcParenOffset();	/* with the newly edited expression we must recalculate the parentheses offset */
		}
		/* if the top text field is the result of recently calculated expression */
		else if(justClickedEQ)
		{
			/* clear everything and start with the left parentheses */
			clearAction();
			calculator.setTopText(Calculator.LEFT_PAREN);
			infixTokens.add(Calculator.LEFT_PAREN);
			parenOffset++;	/* increment the parentheses offset */
		}
		else
		{
			/* otherwise just append the left parentheses to the top text field and the infix tokens list */
			String value = calculator.getTopText() + Calculator.LEFT_PAREN;
			infixTokens.add(Calculator.LEFT_PAREN);
			calculator.setTopText(value);
			parenOffset++;	/* increment the parentheses offset */
		}
		justClickedEQ = false;
	}
	
	/* handles clicked right parentheses button */
	protected void rightParenAction()
	{
		/* only allow right parentheses if the parentheses offset is greater than zero 
		 * this is to ensure we dont add more right parentheses than left parentheses
		 * as this case is not possible for a mathematical expression*/
		if(parenOffset > 0)
		{
			String botText = new String();
			String topText = calculator.getTopText();
			
			/* if the user has entered a decimal with no digits after it, remove the decimal */
			if(isDanglingDecimal())
				removeDanglingDecimal();
			
			/* only append the bottom text operand if the last token is NOT a right parentheses 
			 * as it would only make sense to have an operator following a right parentheses*/
			if(!getLastInfixToken().equals(Calculator.RIGHT_PAREN))
			{
				botText = calculator.getBotText();
				infixTokens.add(botText);
			}
			
			/* update the text fields and infix tokens list and decrement the parentheses offset */
			String newTopText = topText + botText + Calculator.RIGHT_PAREN;
			infixTokens.add(Calculator.RIGHT_PAREN);
			calculator.setTopText(newTopText);
			calculator.setBotText(Calculator.DIGIT_ZERO);
			parenOffset--;
			justClickedEQ = false;
		}
	}
	
	/* handles clicked equals button */
	protected void equalsAction()
	{
		String result = null;
		String topText = calculator.getTopText();
		String botText = calculator.getBotText();
		
		/* if the user has entered a decimal with no digits after it, remove the decimal */
		if(isDanglingDecimal())
			removeDanglingDecimal();
		
		/* if the parentheses offset is not zero then we validate the parentheses by removing any extra open parentheses that dont belong */
		if(parenOffset > 0)
			infixTokens = validateParentheses(infixTokens);
		
		/* if the last token in the infix tokens list is an operastor */
		if(isDanglingOperator())
		{
			/* add the bottom text operand to the infix tokens list and execute calculation */
			infixTokens.add(botText);
			result = execute(infixTokens);
		}
		
		/* if the last token in the infix tokens list is a right parentheses */
		else if(isLastTokenRP())
			/* execute calculation */
			result = execute(infixTokens);
		
		/* if the top text field is empty then the user hasnt entered an expression
		 * OR
		 * if the top text is a result of the recently calculation expression */
		else if(topText.isEmpty() || justClickedEQ)
			/* whatever is in the bottom text field will be considered the result
			 * either zero, or another number */
			result = botText;

		/* result will be null if the calculation failed otherwise it succeeded */
		if(result != null)
		{
			/* set the result to the top text field and reset the bottom text field */
			calculator.setBotText(Calculator.DIGIT_ZERO);
			calculator.setTopText(result);
			infixTokens.clear();
			infixTokens.add(result);
		}
		else
			clearAction();
		
		justClickedEQ = true;
	}
	
	/* handles clicked decimal button */
	protected void decimalAction()
	{
		/* check if the user already has a decimal in the current input
		 * cannot have multiple decimals in an operand */
		if(!isDecimalUsed())
		{
			if(justClickedEQ)
				clearAction();
			
			/* append the decimal to the bottom text field */
			String value = calculator.getBotText() + Calculator.DECIMAL;
			calculator.setBotText(value);
			justClickedEQ = false;
		}
	}
	
	/* handles clicked NEG button */
	protected void negationAction()
	{
		/* check if the bottom text field is zero
		 * cannot have a negative zero */
		if(!isBotZero())
		{
			String botText = calculator.getBotText();
			/* if the value is already negative, then remove the negative sign */
			if(isNegative())
			{
				String newValue = botText.substring(1);
				calculator.setBotText(newValue);
			}
			/* add the negative sign to the front of the bottom text field */
			else
			{
				String newValue = Calculator.SUB_OPERATOR + calculator.getBotText();
				calculator.setBotText(newValue);
			}
			justClickedEQ = false;
		}
	}
	
	/* handles clicked clear button */
	protected void clearAction()
	{
		/* reset all text fields and infix tokens list */
		infixTokens.clear();
		calculator.setBotText(Calculator.DIGIT_ZERO);
		calculator.setTopText(new String());
		justClickedEQ = false;
		parenOffset = 0;
	} 
	
	/* 
	 * 
	 * 
	 * HELPER METHODS BELOW 
	 * 
	 * 
	 * */
	
	/* returns true only if the last token of the infixTokens list is a right parentheses */
	private boolean isLastTokenRP()
	{
		if(!infixTokens.isEmpty())
			if(infixTokens.getLast().equals(Calculator.RIGHT_PAREN))
				return true;
		return false;
	}
	
	/* returns true if the bottom text field is a negative number */
	private boolean isNegative()
	{
		String botText = calculator.getBotText();
		if(botText.contains(Calculator.SUB_OPERATOR))
			return true;
		return false;
	}
	
	/* returns true only if the bottom text field is currently set to zero */
	private boolean isBotZero()
	{
		String botText = calculator.getBotText();
		if(botText.equals(Calculator.DIGIT_ZERO))
			return true;
		return false;
	}
	
	/* returns true only if the last token of the infixTokens list is an operator AND the bottom text field is currently set to zero */
	private boolean isOperatorLocked()
	{
		if(isDanglingOperator() && isBotZero())
			return true;
		return false;
	}
	
	/* returns true only if the bottom text field contains a decimal */
	private boolean isDecimalUsed()
	{
		String botText = calculator.getBotText();
		if(botText.contains(Calculator.DECIMAL))
			return true;
		return false;
	}
	
	/* returns true only if the last character of the bottom text field is a decimal */
	private boolean isDanglingDecimal()
	{
		String botText = calculator.getBotText();
		if(!botText.isEmpty())
		{
			String lastChar = botText.substring(botText.length()-1);
			if(lastChar.equals(Calculator.DECIMAL))
				return true;
		}
		return false;
	}
	
	/* returns true only if the last token of infixTokens is an operator */
	private boolean isDanglingOperator()
	{
		if(infixTokens.size() > 0)
		{
			String lastToken = infixTokens.getLast();
			switch(lastToken)
			{
				case Calculator.ADD_OPERATOR: 
					return true;
				case Calculator.SUB_OPERATOR:
					return true;
				case Calculator.MUL_OPERATOR:
					return true;
				case Calculator.DIV_OPERATOR:
					return true;
				case Calculator.EXP_OPERATOR:
					return true;	
			}
		}
		return false;
	}
	
	/* if the last character of the bottom text field is a decimal, remove it */
	private void removeDanglingDecimal()
	{
		if(isDanglingDecimal())
		{
			String botText = calculator.getBotText();
			botText = botText.substring(0, botText.length()-1);
			calculator.setBotText(botText);
		}		
	}
	
	/* return the last token of the infixTokens list */
	private String getLastInfixToken()
	{
		String last = new String();
		if(!infixTokens.isEmpty())
			last = infixTokens.getLast();
		return last;
	}
	
	/* recalculates the parentheses offset
	 * only used after removing parentheses of the infix expression */
	private int recalcParenOffset()
	{
		int parenOffset = 0;
		for(String str : infixTokens)
		{
			if(str.equals(Calculator.LEFT_PAREN))
				parenOffset++;
			if(str.equals(Calculator.RIGHT_PAREN))
				parenOffset--;
		}
		this.parenOffset = parenOffset;
		return parenOffset;
	}
	
	/* this method is only used given that the last token of the list is a right parentheses
	 * this method will find its concurrent left parentheses and remove everything after it */
	protected LinkedList<String> editParenContent(LinkedList<String> infixTokens)
	{
		int index = infixTokens.size()-1;
		int count = 1;
		
		/* when count reaches zero we are at the index of the correct left parentheses */
		while(count > 0)
		{
			String token = infixTokens.get(--index);
			if(token.equals("("))
				count--;
			else if(token.equals(")"))
				count++;
		}
		int size = infixTokens.size();
		/* remove everything after the index */
		for(int i = ++index; i < size; i++)
			infixTokens.removeLast();
		return infixTokens;
	}
	
	/* FUNCTION OVERLOADED FOR STRING */
	/* this method is only used given that the last character of the String is a right parentheses
	 * this method will find its concurrent left parentheses and remove everything after it */
	protected String editParenContent(String infixTokens)
	{
		int index = infixTokens.length()-1;
		int count = 1;
		
		/* when count reaches zero we are at the index of the correct left parentheses */
		while(count > 0)
		{
			Character token = infixTokens.charAt(--index);
			if(token == '(')
				count--;
			else if(token == ')')
				count++;
		}
		/* remove everything after the index */
		String result = infixTokens.substring(0, index+1);
		return result;
	}
	
	/* validates the parentheses of an infix expression to be passed into execution by removing any invalid left parentheses.
	 * NOTE that there cannot be invalid right parentheses due to the restrictions of the parentheses offset */
	protected LinkedList<String> validateParentheses(LinkedList<String> infixTokens)
	{
		/* stores the index of previously validated left parentheses */
		Set<Integer> validParenIndex = new HashSet<>();
		for(int outterIndex = 0; outterIndex < infixTokens.size(); outterIndex++)
		{
			String token1 = infixTokens.get(outterIndex);
			
			/* if we come accross a left parentheses that is not validated then it must be invalid - remove it */
			if(token1.equals("(") && !validParenIndex.contains(outterIndex))
				infixTokens.remove(outterIndex);
			/* if we come accross a right parentheses, find its concurrent left parentheses and add the index to the set */
			else if(token1.equals(")"))
			{
				int count = 1;
				int innerIndex = outterIndex;
				/* when count reaches zero we are at the index of the correct left parentheses */
				while(count > 0)
				{
					String token2 = infixTokens.get(--innerIndex);
					if(token2.equals("("))
						count--;
					else if(token2.equals(")"))
						count++;
				}
				/* add the index to the set */
				validParenIndex.add(innerIndex);
			}
		}
		recalcParenOffset();
		return infixTokens;
	}
	
	/* Assigns an integer value for order of precedence of operators and returns the value */
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
	@SuppressWarnings("unused")
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
	
	/* Converts a string to a float */
	private Float stringToFloat(String operand)
	{
		float value;
		try 
		{
			value = Float.parseFloat(operand);
		}
		catch (NumberFormatException e) 
		{
			return Float.NaN;
		}
		return value;
	}
}
