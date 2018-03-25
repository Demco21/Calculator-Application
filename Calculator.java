import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private static final Calculator instance = new Calculator();
	private static final Controller controller = new Controller();
	static final String ADD_OPERATOR = "+";
	static final String SUB_OPERATOR = "-";
	static final String MUL_OPERATOR = "*";
	static final String DIV_OPERATOR = "/";
	static final String EXP_OPERATOR = "^";
	static final String DIGIT_ZERO = "0";
	static final String DIGIT_ONE = "1";
	static final String DIGIT_TWO = "2";
	static final String DIGIT_THREE = "3";
	static final String DIGIT_FOUR = "4";
	static final String DIGIT_FIVE = "5";
	static final String DIGIT_SIX = "6";
	static final String DIGIT_SEVEN = "7";
	static final String DIGIT_EIGHT = "8";
	static final String DIGIT_NINE = "9";
	static final String LEFT_PAREN = "(";
	static final String RIGHT_PAREN = ")";
	static final String EQUALS = "=";
	static final String DECIMAL = ".";
	static final String CLEAR = "C";
	static final String NEGATION = "NEG";
	static final String FONT = "Tahoma";
	static final String TITLE = "Calculator";

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
				controller.operandAction(DIGIT_ZERO);
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
				controller.operandAction(DIGIT_ONE);
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
				controller.operandAction(DIGIT_TWO);
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
				controller.operandAction(DIGIT_THREE);
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
				controller.operandAction(DIGIT_FOUR);
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
				controller.operandAction(DIGIT_FIVE);
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
				controller.operandAction(DIGIT_SIX);
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
				controller.operandAction(DIGIT_SEVEN);
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
				controller.operandAction(DIGIT_EIGHT);
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
				controller.operandAction(DIGIT_NINE);
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
				controller.operatorAction(button_SUB.getText());
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
				controller.operatorAction(button_ADD.getText());
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
				controller.operatorAction(button_DIV.getText());
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
				controller.operatorAction(button_MUL.getText());
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
				controller.operatorAction(button_EXP.getText());
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
				controller.leftParenAction();
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
				controller.rightParenAction();
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
				controller.equalsAction();
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
				controller.decimalAction();
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
				controller.clearAction();
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
				controller.negationAction();
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
	
	/* accessors and modifiers for text fields */
	protected void setBotText(String text)
	{
		botTextField.setText(text);
	}
	
	protected void setTopText(String text)
	{
		topTextField.setText(text);
	}
	
	protected String getBotText()
	{
		return botTextField.getText();
	}
	
	protected String getTopText()
	{
		return topTextField.getText();
	}
	
}