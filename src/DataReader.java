import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DataReader {
	
	private String connectionString;
	private String user;
	private String password;
	Connection mycon = null;
	
	public void getConnection(String connectionString,String user,String password)
	{
		this.connectionString = connectionString;
		this.user = user;
		this.password = password;
		
		try {
        	Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            mycon = DriverManager.getConnection(connectionString,user,password);
            } catch (Exception e) {
    				e.printStackTrace();
    		}
	}
	
	public ArrayList<ProfessorInfo> read(String sql) throws SQLException
	{
		Statement st=null;
	    ResultSet rs=null;
	    ArrayList<ProfessorInfo> professorList=new ArrayList<ProfessorInfo>();
	    try{
		    st=mycon.createStatement();
	        rs=st.executeQuery(sql);
	
		    while(rs.next())
	        {
		    	String name = rs.getString("name");
		    	Map<String,String> contactInfo = new HashMap<String,String>();
			    contactInfo.put(rs.getString("email"),rs.getString("phone") );
			    String educationBackground = rs.getString("educationBackground");
			    String researchInterests = rs.getString("researchInterests");
			    ProfessorInfo professor=new ProfessorInfo(name,contactInfo,educationBackground,researchInterests);
			    professor.setName(name);
			    professor.setContactInfo(contactInfo);
			    professor.setEducationBackground(educationBackground);
			    professor.setResearchInterests(researchInterests);
			    professorList.add(professor);
	        }
	
			return professorList;
	    }finally {
			try {
				mycon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
