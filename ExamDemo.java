
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ExamDemo{
	static FrameLayout fm;
	ExamDemo(){
		
		fm=new FrameLayout();
		fm.setSize(700,500);
       Dimension screenDim = 
             Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winDim = fm.getBounds();
        fm.setLocation((screenDim.width - winDim.width) / 2,
		(screenDim.height - winDim.height) / 2);
		fm.setIconImage(new ImageIcon("Icon.gif").getImage());
		fm.setVisible(true);
		fm.setResizable(false);
		fm.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				final JDialog jd=new JDialog();
				jd.setModal(true);
				jd.setTitle("ExamDemo");
				Label l1=new Label("Do You Want to Close Test ?",Label.CENTER);
				final JButton b1=new JButton("Ok");
				final JButton b2=new JButton("Cancel");
				b1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(e.getSource()==b1)	System.exit(0);
						}
				});
				b2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(e.getSource()==b2)	jd.hide();
						}
				});
				Panel p=new Panel();
				p.add(b1);
				p.add(b2);
				Container con=jd.getContentPane();
				con.add(l1,BorderLayout.NORTH);
				con.add(p,BorderLayout.SOUTH);
				jd.setSize(200,100);
				Dimension screenDim =
				           Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle winDim = jd.getBounds();
				jd.setLocation((screenDim.width - winDim.width) / 2,
				(screenDim.height - winDim.height) / 2);
				jd.show();
				}
		});
	}

}
class FrameLayout extends JFrame {
	String Question[][] =new String[40][7];
	boolean Answer[][]=new boolean[40][6];
	Label QueNo =new Label();
	Label Timer =new Label("",Label.RIGHT);
	int maxQue=1;
	private MenuBar mainMenu;
	private Menu MFile;
	private MenuItem IStart;
	TextArea TQuestion=new TextArea();
	ButtonGroup bg=new ButtonGroup();
	JRadioButton JAnswer[]=new JRadioButton[5];
	JRadioButton JMark =new JRadioButton("Mark");
	JButton JNext,JPrev,JGrade,JReview;
	static int QuestionNo=1;
	Questions Ques=new Questions();
	FrameLayout(){
		setTitle("ExamDemo");
		Timer t=new Timer(this);
		t.setDaemon(true);
		t.start();
                      
		Container contentPane=getContentPane();
		Question=Ques.getQuestions();
		Panel Top=new Panel();
			Top.setLayout(new BorderLayout());
		Top.add(QueNo,BorderLayout.WEST);
		Top.add(JMark,BorderLayout.EAST);
		TQuestion.setEditable(false);
		Top.add(TQuestion,BorderLayout.SOUTH);
		Panel center =new Panel();
		center.setLayout(new GridLayout(5,1));
		for(int i=0;i<5;i++){
			
                                                    bg.add(JAnswer[i]);
                        
			JAnswer[i] =new JRadioButton();
			JAnswer[i].addItemListener(new ItemList());
			center.add(JAnswer[i]);
			JAnswer[i].setFont(new Font("Seriff",Font.PLAIN,12));
		}
		Panel bottom=new Panel();
		bottom.setLayout(new GridLayout(1,5));
		JNext=new JButton("Next");
		JPrev=new JButton("Previous");
		JPrev.setEnabled(false);
		JGrade =new JButton("Result");
		JReview=new JButton("Review");
		bottom.add(JPrev);
		bottom.add(JNext);
		bottom.add(JGrade);
		bottom.add(JReview);
		Timer.setForeground(Color.blue);
		bottom.add(Timer);
		contentPane.add(Top,BorderLayout.NORTH);
		contentPane.add(center,BorderLayout.CENTER);
		contentPane.add(bottom,BorderLayout.SOUTH);
		makeQueVis(1);
		makeMenu();
		JNext.addActionListener(new ActionList());
		JPrev.addActionListener(new ActionList());
		JGrade.addActionListener(new ActionList());
		JReview.addActionListener(new ActionList());
		JMark.addItemListener(new ItemList());
	}
	void makeQueVis(int No){
		if(No>0 && No<41){
			QueNo.setText("Question No " + No + "/40");
			TQuestion.setText(Question[No-1][0]);
			TQuestion.setRows(10);
			if(maxQue<No){
				maxQue=No;
				for(int i=0;i<5;i++){
					if(!Question[No-1][i+1].equals("")){
						JAnswer[i].setVisible(true);
						JAnswer[i].setSelected(false);
						JAnswer[i].setText(Question[No-1][i+1]);
					}
					else JAnswer[i].setVisible(false);
				}
				JMark.setSelected(false);
			}
			else{
				for(int i=0;i<5;i++){
					if(!Question[No-1][i+1].equals("")){
						JAnswer[i].setVisible(true);
						JAnswer[i].setSelected(Answer[No-1][i]);
						JAnswer[i].setText(Question[No-1][i+1]);
					}
					else JAnswer[i].setVisible(false);
				}
				JMark.setSelected(Answer[No-1][5]);
			}
		}
	}

	void makeMenu(){
				mainMenu=new MenuBar();
				MFile=new Menu("About");
				IStart=new MenuItem("About ExamDemo");
				IStart.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent a){
						final JDialog jd=new JDialog();
						jd.setModal(true);
						jd.setTitle("About ExamDemo");
						Label l1=new Label("ExamDemo",Label.CENTER);
						Label l2=new Label("MIT COLLEGE",Label.CENTER);
						Label l3=new Label("Submitted By -Khemnar Vaishnavi \n Sanjana Pakal \n Zond Vaishnavi",Label.CENTER);
						Label l4=new Label("E-mail -Ravina1shihare@gmail.com",Label.CENTER);
						JButton b1=new JButton("Ok");
						b1.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								jd.dispose();
							}
						});
						Panel p=new Panel();
						p.add(l1);
						p.add(l2);
						p.add(l3);
						p.add(l4);
						p.setLayout(new GridLayout(4,1));
						Container con=jd.getContentPane();
						con.add(p,BorderLayout.NORTH);
						con.add(b1,BorderLayout.SOUTH);
						jd.setSize(300,200);
				        Dimension screenDim =
			             Toolkit.getDefaultToolkit().getScreenSize();
				        Rectangle winDim = jd.getBounds();
				        jd.setLocation((screenDim.width - winDim.width) / 2,
						(screenDim.height - winDim.height) / 2);
						jd.show();
					}
				});
				mainMenu.add(MFile);
				MFile.add(IStart);
				setMenuBar(mainMenu);
		}


	class ActionList implements ActionListener{
		public void actionPerformed(ActionEvent a){
			if(a.getSource()==JNext){
				if(QuestionNo<40){
					QuestionNo=QuestionNo+1;
					JNext.setEnabled(true);
					JPrev.setEnabled(true);
					if(QuestionNo>=40) JNext.setEnabled(false);
				}
				makeQueVis(QuestionNo);
			}
			if(a.getSource()==JPrev){
				if(QuestionNo>0){
					QuestionNo=QuestionNo-1;
					JNext.setEnabled(true);
					JPrev.setEnabled(true);
					if(QuestionNo<=1) JPrev.setEnabled(false);
				}
				makeQueVis(QuestionNo);
			}
			if(a.getSource()==JGrade){
				Result fr=new Result(ExamDemo.fm);
			}
			if(a.getSource()==JReview){
				Final fr=new Final(ExamDemo.fm);
			}
		}
	}

	class ItemList implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			JRadioButton jc=(JRadioButton)e.getItem();
			for(int i=0;i<5;i++){
				if(e.getStateChange()==1){
					if(jc==JAnswer[i]){
						Answer[QuestionNo-1][i]=true;
					}
				}
				else{
					if(jc==JAnswer[i]){
						Answer[QuestionNo-1][i]=false;
					}
				}
			}
			if(jc==JMark){
				if(e.getStateChange()==1){
					Answer[QuestionNo-1][5]=true;
				}
				else Answer[QuestionNo-1][5]=false;
			}
		}
	}

}


class Questions {
	RandomAccessFile file;
	String Question[][] =new String[40][7];
	Questions(){
		try{
			file=new RandomAccessFile("Question.txt","r");
		}
		catch(IOException e){System.out.println(e);}
	}

	String[][] getQuestions(){
		StringBuffer s1;
		try{
		for(int i=0;i<40;i++){
			StringBuffer sb1=new StringBuffer();
			b1: while(true){
					String Temp=new String(file.readLine());
					if(Temp.equals("##-----Question-----##")){
						int k;
						k=(int)Math.ceil(Math.random()*2);
						switch(k){
							case 2:
								break b1;
						}
					}
			}
			while(true){
				s1=new StringBuffer(file.readLine());
				if(new String(s1).equals("##-----Question-----##")){
					break;
				}
				else if(new String(s1).equals("##ans1##")){
					Question[i][1]=file.readLine();
				}
				else if(new String(s1).equals("##ans2##")){
					Question[i][2]=file.readLine();
				}
				else if(new String(s1).equals("##ans3##")){
					Question[i][3]=file.readLine();
				}
				else if(new String(s1).equals("##ans4##")){
					Question[i][4]=file.readLine();
				}
				else if(new String(s1).equals("##ans5##")){
					Question[i][5]=file.readLine();
				}
				else if(new String(s1).equals("##correct##")){
					Question[i][6]=file.readLine();
				}
				else{
					sb1.append(s1);
					sb1.append("\n");
					Question[i][0]=new String(sb1);
				}
			}
		}
		}
		catch(IOException e){System.out.println(e);}
		return Question;

	}
}

class Result extends JDialog {
	Label TotalQue =new Label("Total Question");
	Label TotalAns =new Label("Correct Answer");
	Label TotalWrg =new Label("In-Correct Answer");
	Label Per =new Label("Percentage Of Marks");
	Label Grade =new Label("Grade");
	JButton jb=new JButton("Ok-End Test");
	int correct;
	Result(final FrameLayout fm){
		setTitle("Result");
		setModal(true);
		for(int i=0;i<fm.maxQue;i++){
			StringBuffer s=new StringBuffer();
			for(int j=0;j<5;j++){
				if(fm.Answer[i][j]==true){
					s.append(j+1 + ",");
				}
			}
			if(s.length()!=0){
				s.deleteCharAt(s.length()-1);
			}
			if(fm.Question[i][6].equals(new String(s))){
				correct=correct+1;
			}
		}
		Container content=getContentPane();
		Panel con=new Panel();
		con.setLayout(new GridLayout(5,2));
		con.add(TotalQue);
		con.add(new Label("" + fm.maxQue));
		con.add(TotalAns);
		con.add(new Label("" + correct));
		con.add(TotalWrg);
		con.add(new Label((fm.maxQue-correct) + ""));
		con.add(Per);
		int l=0;
		l=correct*100/fm.maxQue;
		//System.out.println(l);
		con.add(new Label(l+ " %"));
		con.add(Grade);
		if(l<71) con.add(new Label("Failed"));
		else con.add(new Label("Passed"));
		content.add(con,BorderLayout.NORTH);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				System.exit(0);
			}
		});
		content.add(jb,BorderLayout.SOUTH);
		setSize(250,250);
        Dimension screenDim =
             Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winDim =getBounds();
        setLocation((screenDim.width - winDim.width) / 2,
		(screenDim.height - winDim.height) / 2);
		setVisible(true);
	}
}


class Timer extends Thread{
	private int min,sec;
	private String Smin,Ssec;
	FrameLayout l;

	public void run(){
		min=29;
		sec=60;
		while(true){
			try{
				sleep(1000);
			}
			catch(Exception e){}
			sec=sec-1;
			if(sec<1){
				min=min-1;
				sec=60;
			}
			if(min<10){
				Smin="0" + min;
			}
			else{
				Smin="" + min;
			}
			if(sec<10){
				Ssec="0" + sec;
			}
			else{
				Ssec=""  + sec;
			}
			l.Timer.setText("Time : " + Smin + ":" + Ssec);
			if(min<1 &&  sec==1){
				final JDialog jd=new JDialog(l,true);
				Label l1=new Label("Your Times Up",Label.CENTER);
				JButton b1=new JButton("Ok -End Test");
				b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent a){
						l.JNext.setEnabled(false);
						l.JPrev.setEnabled(false);
						l.JMark.setEnabled(false);
						jd.dispose();
					}
				});
				Container con=jd.getContentPane();
				con.add(l1,BorderLayout.NORTH);
				con.add(b1,BorderLayout.SOUTH);
				jd.setSize(150,100);
		        Dimension screenDim =
	             Toolkit.getDefaultToolkit().getScreenSize();
		        Rectangle winDim = jd.getBounds();
		        jd.setLocation((screenDim.width - winDim.width) / 2,
				(screenDim.height - winDim.height) / 2);
				jd.show();
				break;
			}
		}
	}
	Timer(FrameLayout l){
			this.l=l;
	}
}




class SplashWindowFrame extends Frame {
    SplashWindow sw;
    Image splashIm;

    SplashWindowFrame() {
       MediaTracker mt = new MediaTracker(this);
       splashIm = Toolkit.getDefaultToolkit(
           ).getImage("FlashScreen1.jpg");
       mt.addImage(splashIm,0);
       try {
          mt.waitForID(0);
       } catch(InterruptedException ie){}

       sw = new SplashWindow(this,splashIm);

       try {
	  Thread.sleep(5000);
       } catch(InterruptedException ie){}

       sw.dispose();

       }
}

class SplashWindow extends Window {
    Image splashIm;

    SplashWindow(Frame parent, Image splashIm) {
        super(parent);
        this.splashIm = splashIm;
        setSize(600,400);

        /* Center the window */
        Dimension screenDim =
             Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winDim = getBounds();
        setLocation((screenDim.width - winDim.width) / 2,
		(screenDim.height - winDim.height) / 2);
        setVisible(true);
    }

    public void paint(Graphics g) {
       if (splashIm != null) {
           g.drawImage(splashIm,0,0,this);
       }
    }
}


class Final extends JDialog{
	private JButton Question[] =new JButton[40];
	FrameLayout cl=new FrameLayout();
	class ActionList implements ActionListener{
			int i;
			public void actionPerformed(ActionEvent a){
				String s=a.getActionCommand();
				if(s.length()==11){
					s=s.substring(7,8);
					//System.out.println(s);
				}
				else s=s.substring(7,9);
				int i=new Integer(s).intValue();
				cl.QuestionNo=i;
				cl.makeQueVis(i);
			}
			ActionList(int i){
				this.i=i;
			}
	}
	Final(final FrameLayout cl){
		setTitle("Review");
		setModal(true);
		this.cl=cl;
		int j=0;
		final Container panel=getContentPane();
		Panel pane=new Panel();
		if(cl.maxQue<10){
			pane.setLayout(new GridLayout(2,5));
		}
		else if(cl.maxQue<20){
			pane.setLayout(new GridLayout(4,5));
		}
		else if(cl.maxQue<30){
			pane.setLayout(new GridLayout(6,5));
		}
		else if(cl.maxQue<40){
					pane.setLayout(new GridLayout(8,5));
		}
		for(int i=0;i<cl.maxQue;i++){
			j=i+1;
			Question[i]=new JButton("Que No." + j + "/40");
			if(cl.Answer[i][5]==true){
				Question[i].setBackground(Color.red);
			}
			Question[i].addActionListener(new ActionList(i));
			pane.add(Question[i]);
		}
		Button ok=new Button("Ok");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				dispose();
			}
		});
		panel.add(pane,BorderLayout.CENTER);
		panel.add(ok,BorderLayout.SOUTH);
		setSize(600,400);
        Dimension screenDim =
             Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winDim = getBounds();
        setLocation((screenDim.width - winDim.width) / 2,
		(screenDim.height - winDim.height) / 2);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					//System.out.println("Test");
				}
		});
	}
}

