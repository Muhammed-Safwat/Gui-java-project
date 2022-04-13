	/** this is description of method I use in code
	* Methods:
	* 1- private static void getDimension()
	* for taking matrix's dimensitions
	* 2- private static void setElements(double matrix [][], String title )
	* for filling matrix's elements
	* 3- private static void checkTextField (JTextField field [][] )
	* For setting spaced fields as zeors
	* 4- private void ChooseOperation () for choising an operation to be a
	* to the matrix aplied
	* 5- private void actionPerformed(ActionEvent e)
	* Output methods:
	* 6 - public void actionPerformed(ActionEvent e)
	* for setting buttons performance
	* 7- private static void showMatrix(double [][] matrix, String title )
	* for showing the matrix (matrix) with the title (title)
	* 8 - private static void matrixPlusMatrix ()
	* Do a plusing operation of matrix with other matrix
	* 9 - private static void matrixMinusMatrix ()
	* Do a subtracting operation of matrix and other matrix
	* 10 - private static void multiplyByMatrix ()
	* Do a multiplication operation between matrix and other matrix
	value
	* 11- private static void newMatrix ()
	* Enable the user to enter a new matrix and use the program's
	* features on it
	* 12- public static void main (String [] args)
	* For invoking the program
	*/
package finalproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class finalProject implements ActionListener{
	static JButton minusB, plusB, multiplyB,showMatrix,
	newMatrix , comfarm,next,
	b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdiv,bmul,bsub,badd,bdec,beq,bdel,
	bclr;
	static int col, row ,lastCol , lastRow ;
	static double myMatrix [][];
	static double tempMatrix [][];
	static JTextField inputField [][];//use for create field to write elemants of array
	static double ResultPramter_x , ResultPramter_y; //variable use for save root of equation
	static double b, a , c ;//paramter of equation
	static JPanel choosePanel [] ;
	static double aa =0, bb =0, result =0; //use in operation culculater
	static int operator = 0 ;// use in culc to chose operation in siwch statement
	static JLabel equ ,equ1 ,at1 , bt1 , ct1, u;
	static JTextField t , Q , at , bt , ct ; //t use in culculater , Q use to get solve of equation
	// at get a , bt get b ,ct get c
	finalProject(){
	choosePanel = new JPanel[20];//initialize panel that have button
	//set initial vule for col and row
	col = row = 0;
	myMatrix = new double [0][0];
	ChooseOperation();
	/*private void ChooseOperation () for choising an
	operation to be applied
	to the matrix and culclater */
	}
	private static void getDimension()
	{
	// for design panel which user set row and colums
	JTextField lField = new JTextField(15);
	lField.setFont(new Font(Font.SERIF,Font.BOLD,20));
	JTextField wField = new JTextField(15);
	wField.setFont(new Font(Font.SERIF,Font.BOLD,20));
	JPanel choosePanel [] = new JPanel [2];choosePanel[0] = new JPanel();
	choosePanel[1] = new JPanel();
	choosePanel[0].setBackground(new Color(150,200,150));
	choosePanel[0].add(new JLabel("Enter Dimensitions of matrix") ).setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[1].add(new JLabel("Rows:")).setFont(new
	Font(Font.SERIF,Font.BOLD,20));
	choosePanel[1].add(lField);
	choosePanel[1].add(Box.createGlue());
	choosePanel[1].add(new JLabel("Column:")).setFont(new
	Font(Font.SERIF,Font.BOLD,20));
	choosePanel[1].add(wField);
	result = JOptionPane.showConfirmDialog(null, choosePanel,
	null,JOptionPane.CANCEL_OPTION,
	JOptionPane.PLAIN_MESSAGE);
	//save last dimensions
	lastCol = col;
	lastRow = row;
	//ok option
	if(result == 0){
	if(wField.getText().equals(""))
	col = 0;
	else
	{
	if(isInt(wField.getText()))
	{
	col = Integer.parseInt(wField.getText());
	}
	else
	{
	JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	col = lastCol;
	row = lastRow;
	return;
	}
	if(isInt(lField.getText())){
	row = Integer.parseInt(lField.getText());
	}
	else
	{
	JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	col = lastCol;
	row = lastRow;return;
	}
	}
	if(col < 1 || row < 1)
	{
	JOptionPane.showConfirmDialog(null, " wrongdimensions","Error",JOptionPane.PLAIN_MESSAGE);
	col = lastCol;
	row = lastRow;
	}
	else
	{
	tempMatrix = myMatrix;
	myMatrix = new double [row][col];
	if(!setElements(myMatrix, "Fill new matrix"))
	//filling the new matrix
	{
	myMatrix = tempMatrix;
	}
	}
	}
	else if(result == 1)
	{
	col = lastCol;
	row = lastRow;
	}
	}//setting a matrix's elementis
	private static boolean setElements(double matrix [][],
	String title )
	{
	int temp, temp1; //temprature variable
	String tempString;
	JPanel choosePanel [] = new JPanel [row+2];
	choosePanel[0] = new JPanel();
	choosePanel[0].add(new Label(title ));
	choosePanel[choosePanel.length-1] = new JPanel();
	choosePanel[choosePanel.length-1].add(new Label("consider space field as zeros"));
	inputField = new JTextField
	[matrix.length][matrix[0].length];
	for(temp = 1; temp <= matrix.length; temp++)
	{
	choosePanel[temp] = new JPanel();for(temp1 = 0; temp1 < matrix[0].length; temp1++)
	{
	inputField [temp-1][temp1] = new JTextField(3);
	choosePanel[temp].add(inputField [temp-
	1][temp1]);
	if(temp1 < matrix[0].length -1)
	{
	choosePanel[temp].add(Box.createHorizontalStrut(15)); // aspacer
	}
	}//end col loop
	}//end row loop
	result = JOptionPane.showConfirmDialog(null, choosePanel,
	null, JOptionPane.OK_OPTION,
	JOptionPane.PLAIN_MESSAGE);
	if(result == 0)
	{
	checkTextField(inputField);
	for(temp = 0; temp < matrix.length; temp++)
	{
	for(temp1 = 0; temp1 < matrix[0].length; temp1++)
	{
	tempString = inputField[temp][temp1].getText();
	if(isDouble(tempString))
	{
	matrix [temp][temp1] =
	Double.parseDouble(inputField[temp][temp1].getText());
	}
	else
	{
	JOptionPane.showMessageDialog(null, "You entered wrong elements");
	//backup
	col = lastCol;
	row = lastRow;return false;
	}
	}
	}
	return true;
	}
	else
	return false;
	}//end get Inputs
	//for setting spaced fields as zeros
	private static void checkTextField (JTextField field [][] )
	{
	for(int temp = 0; temp < field.length; temp++)
	{
	for(int temp1 = 0; temp1 < field[0].length;
	temp1++)
	{
	if(field[temp][temp1].getText().equals(""))
	field[temp][temp1].setText("0");
	}
	}
	}
	private void ChooseOperation ()
	{
	int temp;
	for(temp = 0; temp < choosePanel.length; temp++)
	{
	choosePanel [temp] = new JPanel ();
	}
	at1=new JLabel("paramter a ");
	at1.setPreferredSize(new Dimension (110,30));
	at1.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[12].add(at1);
	at= new JTextField();
	at.setPreferredSize(new Dimension(110,50));
	at.addActionListener(this);
	at .setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[ 12 ].add(at);
	bt1=new JLabel("paramter b");
	bt1.setPreferredSize(new Dimension (110,30));
	bt1.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[12].add(bt1);
	bt= new JTextField();
	bt.setPreferredSize(new Dimension(110,50));bt.addActionListener(this);
	bt .setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[ 12 ].add(bt);
	ct1=new JLabel("paramter c ");
	ct1.setPreferredSize(new Dimension (110,30));
	ct1.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[12].add(ct1);
	ct= new JTextField();
	ct.setPreferredSize(new Dimension(110,50));
	ct.addActionListener(this);
	ct.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[ 12 ].add(ct);
	u=new JLabel(" solve of equation : " ) ;
	u.setFont(new Font(Font.SERIF,Font.BOLD,18));
	u.setPreferredSize(new Dimension (200,40));
	u.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[10].add(u);
	Q= new JTextField();
	Q.setPreferredSize(new Dimension(350,50));
	Q.addActionListener(this);
	Q.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[ 10 ].add(Q);
	t= new JTextField();
	t.setPreferredSize(new Dimension(1000,50));
	t.addActionListener(this);
	t.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[ 1 ].add(t);
	equ=new JLabel(" Hint : The standard form of a quadratic equation is ax2+bx+c=0 " ) ;
	equ.setPreferredSize(new Dimension (520,40));
	choosePanel[11].add(equ);
	equ.setFont(new Font(Font.SERIF,Font.BOLD,18));
	equ1=new JLabel("Quadratic equation");
	equ1.setPreferredSize(new Dimension (200,40));
	equ1.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[8].add(equ1);
	comfarm=new JButton("Confirm");
	comfarm.setPreferredSize(new Dimension(120,50));
	comfarm.addActionListener(this);
	comfarm.setBackground(Color.LIGHT_GRAY);
	comfarm.setFont(new
	Font(Font.SERIF,Font.BOLD,20));
	choosePanel[14].add(comfarm);b1=new JButton("1");
	choosePanel[ 1 ].add(t);
	b1.setPreferredSize(new Dimension(100,40));
	b1.setBackground(Color.LIGHT_GRAY);
	b1.addActionListener(this);
	choosePanel[ 2 ].add(b1);
	b1.setFont(new Font(Font.SERIF,Font.BOLD,20));
	b2=new JButton("2");
	b2.setPreferredSize(new Dimension (100,40));
	b2.addActionListener(this);
	b2.setBackground(Color.LIGHT_GRAY);
	b2.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[2].add(b2);
	b3=new JButton("3");
	b3.setPreferredSize(new Dimension (100,40));
	b3.addActionListener(this);
	b3.setBackground(Color.LIGHT_GRAY);
	b3.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[2].add(b3);
	badd=new JButton("+");
	badd.setPreferredSize(new Dimension (100,40));
	badd.addActionListener(this);
	badd.setBackground(Color.LIGHT_GRAY);
	badd.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[2].add(badd);
	bsub=new JButton("-");
	bsub.setPreferredSize(new Dimension (100,40));
	bsub.addActionListener(this);
	bsub.setBackground(Color.LIGHT_GRAY);
	bsub.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[2].add(bsub);
	bclr=new JButton("Clear");
	bclr.setPreferredSize(new Dimension (100,40));
	bclr.addActionListener(this);
	bclr.setBackground(Color.LIGHT_GRAY);
	bclr.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[2].add(bclr);
	b4=new JButton("4");
	b4.setPreferredSize(new Dimension (100,40));
	b4.addActionListener(this);b4.setBackground(Color.LIGHT_GRAY);
	b4.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(b4);
	b5=new JButton("5");
	b5.setPreferredSize(new Dimension (100,40));
	b5.addActionListener(this);
	b5.setBackground(Color.LIGHT_GRAY);
	b5.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(b5);
	b6=new JButton("6");
	b6.setPreferredSize(new Dimension (100,40));
	b6.addActionListener(this);
	b6.setBackground(Color.LIGHT_GRAY);
	b6.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(b6);
	bmul=new JButton("*");
	bmul.setPreferredSize(new Dimension (100,40));
	bmul.addActionListener(this);
	bmul.setBackground(Color.LIGHT_GRAY);
	bmul.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(bmul);
	bdiv=new JButton("/");
	bdiv.setPreferredSize(new Dimension (100,40));
	bdiv.addActionListener(this);
	bdiv.setBackground(Color.LIGHT_GRAY);
	bdiv.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(bdiv);
	bdel=new JButton("Delete");
	bdel.setPreferredSize(new Dimension (100,40));
	bdel.addActionListener(this);
	bdel.setBackground(Color.LIGHT_GRAY);
	bdel.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[3].add(bdel);
	b7=new JButton("7");
	b7.setPreferredSize(new Dimension (100,40));
	b7.addActionListener(this);
	b7.setBackground(Color.LIGHT_GRAY);
	b7.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[4].add(b7);b8=new JButton("8");
	b8.setPreferredSize(new Dimension (100,40));
	b8.addActionListener(this);
	b8.setBackground(Color.LIGHT_GRAY);
	b8.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[4].add(b8);
	b9=new JButton("9");
	b9.setPreferredSize(new Dimension (100,40));
	b9.addActionListener(this);
	b9.setBackground(Color.LIGHT_GRAY);
	b9.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[4].add(b9);
	b0=new JButton("0");
	b0.setPreferredSize(new Dimension (100,40));
	b0.addActionListener(this);
	b0.setBackground(Color.LIGHT_GRAY);
	b0.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[4].add(b0);
	bdec=new JButton(".");
	bdec.setPreferredSize(new Dimension (100,40));
	bdec.addActionListener(this);
	bdec.setBackground(Color.LIGHT_GRAY);
	bdec.setFont(new Font(Font.SERIF,Font.BOLD,20));
	choosePanel[4].add(bdec);
	beq=new JButton("=");
	beq.setPreferredSize(new Dimension (100,40));
	beq.addActionListener(this);
	beq.setBackground(Color.LIGHT_GRAY);
	choosePanel[4].add(beq);
	beq.setFont(new Font(Font.SERIF,Font.BOLD,20));
	showMatrix = new JButton ("Show last Matrix");
	showMatrix.setPreferredSize(new Dimension(300,30));
	showMatrix.addActionListener(this);
	showMatrix.setBackground(Color.LIGHT_GRAY);
	showMatrix.setFont(new Font(Font.SERIF,Font.BOLD,16));
	choosePanel[6].add(showMatrix);
	plusB = new JButton ("Sum matrix with new matrix");
	plusB.setPreferredSize(new Dimension(300,30));
	plusB.addActionListener(this);
	plusB.setBackground(Color.LIGHT_GRAY);plusB.setFont(new Font(Font.SERIF,Font.BOLD,16));
	choosePanel[7].add(plusB);
	minusB = new JButton ("Subtracting matrix with new matrix");
	minusB.setPreferredSize(new Dimension(300,30));
	minusB.addActionListener(this);
	minusB.setBackground(Color.LIGHT_GRAY);
	minusB.setFont(new Font(Font.SERIF,Font.BOLD,16));
	choosePanel[7].add(minusB);
	multiplyB = new JButton ("Multiplying matrix with new matrix");
	multiplyB.setPreferredSize(new Dimension (300,30));
	multiplyB.addActionListener(this);
	multiplyB.setBackground(Color.LIGHT_GRAY);
	multiplyB.setFont(new Font(Font.SERIF,Font.BOLD,16));
	choosePanel[6].add(multiplyB);
	newMatrix = new JButton("New Matrix");
	newMatrix.setBackground(new Color(150,200,150));
	newMatrix.setPreferredSize(new Dimension(300,30));
	newMatrix.addActionListener(this);
	newMatrix.setBackground(Color.LIGHT_GRAY);
	newMatrix.setFont(new Font(Font.SERIF,Font.BOLD,16));
	choosePanel[5].add(newMatrix);
	JOptionPane.showConfirmDialog(null, choosePanel, null,
	JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);
	}
	@Override
	public void actionPerformed(ActionEvent e ){
	{
	{
	if ( e.getSource() == comfarm){
	if
	(at.getText().isEmpty()||bt.getText().isEmpty()||ct.getText().isEmpty()){
	 JOptionPane.showMessageDialog(null, "You must enter pramter");
	}
	else
	{
	a=Double.parseDouble(at.getText());
	b=Double.parseDouble(bt.getText());c=Double.parseDouble(ct.getText());
	ResultPramter_x=((-b ) + Math.sqrt(( b * b) - (4 * a *
	c))) / (2 * a);
	ResultPramter_y=((-b) - Math.sqrt(( b * b) - (4 * a *
	c))) / ( 2 * a);
	Q.setText("paramter1 "+ResultPramter_x+ " paramter2"+ResultPramter_y);
	}
	}
	if(e.getSource()==b1)
	t.setText(t.getText().concat("1"));
	if(e.getSource()==b2)
	t.setText(t.getText().concat("2"));
	if(e.getSource()==b3)
	t.setText(t.getText().concat("3"));
	if(e.getSource()==b4)
	t.setText(t.getText().concat("4"));
	if(e.getSource()==b5)
	t.setText(t.getText().concat("5"));
	if(e.getSource()==b6)
	t.setText(t.getText().concat("6"));
	if(e.getSource()==b7)
	t.setText(t.getText().concat("7"));
	if(e.getSource()==b8)
	t.setText(t.getText().concat("8"));
	if(e.getSource()==b9)
	t.setText(t.getText().concat("9"));
	if(e.getSource()==b0)
	t.setText(t.getText().concat("0"));
	if(e.getSource()==bdec)
	t.setText(t.getText().concat("."));
	if(e.getSource()==badd)
	{
	aa=Double.parseDouble(t.getText());
	operator=1;
	t.setText("");}
	if(e.getSource()==bsub)
	{
	aa=Double.parseDouble(t.getText());
	operator=2;
	t.setText("");
	}
	if(e.getSource()==bmul)
	{
	aa=Double.parseDouble(t.getText());
	operator=3;
	t.setText("");
	}
	if(e.getSource()==bdiv)
	{
	aa=Double.parseDouble(t.getText());
	operator=4;
	t.setText("");
	}
	if(e.getSource()==beq)
	{
	bb=Double.parseDouble(t.getText());
	switch(operator)
	{
	case 1: result =aa+bb;
	break;
	case 2: result =aa-bb;
	break;
	case 3: result =aa*bb;
	break;
	case 4: result =aa/bb;
	break;
	default: result =0;
	} t
	.setText(""+result );
	}if(e.getSource()==bclr)
	t.setText("");
	if(e.getSource()==bdel)
	{
	String s=t.getText();
	t.setText("");
	for(int i=0;i<s.length()-1;i++)
	t.setText(t.getText()+s.charAt(i));
	}
	if (e.getSource()==showMatrix){
	showMatrix( myMatrix, "Your Matrix");
	}
	if(e.getSource() == plusB)
	{
	matrixPlusMatrix();
	}
	else if(e.getSource() == minusB)
	{
	matrixMinusMatrix();
	}
	else if(e.getSource() == multiplyB)
	{
	multiplyByMatrix();
	}
	else if(e.getSource() == newMatrix)
	{
	newMatrix();
	} }
	}//end action performed
	}
	private static void showMatrix(double [][] matrix, String
	title )
	{
	int temp, temp1; //temprature variable
	JPanel choosePanel [] = new JPanel [matrix.length+1];
	choosePanel[0] = new JPanel ();
	choosePanel[0].add( new JLabel (title) );for(temp = 1; temp <= matrix.length; temp++)
	{
	choosePanel[temp] = new JPanel();
	for(temp1 = 0; temp1 < matrix[0].length; temp1++)
	{
	if(matrix[temp-1][temp1] == -0)
	{
	matrix[temp-1][temp1] = 0;
	}
	choosePanel[temp].add(new
	JLabel(String.format("%.2f", matrix[temp-1][temp1])));
	if(temp1 < matrix[0].length -1)
	{
	choosePanel[temp].add(Box.createHorizontalStrut(15)); // aspacer
	}
	}//end col loop
	}//end row loop
	if(col == 0 || row == 0)
	{
	JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	}
	else
	{
	JOptionPane.showMessageDialog(null, choosePanel, null,
	JOptionPane.PLAIN_MESSAGE, null);
	} }
	//end show Matrix
	private static void matrixPlusMatrix ()
	{
	if(myMatrix.length < 1)
	{
	JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	}
	else
	{double m2[][]=new double [row][col];
	double sum[][] = new double [row][col];
	if(setElements(m2, "Fill Aditional Matrix"))
	{
	for(int i=0;i<row;i++)
	{
	for(int j=0;j<col;j++)
	{
	sum[i][j]=myMatrix[i][j]+m2[i][j];
	} }
	showMatrix(sum, "Summition Result");
	}
	}//end else checking
	}//end plus matrix
	private static void matrixMinusMatrix ()
	{
	if(myMatrix.length < 1)
	{
	JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	}
	else
	{
	double m2[][]=new double [row][col];
	double sub[][] = new double [row][col];
	double temp [][] = new double [row][col];
	if(setElements(m2, "Fill Subtracting Matrix"))
	{
	for(int i=0;i<row;i++)
	{
	for(int j=0;j<col;j++)
	{
	temp[i][j]=(-1*m2[i][j]);
	sub[i][j]=myMatrix[i][j]+temp[i][j];
	} }
	showMatrix(sub, "Subtracting Result");
	}
	
	}//end else of checking
	}
	private static void multiplyByMatrix ()
	{
	JTextField wField = new JTextField(5); //col field
	int col2 = 0;
	double m2 [][] , results[][];
	int sum;
	if(myMatrix.length < 1)
	{
	JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	}
	else
	{
	//design input line
	JPanel choosePanel [] = new JPanel [2];
	choosePanel [0] = new JPanel();
	choosePanel [1] = new JPanel();
	choosePanel[0].add(new JLabel("Enter Dimensitions") );
	choosePanel[1].add(new JLabel("Rows:"));
	choosePanel[1].add(new JLabel(""+col));
	choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
	choosePanel[1].add(new JLabel("Cols:"));
	choosePanel[1].add(wField);
	result = JOptionPane.showConfirmDialog(null, choosePanel,
	null,JOptionPane.PLAIN_MESSAGE,
	JOptionPane.PLAIN_MESSAGE);
	if(result == 0)
	{
	if(wField.getText().equals(""))
	{
	col2 = 0;
	}
	else
	{
	if(isInt(wField.getText()) ){
	col2 = Integer.parseInt(wField.getText());
	}
	}
	m2 = new double [col][col2];
	results = new double [row][col2];
	if(setElements(m2, "Fill multiplying matrix"))
	{
	for ( int i = 0 ; i < row ; i++ )
	{
	for ( int j = 0 ; j < col2 ; j++ )
	{
	sum = 0;
	for ( int k = 0 ; k < col ; k++ )
	{
	sum += myMatrix[i][k]*m2[k][j];
	}
	results[i][j] = sum;
	}
	}
	showMatrix(results, "Mulitiplication Result");
	}//elements checking
	}//dimensions checking
	else
	return;
	}//end else of checking
	}
	public static boolean isInt (String str)
	{
	int temp;
	if (str.length() == '0')
	return false;
	for(temp = 0; temp < str.length();temp++)
	{
	if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
	&& !Character.isDigit(str.charAt(temp)))
	{
	return false;
	}
	}return true;
	}
	private static boolean isDouble (String str)
	{
	int temp;
	if (str.length() == '0')
	return false;
	for(temp = 0; temp < str.length();temp++)
	{
	if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
	&& str.charAt(temp) != '.'
	&& !Character.isDigit(str.charAt(temp))
	)
	{
	return false;
	}
	}
	return true;
	}
	private static void newMatrix ()
	{
	getDimension();
	}
	public static void main (String [] args)
	{
	new finalProject();
	}
	 
}
