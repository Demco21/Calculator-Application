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
	private String[] operators = {"-", "+", "/", "*", "^"};
	
	/* singleton pattern */
	public static Calculator getInstance()
	{
		return instance;
	}
	
	/* constructor */
	private Calculator(){}
	
	/* Initialize the contents of the frame. */
	public void initialize() 
	{
		calculatorFrame = new JFrame();
		calculatorFrame.setTitle("Calculator");
		calculatorFrame.getContentPane().setBackground(SystemColor.menu);
		calculatorFrame.setBounds(100, 100, 255, 477);
		calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculatorFrame.getContentPane().setLayout(null);	
		
		JButton button_0 = new JButton("0");
		button_0.setBackground(SystemColor.scrollbar);
		button_0.setForeground(Color.BLACK);
		button_0.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_0.setBounds(65, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_0);
		button_0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!botIsZero)
				{
					String value = botTextField.getText() + button_0.getText();
					botTextField.setText(value);
					justClickedEQ = false;
					justClickedRP = false;
				}
			}
		});
		
		JButton button_1 = new JButton("1");
		button_1.setBackground(SystemColor.scrollbar);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_1.setBounds(10, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_1.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_1.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_2 = new JButton("2");
		button_2.setBackground(SystemColor.scrollbar);
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_2.setBounds(65, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_2.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_2.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_3 = new JButton("3");
		button_3.setBackground(SystemColor.scrollbar);
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_3.setBounds(120, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_3);
		button_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_3.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_3.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_4 = new JButton("4");
		button_4.setForeground(Color.BLACK);
		button_4.setBackground(SystemColor.scrollbar);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_4.setBounds(10, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_4);
		button_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_4.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_4.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_5 = new JButton("5");
		button_5.setBackground(SystemColor.scrollbar);
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_5.setBounds(65, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_5);
		button_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_5.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_5.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_6 = new JButton("6");
		button_6.setBackground(SystemColor.scrollbar);
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_6.setBounds(120, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_6);
		button_6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_6.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_6.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_7 = new JButton("7");
		button_7.setBackground(SystemColor.scrollbar);
		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_7.setBounds(10, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_7);
		button_7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_7.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_7.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_8 = new JButton("8");
		button_8.setBackground(SystemColor.scrollbar);
		button_8.setForeground(Color.BLACK);
		button_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_8.setBounds(65, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_8);
		button_8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_8.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_8.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_9 = new JButton("9");
		button_9.setBackground(SystemColor.scrollbar);
		button_9.setForeground(Color.BLACK);
		button_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_9.setBounds(120, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_9);
		button_9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = new String();
				if(justClickedEQ)
					clear();
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_1.getText());
					recalcParenOffset();
				}
				if(botIsZero)
				{
					value = button_9.getText();
					botTextField.setText(value);
					botIsZero = false;
				}
				else
				{
					value = botTextField.getText() + button_9.getText();
					botTextField.setText(value);
				}
				justClickedEQ = false;
				justClickedRP = false;
			}
		});
		
		JButton button_SUB = new JButton("-");
		button_SUB.setBackground(SystemColor.scrollbar);
		button_SUB.setForeground(Color.BLACK);
		button_SUB.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_SUB.setBounds(175, 280, 54, 49);
		calculatorFrame.getContentPane().add(button_SUB);
		button_SUB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!operatorLocked())
				{
					String topText = new String();
					String botText = botTextField.getText();

					if(botIsZero && getLastCharTop() != '(')
						topText = topTextField.getText() + button_0.getText() + button_SUB.getText();
					if(botIsZero && !topTextField.getText().isEmpty() && getLastCharTop() != '(')
						topText = topTextField.getText() + button_SUB.getText();
					else
					{
						topText = topTextField.getText() + botTextField.getText() + button_SUB.getText();
						infixTokens.add(botText);
					}

					infixTokens.add(button_SUB.getText());
					topTextField.setText(topText);
					botTextField.setText(button_0.getText());
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
				else
				{
					String topText = topTextField.getText();
					int index = topText.length()-1;
					String newTopText = topText.substring(0, index) + button_SUB.getText();
					infixTokens.removeLast();
					infixTokens.add(button_SUB.getText());
					topTextField.setText(newTopText);
				}
			}
		});
		
		JButton button_ADD = new JButton("+");
		button_ADD.setBackground(SystemColor.scrollbar);
		button_ADD.setForeground(Color.BLACK);
		button_ADD.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_ADD.setBounds(175, 330, 54, 49);
		calculatorFrame.getContentPane().add(button_ADD);
		button_ADD.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!operatorLocked())
				{
					String topText = new String();
					String botText = botTextField.getText();

					if(botIsZero && getLastCharTop() != '(')
						topText = topTextField.getText() + button_0.getText() + button_ADD.getText();
					if(botIsZero && !topTextField.getText().isEmpty() && getLastCharTop() != '(')
						topText = topTextField.getText() + button_ADD.getText();
					else
					{
						topText = topTextField.getText() + botTextField.getText() + button_ADD.getText();
						infixTokens.add(botText);
					}

					infixTokens.add(button_ADD.getText());
					topTextField.setText(topText);
					botTextField.setText(button_0.getText());
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
				else
				{
					String topText = topTextField.getText();
					int index = topText.length()-1;
					String newTopText = topText.substring(0, index) + button_ADD.getText();
					infixTokens.removeLast();
					infixTokens.add(button_ADD.getText());
					topTextField.setText(newTopText);
				}
			}
		});
		
		JButton button_DIV = new JButton("/");
		button_DIV.setBackground(SystemColor.scrollbar);
		button_DIV.setForeground(Color.BLACK);
		button_DIV.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_DIV.setBounds(175, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_DIV);
		button_DIV.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!operatorLocked())
				{
					String topText = new String();
					String botText = botTextField.getText();

					if(botIsZero && getLastCharTop() != '(')
						topText = topTextField.getText() + button_0.getText() + button_DIV.getText();
					if(botIsZero && !topTextField.getText().isEmpty() && getLastCharTop() != '(')
						topText = topTextField.getText() + button_DIV.getText();
					else
					{
						topText = topTextField.getText() + botTextField.getText() + button_DIV.getText();
						infixTokens.add(botText);
					}

					infixTokens.add(button_DIV.getText());
					topTextField.setText(topText);
					botTextField.setText(button_0.getText());
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
				else
				{
					String topText = topTextField.getText();
					int index = topText.length()-1;
					String newTopText = topText.substring(0, index) + button_DIV.getText();
					infixTokens.removeLast();
					infixTokens.add(button_DIV.getText());
					topTextField.setText(newTopText);
				}
			}
		});
		
		JButton button_MUL = new JButton("*");
		button_MUL.setBackground(SystemColor.scrollbar);
		button_MUL.setForeground(Color.BLACK);
		button_MUL.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_MUL.setBounds(175, 230, 54, 49);
		calculatorFrame.getContentPane().add(button_MUL);
		button_MUL.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(!operatorLocked())
				{
					String topText = new String();
					String botText = botTextField.getText();

					if(botIsZero && getLastCharTop() != '(')
						topText = topTextField.getText() + button_0.getText() + button_MUL.getText();
					if(botIsZero && !topTextField.getText().isEmpty() && getLastCharTop() != '(')
						topText = topTextField.getText() + button_MUL.getText();
					else
					{
						topText = topTextField.getText() + botTextField.getText() + button_MUL.getText();
						infixTokens.add(botText);
					}

					infixTokens.add(button_MUL.getText());
					topTextField.setText(topText);
					botTextField.setText(button_0.getText());
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
				else
				{
					String topText = topTextField.getText();
					int index = topText.length()-1;
					String newTopText = topText.substring(0, index) + button_MUL.getText();
					infixTokens.removeLast();
					infixTokens.add(button_MUL.getText());
					topTextField.setText(newTopText);
				}
			}
		});
		
		JButton button_EXP = new JButton("^");
		button_EXP.setBackground(SystemColor.scrollbar);
		button_EXP.setForeground(Color.BLACK);
		button_EXP.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_EXP.setBounds(120, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_EXP);
		button_EXP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!operatorLocked())
				{
					String topText = new String();
					String botText = botTextField.getText();

					if(botIsZero && getLastCharTop() != '(')
						topText = topTextField.getText() + button_0.getText() + button_EXP.getText();
					if(botIsZero && !topTextField.getText().isEmpty() && getLastCharTop() != '(')
						topText = topTextField.getText() + button_EXP.getText();
					else
					{
						topText = topTextField.getText() + botTextField.getText() + button_EXP.getText();
						infixTokens.add(botText);
					}

					infixTokens.add(button_EXP.getText());
					topTextField.setText(topText);
					botTextField.setText(button_0.getText());
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
				else
				{
					String topText = topTextField.getText();
					int index = topText.length()-1;
					String newTopText = topText.substring(0, index) + button_EXP.getText();
					infixTokens.removeLast();
					infixTokens.add(button_EXP.getText());
					topTextField.setText(newTopText);
				}
			}
		});
		
		JButton button_LP = new JButton("(");
		button_LP.setBackground(SystemColor.scrollbar);
		button_LP.setForeground(Color.BLACK);
		button_LP.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_LP.setBounds(10, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_LP);
		button_LP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(justClickedRP)
				{
					infixTokens = controller.editParenContent(infixTokens);
					String topText = topTextField.getText();
					String newTopText = controller.editParenContent(topText);
					topTextField.setText(newTopText);
					botTextField.setText(button_0.getText());
					parenOffset = recalcParenOffset();
				}
				else if(justClickedEQ)
				{
					clear();
					topTextField.setText(button_LP.getText());
					infixTokens.add(button_LP.getText());
					parenOffset++;	
				}
				else
				{
					String value = topTextField.getText() + button_LP.getText();
					infixTokens.add(button_LP.getText());
					topTextField.setText(value);
					parenOffset++;
				}
				justClickedEQ = false;
				justClickedRP = false;
				isNEG = false;
			}
		});
		
		JButton button_RP = new JButton(")");
		button_RP.setBackground(SystemColor.scrollbar);
		button_RP.setForeground(Color.BLACK);
		button_RP.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_RP.setBounds(65, 180, 54, 49);
		calculatorFrame.getContentPane().add(button_RP);
		button_RP.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(parenOffset > 0)
				{
					String botText = new String();
					String topText = topTextField.getText();
					
					if(getLastCharTop() != ')')
					{
						botText = botTextField.getText();
						infixTokens.add(botText);
					}
					
					String newTopText = topText + botText + button_RP.getText();
					infixTokens.add(button_RP.getText());
					topTextField.setText(newTopText);
					botTextField.setText(button_0.getText());
					parenOffset--;
					botIsZero = true;
					justClickedEQ = false;
					justClickedRP = true;
					isNEG = false;
				}
			}
		});
		
		JButton button_EQ = new JButton("=");
		button_EQ.setBackground(SystemColor.scrollbar);
		button_EQ.setForeground(Color.BLACK);
		button_EQ.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_EQ.setBounds(175, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_EQ);
		button_EQ.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String result = null;
				String botText = botTextField.getText();
				String topText = topTextField.getText();
				
				if(parenOffset > 0)
					infixTokens = controller.validateParentheses(infixTokens);
				if(danglingOperator())
				{
					infixTokens.add(botText);
					result = controller.execute(infixTokens);
				}
				else if(justClickedRP)
					result = controller.execute(infixTokens);
				else if(topText.isEmpty())
					result = botText;

				if(result != null)
				{
					botTextField.setText(button_0.getText());
					topTextField.setText(result);
					infixTokens.clear();
					infixTokens.add(result);
					botIsZero = true;
				}
				else
				{
					infixTokens.clear();
					botTextField.setText(button_0.getText());
					topTextField.setText(new String());
					botIsZero = true;
				}
				justClickedEQ = true;
				justClickedRP = false;
				isNEG = false;
			}
		});
		
		JButton button_DEC = new JButton(".");
		button_DEC.setBackground(SystemColor.scrollbar);
		button_DEC.setForeground(Color.BLACK);
		button_DEC.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_DEC.setBounds(120, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_DEC);
		button_DEC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(isValidDecimal())
				{
					if(justClickedEQ)
						clear();
					String value = botTextField.getText() + button_DEC.getText();
					botTextField.setText(value);
					botIsZero = false;
					justClickedEQ = false;
					justClickedRP = false;
					isNEG = false;
				}
			}
		});
		
		JButton button_C = new JButton("C");
		button_C.setBackground(SystemColor.scrollbar);
		button_C.setForeground(Color.BLACK);
		button_C.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_C.setBounds(175, 130, 54, 49);
		calculatorFrame.getContentPane().add(button_C);
		button_C.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				clear();
			}
		});
		
		JButton button_NEG = new JButton("NEG");
		button_NEG.setForeground(Color.BLACK);
		button_NEG.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_NEG.setBackground(SystemColor.scrollbar);
		button_NEG.setBounds(10, 380, 54, 49);
		calculatorFrame.getContentPane().add(button_NEG);
		button_NEG.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!botIsZero)
				{
					String text = botTextField.getText();
					if(!isNEG)
					{
						String newValue = button_SUB.getText() + botTextField.getText();
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
		});
		
		botTextField = new JTextField();
		botTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		botTextField.setBackground(SystemColor.menu);
		botTextField.setForeground(Color.BLACK);
		botTextField.setFont(new Font("Tahoma", Font.BOLD, 35));
		botTextField.setEditable(false);
		botTextField.setBounds(8, 58, 221, 50);
		calculatorFrame.getContentPane().add(botTextField);
		botTextField.setColumns(10);
		botTextField.setText(button_0.getText());
		
		topTextField = new JTextField();
		topTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		topTextField.setForeground(Color.BLACK);
		topTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		topTextField.setEditable(false);
		topTextField.setColumns(10);
		topTextField.setBackground(SystemColor.menu);
		topTextField.setBounds(8, 8, 221, 50);
		calculatorFrame.getContentPane().add(topTextField);
		
		calculatorFrame.setVisible(true);
	}
	
	private boolean isValidDecimal()
	{
		String text = botTextField.getText();
		for(int i = 0; i < text.length(); i++)
			if(text.charAt(i) == '.')
				return false;
		return true;
	}
	
	public boolean operatorLocked()
	{
		if(danglingOperator() && botIsZero || getLastCharBot() == '.')
			return true;
		return false;
	}
	
	public boolean danglingOperator()
	{
		if(infixTokens.size() > 0)
			if(matchesAny(infixTokens.getLast(), operators))
				return true;
		return false;
	}
	
	public boolean matchesAny(String string, String[] array)
	{
		for(int i = 0; i < array.length; i++)
			if(string.equals(array[i]))
				return true;
		return false;
	}
	
	public Character getLastCharTop()
	{
		Character ch = ' ';
		String topText = topTextField.getText();
		if(!topText.isEmpty())
			ch = topText.charAt(topText.length()-1);
		return ch;
	}
	
	public Character getLastCharBot()
	{
		Character ch = ' ';
		String botText = botTextField.getText();
		if(!botText.isEmpty())
			ch = botText.charAt(botText.length()-1);
		return ch;
	}
	
	public void clear()
	{
		infixTokens.clear();
		botTextField.setText("0");
		topTextField.setText(new String());
		botIsZero = true;
		justClickedEQ = false;
		justClickedRP = false;
		isNEG = false;
		parenOffset = 0;
	}
	
	public int recalcParenOffset()
	{
		int parenOffset = 0;
		for(String str : infixTokens)
		{
			if(str.equals("("))
				parenOffset++;
			if(str.equals(")"))
				parenOffset--;
		}
		this.parenOffset = parenOffset;
		return parenOffset;
	}
}




