import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Calculator
{
	private JFrame calculatorFrame;
	private JTextField botTextField;
	private JTextField topTextField;
	private LinkedList<String> infixTokens = new LinkedList<String>();
	private Controller controller = new Controller();
	private static Calculator instance = new Calculator();
	private int parenOffset = 0;
	private Boolean botIsZero = true;
	private Boolean justClickedEQ = false;
	private Boolean justClickedRP = false;
	private Boolean isNEG = false;
	private final String ADD_OPERATOR = "+";
	private final String SUB_OPERATOR = "-";
	private final String MUL_OPERATOR = "*";
	private final String DIV_OPERATOR = "/";
	private final String EXP_OPERATOR = "^";
	private final String DIGIT_ZERO = "0";
	private final String DIGIT_ONE = "1";
	private final String DIGIT_TWO = "2";
	private final String DIGIT_THREE = "3";
	private final String DIGIT_FOUR = "4";
	private final String DIGIT_FIVE = "5";
	private final String DIGIT_SIX = "6";
	private final String DIGIT_SEVEN = "7";
	private final String DIGIT_EIGHT = "8";
	private final String DIGIT_NINE = "9";
	private final String LEFT_PAREN = "(";
	private final String RIGHT_PAREN = ")";
	private final String EQUALS = "=";
	private final String DECIMAL = ".";
	private final String CLEAR = "C";
	private final String NEGATION = "NEG";
	private final String FONT = "Tahoma";
	private final String TITLE = "Calculator";
	

	/* singleton pattern */
	protected static Calculator getInstance()
	{
		return instance;
	}
	
	/* constructor */
	private Calculator(){}
	
	/* Initialize the contents of the frame. */
	protected void initialize() 
	{
		calculatorFrame = new JFrame();
		calculatorFrame.setTitle(TITLE);
		calculatorFrame.getContentPane().setBackground(SystemColor.menu);
		calculatorFrame.setBounds(100, 100, 255, 477);
		calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculatorFrame.getContentPane().setLayout(null);	
		
		JButton button_0 = new JButton(DIGIT_ZERO);
		button_0.setBackground(SystemColor.scrollbar);
		button_0.setForeground(Color.BLACK);
		button_0.setFont(new Font(FONT, Font.BOLD, 20));
		button_0.setBounds(65, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_0);
		button_0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_ZERO);
			}
		});
		
		JButton button_1 = new JButton(DIGIT_ONE);
		button_1.setBackground(SystemColor.scrollbar);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font(FONT, Font.BOLD, 20));
		button_1.setBounds(10, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_ONE);
			}
		});
		
		JButton button_2 = new JButton(DIGIT_TWO);
		button_2.setBackground(SystemColor.scrollbar);
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font(FONT, Font.BOLD, 20));
		button_2.setBounds(65, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_TWO);
			}
		});
		
		JButton button_3 = new JButton(DIGIT_THREE);
		button_3.setBackground(SystemColor.scrollbar);
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font(FONT, Font.BOLD, 20));
		button_3.setBounds(120, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_3);
		button_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_THREE);
			}
		});
		
		JButton button_4 = new JButton(DIGIT_FOUR);
		button_4.setForeground(Color.BLACK);
		button_4.setBackground(SystemColor.scrollbar);
		button_4.setFont(new Font(FONT, Font.BOLD, 20));
		button_4.setBounds(10, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_4);
		button_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_FOUR);
			}
		});
		
		JButton button_5 = new JButton(DIGIT_FIVE);
		button_5.setBackground(SystemColor.scrollbar);
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font(FONT, Font.BOLD, 20));
		button_5.setBounds(65, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_5);
		button_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_FIVE);
			}
		});
		
		JButton button_6 = new JButton(DIGIT_SIX);
		button_6.setBackground(SystemColor.scrollbar);
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font(FONT, Font.BOLD, 20));
		button_6.setBounds(120, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_6);
		button_6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_SIX);
			}
		});
		
		JButton button_7 = new JButton(DIGIT_SEVEN);
		button_7.setBackground(SystemColor.scrollbar);
		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font(FONT, Font.BOLD, 20));
		button_7.setBounds(10, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_7);
		button_7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_SEVEN);
			}
		});
		
		JButton button_8 = new JButton(DIGIT_EIGHT);
		button_8.setBackground(SystemColor.scrollbar);
		button_8.setForeground(Color.BLACK);
		button_8.setFont(new Font(FONT, Font.BOLD, 20));
		button_8.setBounds(65, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_8);
		button_8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_EIGHT);
			}
		});
		
		JButton button_9 = new JButton(DIGIT_NINE);
		button_9.setBackground(SystemColor.scrollbar);
		button_9.setForeground(Color.BLACK);
		button_9.setFont(new Font(FONT, Font.BOLD, 20));
		button_9.setBounds(120, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_9);
		button_9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operandAction(DIGIT_NINE);
			}
		});
		
		JButton button_SUB = new JButton(SUB_OPERATOR);
		button_SUB.setBackground(SystemColor.scrollbar);
		button_SUB.setForeground(Color.BLACK);
		button_SUB.setFont(new Font(FONT, Font.BOLD, 20));
		button_SUB.setBounds(175, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_SUB);
		button_SUB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operatorAction(button_SUB.getText());
			}
		});
		
		JButton button_ADD = new JButton(ADD_OPERATOR);
		button_ADD.setBackground(SystemColor.scrollbar);
		button_ADD.setForeground(Color.BLACK);
		button_ADD.setFont(new Font(FONT, Font.BOLD, 20));
		button_ADD.setBounds(175, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_ADD);
		button_ADD.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operatorAction(button_ADD.getText());
			}
		});
		
		JButton button_DIV = new JButton(DIV_OPERATOR);
		button_DIV.setBackground(SystemColor.scrollbar);
		button_DIV.setForeground(Color.BLACK);
		button_DIV.setFont(new Font(FONT, Font.BOLD, 20));
		button_DIV.setBounds(175, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_DIV);
		button_DIV.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operatorAction(button_DIV.getText());
			}
		});
		
		JButton button_MUL = new JButton(MUL_OPERATOR);
		button_MUL.setBackground(SystemColor.scrollbar);
		button_MUL.setForeground(Color.BLACK);
		button_MUL.setFont(new Font(FONT, Font.BOLD, 20));
		button_MUL.setBounds(175, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_MUL);
		button_MUL.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{		
				operatorAction(button_MUL.getText());
			}
		});
		
		JButton button_EXP = new JButton(EXP_OPERATOR);
		button_EXP.setBackground(SystemColor.scrollbar);
		button_EXP.setForeground(Color.BLACK);
		button_EXP.setFont(new Font(FONT, Font.BOLD, 20));
		button_EXP.setBounds(120, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_EXP);
		button_EXP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				operatorAction(button_EXP.getText());
			}
		});
		
		JButton button_LP = new JButton(LEFT_PAREN);
		button_LP.setBackground(SystemColor.scrollbar);
		button_LP.setForeground(Color.BLACK);
		button_LP.setFont(new Font(FONT, Font.BOLD, 20));
		button_LP.setBounds(10, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_LP);
		button_LP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				leftParenAction();
			}
		});
		
		JButton button_RP = new JButton(RIGHT_PAREN);
		button_RP.setBackground(SystemColor.scrollbar);
		button_RP.setForeground(Color.BLACK);
		button_RP.setFont(new Font(FONT, Font.BOLD, 20));
		button_RP.setBounds(65, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_RP);
		button_RP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				rightParenAction();
			}
		});
		
		JButton button_EQ = new JButton(EQUALS);
		button_EQ.setBackground(SystemColor.scrollbar);
		button_EQ.setForeground(Color.BLACK);
		button_EQ.setFont(new Font(FONT, Font.BOLD, 20));
		button_EQ.setBounds(175, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_EQ);
		button_EQ.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				equalsAction();
			}
		});
		
		JButton button_DEC = new JButton(DECIMAL);
		button_DEC.setBackground(SystemColor.scrollbar);
		button_DEC.setForeground(Color.BLACK);
		button_DEC.setFont(new Font(FONT, Font.BOLD, 20));
		button_DEC.setBounds(120, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_DEC);
		button_DEC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				decimalAction();
			}
		});
		
		JButton button_C = new JButton(CLEAR);
		button_C.setBackground(SystemColor.scrollbar);
		button_C.setForeground(Color.BLACK);
		button_C.setFont(new Font(FONT, Font.BOLD, 20));
		button_C.setBounds(175, 130, 54, 49);
		calculatorFrame.getContentPane().add(button_C);
		button_C.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				clear();
			}
		});
		
		JButton button_NEG = new JButton(NEGATION);
		button_NEG.setForeground(Color.BLACK);
		button_NEG.setFont(new Font(FONT, Font.BOLD, 10));
		button_NEG.setBackground(SystemColor.scrollbar);
		button_NEG.setBounds(10, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_NEG);
		button_NEG.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				negationAction();
			}
		});
		
		botTextField = new JTextField();
		botTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		botTextField.setBackground(SystemColor.menu);
		botTextField.setForeground(Color.BLACK);
		botTextField.setFont(new Font(FONT, Font.BOLD, 35));
		botTextField.setEditable(false);
		botTextField.setBounds(8, 58, 221, 50);
		calculatorFrame.getContentPane().add(botTextField);
		botTextField.setColumns(10);
		botTextField.setText(DIGIT_ZERO);
		
		topTextField = new JTextField();
		topTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		topTextField.setForeground(Color.BLACK);
		topTextField.setFont(new Font(FONT, Font.PLAIN, 15));
		topTextField.setEditable(false);
		topTextField.setColumns(10);
		topTextField.setBackground(SystemColor.menu);
		topTextField.setBounds(8, 8, 221, 50);
		calculatorFrame.getContentPane().add(topTextField);
		
		calculatorFrame.setVisible(true);
	}
	
	private void operandAction(String operand)
	{
		if(justClickedEQ)
			clear();
		if(operand.equals(DIGIT_ZERO))
		{
			if(!botIsZero)
				botTextField.setText(botTextField.getText() + DIGIT_ZERO);
		}
		else if(justClickedRP)
		{
			infixTokens = controller.editParenContent(infixTokens);
			String topText = topTextField.getText();
			String newTopText = controller.editParenContent(topText);
			topTextField.setText(newTopText);
			botTextField.setText(operand);
			recalcParenOffset();
		}
		else if(botIsZero)
		{
			botTextField.setText(operand);
			botIsZero = false;
		}
		else
			botTextField.setText(botTextField.getText() + operand);
		justClickedEQ = false;
		justClickedRP = false;
	}
	
	private void operatorAction(String operator)
	{
		if(!operatorLocked())
		{
			String topText = new String();
			String botText = botTextField.getText();

			if(botIsZero && !getLastCharTop().equals(LEFT_PAREN))
				topText = topTextField.getText() + DIGIT_ZERO + operator;
			if(botIsZero && !topTextField.getText().isEmpty() && !getLastCharTop().equals(LEFT_PAREN))
				topText = topTextField.getText() + operator;
			else
			{
				topText = topTextField.getText() + botTextField.getText() + operator;
				infixTokens.add(botText);
			}

			infixTokens.add(operator);
			topTextField.setText(topText);
			botTextField.setText(DIGIT_ZERO);
			botIsZero = true;
			justClickedEQ = false;
			justClickedRP = false;
			isNEG = false;
		}
		else
		{
			String topText = topTextField.getText();
			int index = topText.length()-1;
			String newTopText = topText.substring(0, index) + operator;
			infixTokens.removeLast();
			infixTokens.add(operator);
			topTextField.setText(newTopText);
		}
	}
	
	private void leftParenAction()
	{
		if(justClickedRP)
		{
			infixTokens = controller.editParenContent(infixTokens);
			String topText = topTextField.getText();
			String newTopText = controller.editParenContent(topText);
			topTextField.setText(newTopText);
			botTextField.setText(DIGIT_ZERO);
			parenOffset = recalcParenOffset();
		}
		else if(justClickedEQ)
		{
			clear();
			topTextField.setText(LEFT_PAREN);
			infixTokens.add(LEFT_PAREN);
			parenOffset++;	
		}
		else
		{
			String value = topTextField.getText() + LEFT_PAREN;
			infixTokens.add(LEFT_PAREN);
			topTextField.setText(value);
			parenOffset++;
		}
		justClickedEQ = false;
		justClickedRP = false;
		isNEG = false;
	}
	
	private void rightParenAction()
	{
		if(parenOffset > 0)
		{
			String botText = new String();
			String topText = topTextField.getText();
			
			if(!getLastCharTop().equals(RIGHT_PAREN))
			{
				botText = botTextField.getText();
				infixTokens.add(botText);
			}
			
			String newTopText = topText + botText + RIGHT_PAREN;
			infixTokens.add(RIGHT_PAREN);
			topTextField.setText(newTopText);
			botTextField.setText(DIGIT_ZERO);
			parenOffset--;
			botIsZero = true;
			justClickedEQ = false;
			justClickedRP = true;
			isNEG = false;
		}
	}
	
	private void equalsAction()
	{
		String result = null;
		String topText = topTextField.getText();
		String botText = botTextField.getText();
		
		if(parenOffset > 0)
			infixTokens = controller.validateParentheses(infixTokens);
		if(isDanglingOperator())
		{
			infixTokens.add(botText);
			result = controller.execute(infixTokens);
		}
		else if(justClickedRP)
			result = controller.execute(infixTokens);
		else if(topText.isEmpty() || justClickedEQ)
			result = botText;

		if(result != null)
		{
			botTextField.setText(DIGIT_ZERO);
			topTextField.setText(result);
			infixTokens.clear();
			infixTokens.add(result);
			botIsZero = true;
		}
		else
			clear();
		
		justClickedEQ = true;
		justClickedRP = false;
		isNEG = false;
	}
	
	private void decimalAction()
	{
		if(isValidDecimal())
		{
			if(justClickedEQ)
				clear();
			String value = botTextField.getText() + DECIMAL;
			botTextField.setText(value);
			botIsZero = false;
			justClickedEQ = false;
			justClickedRP = false;
			isNEG = false;
		}
	}
	
	private void negationAction()
	{
		if(!botIsZero)
		{
			String text = botTextField.getText();
			if(!isNEG)
			{
				String newValue = SUB_OPERATOR + botTextField.getText();
				botTextField.setText(newValue);
				isNEG = true;
			}
			else
			{
				String newValue = text.substring(1);
				botTextField.setText(newValue);
				isNEG = false;
			}
			justClickedEQ = false;
			justClickedRP = false;
		}
	}
	
	private boolean isValidDecimal()
	{
		String botText = botTextField.getText();
		if(botText.contains(DECIMAL))
				return false;
		return true;
	}
	
	private boolean operatorLocked()
	{
		if(isDanglingOperator() && botIsZero || getLastCharBot().equals(DECIMAL))
			return true;
		return false;
	}
	
	private boolean isDanglingOperator()
	{
		if(infixTokens.size() > 0)
		{
			String lastToken = infixTokens.getLast();
			switch(lastToken)
			{
				case ADD_OPERATOR: 
					return true;
				case SUB_OPERATOR:
					return true;
				case MUL_OPERATOR:
					return true;
				case DIV_OPERATOR:
					return true;
				case EXP_OPERATOR:
					return true;	
			}
		}
		return false;
	}
	
	private String getLastCharTop()
	{
		String topText = topTextField.getText();
		String retVal = new String();
		if(topText.length() > 0)
			retVal = topText.substring(topText.length()-1);
		return retVal;
	}
	
	private String getLastCharBot()
	{
		String botText = botTextField.getText();
		return botText.substring(botText.length()-1);
	}
	
	private void clear()
	{
		infixTokens.clear();
		botTextField.setText(DIGIT_ZERO);
		topTextField.setText(new String());
		botIsZero = true;
		justClickedEQ = false;
		justClickedRP = false;
		isNEG = false;
		parenOffset = 0;
	}
	
	private int recalcParenOffset()
	{
		int parenOffset = 0;
		for(String str : infixTokens)
		{
			if(str.equals(LEFT_PAREN))
				parenOffset++;
			if(str.equals(RIGHT_PAREN))
				parenOffset--;
		}
		this.parenOffset = parenOffset;
		return parenOffset;
	}
}