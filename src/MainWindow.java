import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String keywords;
	JTextArea textArea = new JTextArea();

	public MainWindow()
	{
		JTextField textField = new JTextField();
		textField.setFont(new Font("Serif",Font.PLAIN,20));
		textArea.setFont(new Font("Serif",Font.PLAIN,20));
		//JTextArea textArea = new JTextArea();
		JButton btn = new JButton("Search");
		btn.setFont(new Font("Serif",Font.BOLD,20));
		
		JPanel p = new JPanel(new GridLayout (1, 2));
		p.add(textField);
		p.add(btn);
		p.setPreferredSize(new Dimension(400,50));
		p.setMaximumSize(new Dimension(400,50));
		p.setMinimumSize(new Dimension(400,50));
		
		setLayout (new BorderLayout());
		add(p,BorderLayout.NORTH);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e)
	           {
		           keywords = textField.getText();
		           System.out.print(keywords);
		           try {
					initialize();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	           }
	       });
	}
	
	private void initialize() throws SQLException
	{
		 ArrayList<ProfessorInfo> professorList=new ArrayList<ProfessorInfo>();
		 DataReader reader = new DataReader();
		 reader.getConnection("jdbc:mysql://127.0.0.1:3306/my_schema","root","179324865");
		 professorList = reader.read("select * from professor_info");
		 KeywordsMatcher matcher = new KeywordsMatcher();
		 matcher.calTF(professorList);
		 ArrayList<SearchResult> searchResultList=matcher.sort();
		 for(int i=0;i<searchResultList.size();i++)
		 {
			 //System.out.println(searchResultList.size());
			 ProfessorInfo professor = searchResultList.get(i).getPi();
			 textArea.append(searchResultList.get(i).getTf()+professor.getName()+"\n"+professor.getEducationBackground()
					 +professor.getResearchInterests()+"\n"+professor.getPhone()+"\n"+
					 professor.getEmail()+"\n");
		 }
	}
	
	public String getKeywords()
	{
		return keywords;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MainWindow my_searcher=new MainWindow();
	    my_searcher.setTitle("Professor Searcher");
	    my_searcher.setBounds(100, 100, 400, 500);
	    my_searcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    my_searcher.setVisible(true);
	    
	}

}
