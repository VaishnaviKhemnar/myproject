import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Login extends Frame implements ActionListener
	{
		Label l1,l2;
		Button b1,b2;
		TextField t1,t2;
		Login()
	{
		
		setTitle("FrameDemo");
		setSize(400,300);
		setVisible(true);
		l1=new Label("Username:");
		l2=new Label("Password:");
		t1=new TextField (20);
		t2=new TextField (20);
		b1=new Button("Login");
		b2=new Button("Cancel");
		t2.setEchoChar('*');
		setLayout(null);
		l1.setBounds(100,100,80,30);
		t1.setBounds(200,100,100,20);
		l2.setBounds(100,140,80,30);
		t2.setBounds(200,140,100,20);
		b1.setBounds(120,200,80,25);
		b2.setBounds(220,200,80,25);
		add(l1);add(t1);add(l2);add(t2);
		add(b1);add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);		
		addWindowListener(new FD());
	}
		
	public void actionPerformed(ActionEvent e)
	{
		
	String s="admin";
	if(e.getSource()==b1)
	{
		if(s.equals(t1.getText())&&(s.equals(t2.getText())))
		{
		ExamDemo ed=new ExamDemo();
		setVisible(false);
		}
	
		else
		JOptionPane.showMessageDialog(null,"Try Again");
	}
	else
		System.exit(0);
	}										

      class FD extends WindowAdapter
	{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
    }
     /* class ShowPicture {
    	  
    		  public void init(Graphics g)
    		  {
    	   JFrame frame = new JFrame();
    	   ImageIcon icon = new ImageIcon("login.jpg");
    	   JLabel label = new JLabel(icon);
    	   frame.add(label);
    	   frame.setDefaultCloseOperation
    	          (JFrame.EXIT_ON_CLOSE);
    	   frame.pack();
    	   frame.setVisible(true);
    	  }
      }*/
	public static void main(java.lang.String args[])
 	{
		SplashWindowFrame spl=new SplashWindowFrame();
		Login l=new Login();
		
        }
}