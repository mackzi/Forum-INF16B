
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;



/*
 * by 	Fabian Schulz,
 * 		Daniel Bilmann,
 * 		Jannik Zeyer,
 * 		Andreas Memmel
 */

public class DAO {
	
	//Add new user to DB. 
	public void addNewUser(User user) throws Exception
	{
		Connection con = null;
        PreparedStatement ps = null;
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();

        }
        catch(Exception e) {e.printStackTrace();}
        
        String sqlString = "INSERT INTO USER ("
			        		+ "firstname, lastname, email, role, pwsalt, pwhash, imgurl) "
			        		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
		ps = con.prepareStatement(sqlString);
        ps.setString(1, user.getFirstname());
        ps.setString(2, user.getLastname());
        ps.setString(3, user.getEmail());
        ps.setInt(4, user.getRole());
        ps.setString(5, user.getPwSalt());
        ps.setString(6, user.getPwHash());
        ps.setString(7, user.getImageUrl());
        ps.executeUpdate();
        
        
        ps.close();
        con.close();
	}
	
	//Add post to DB. 
		public void addNewPosting(Posting posting) throws Exception
		{
			Connection con = null;
	        PreparedStatement ps = null;
	        
	        try
	        {
	            con = MySQLDatabase.getInstance().getConnection();

	        }
	        catch(Exception e) {e.printStackTrace();}
	        
	        String sqlString = "INSERT INTO POSTSING ("
				        		+ "authorid, subjectid, Text, whenposted, whendeleted) "
				        		+ "VALUES (?, ?, ?, ?, ?)";
	        
	        
			ps = con.prepareStatement(sqlString);
	        ps.setInt(1, post.getAuthorId());
	        ps.setInt(2, post.getSubjectId());
	        ps.setString(3, post.getText());
	        ps.setDate(4, post.getWhenposted());
	        ps.setDate(5, post.getWhendeleted());
//	        ps.setString(4, new java.sql.Timestamp(System.currentTimeMillis()));	nur falls wir kein Objekt bekommen sollten
//	        ps.setString(5, null);
	        ps.executeUpdate();
	        
	        ps.close();
	        con.close();
		}
		
		//Add attachement to DB. 
		public void addAttachement(char[50] filename, int postingId) throws Exception
		{
			Connection con = null;
	        PreparedStatement ps = null;
	        
	        try
	        {
	            con = MySQLDatabase.getInstance().getConnection();

	        }
	        catch(Exception e) {e.printStackTrace();}
	        
	        String sqlString = "INSERT INTO ATTACHEMENT ("
				        		+ "attachementfilename, postingid) "
				        		+ "VALUES (?, ?)";
	        
			ps = con.prepareStatement(sqlString);
	        ps.setString(1, filename);
	        ps.setInt(2, postingId);
	        ps.executeUpdate();
	        
	        ps.close();
	        con.close();
		}
		
		//Add subject to DB. 
				public void addSubject(char[50] name, int forumId) throws Exception
				{
					Connection con = null;
			        PreparedStatement ps = null;
			        
			        try
			        {
			            con = MySQLDatabase.getInstance().getConnection();

			        }
			        catch(Exception e) {e.printStackTrace();}
			        
			        String sqlString = "INSERT INTO SUBJECT ("
						        		+ "name, forumid) "
						        		+ "VALUES (?, ?)";
			        
					ps = con.prepareStatement(sqlString);
			        ps.setString(1, name);
			        ps.setString(2, forumId);
			        ps.executeUpdate();
			        
			        ps.close();
			        con.close();
				}
				
				//Add forum to DB. 
				public void addForum(String name, String category, int modId) throws Exception
				{
					Connection con = null;
			        PreparedStatement ps = null;
			        
			        try
			        {
			            con = MySQLDatabase.getInstance().getConnection();

			        }
			        catch(Exception e) {e.printStackTrace();}
			        
			        String sqlString = "INSERT INTO FORUM ("
						        		+ "name, category, moteratorid) "
						        		+ "VALUES (?, ?, ?)";
			        
					ps = con.prepareStatement(sqlString);
			        ps.setString(1, name);
			        ps.setString(2, category);
			        ps.setInt(3, modId);
			        ps.executeUpdate();
			        
			        ps.close();
			        con.close();
				}
	
	
	public User getUserByName(String fName, String lName) 
	{
		Connection con = null;
        ResultSet rs; 
        User newUser = null;
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();
        
	        String sqlString = "SELECT * FROM USERS WHERE firstname=? AND lastname=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setString(1, fName);
	        ps.setString(2, lName);
	        
	        rs = ps.executeQuery();
	        
	        if(!rs.next())
	        {
	            return null;
	        }
	        
	        newUser = new User();
	        newUser.setFirstname(rs.getString("firstname"));
	        newUser.setLastname(rs.getString("lastname"));
	        newUser.setEmail(rs.getString("email"));
	        newUser.setRole(rs.getInt("role"));
	        newUser.setPwSalt(rs.getString("passwordSalt"));
	        newUser.setPwHash(rs.getString("passwordHash"));
	        
	        con.close();
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return newUser;
	}
	
	public User getUserByEmail(String email) 
	{
		Connection con = null;
        ResultSet rs; 
        User newUser = null;
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();
        
	        String sqlString = "SELECT * FROM USERS WHERE email=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setString(1, email);
	        
	        rs = ps.executeQuery();
	        
	        if(!rs.next())
	        {
	            return null;
	        }
	        
	        newUser = new User();
	        newUser.setFirstname(rs.getString("firstname"));
	        newUser.setLastname(rs.getString("lastname"));
	        newUser.setEmail(rs.getString("email"));
	        newUser.setRole(rs.getInt("role"));
	        newUser.setPwSalt(rs.getString("passwordSalt"));
	        newUser.setPwHash(rs.getString("passwordHash"));
	        
	        con.close();
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return newUser;
	}
	
	public User getUserById(int id) 
	{
		Connection con = null;
        ResultSet rs; 
        User newUser = null;
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();
        
	        String sqlString = "SELECT * FROM USERS WHERE id=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setString(1, id);
	        
	        rs = ps.executeQuery();
	        
	        if(!rs.next())
	        {
	            return null;
	        }
	        
	        newUser = new User();
	        newUser.setFirstname(rs.getString("firstname"));
	        newUser.setLastname(rs.getString("lastname"));
	        newUser.setEmail(rs.getString("email"));
	        newUser.setRole(rs.getInt("role"));
	        newUser.setPwSalt(rs.getString("passwordSalt"));
	        newUser.setPwHash(rs.getString("passwordHash"));
	        
	        con.close();
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return newUser;
	}
	
	public User gePostingById(int id) 
	{
		Connection con = null;
        ResultSet rs; 
        User newUser = null;
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();
        
	        String sqlString = "SELECT * FROM POSTING WHERE id=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setString(1, id);
	        
	        rs = ps.executeQuery();
	        
	        if(!rs.next())
	        {
	            return null;
	        }
	        
	        newPosting = new Posting();
	        newPosting.setID(rs.getInt("ID"));
	        newPosting.setText(rs.getString("text"));
	        newPosting.setAuthorid(rs.getInt("authorid"));
	        newPosting.setSubjectid(rs.getInt("subjectid"));
	        newPosting.setWhendeleted(rs.getDate("whendeleted"));
	        newPosting.setWhenposted(rs.getDate("whenposted"));
	        
	        con.close();
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return newUser;
	}
	
	public static ArrayList<Posting> getPostingsBySubject(int subjectId) 
	{
		Connection con = null;
        ResultSet rs; 
        ArrayList<Posting> postings = new ArrayList<Posting>();
        
        try
        {
            con = MySQLDatabase.getInstance().getConnection();

	        String sqlString = "SELECT * FROM POSTING WHERE subjectid=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setInt(1, subjectId);
	        rs = ps.executeQuery();

	        while(rs.next())
	        {
	        	Posting posting = new Posting();
	        	posting.setId(rs.getInt("ID"));
	        	posting.setAuthorId(rs.getInt("authorid"));
	        	posting.setSubjectId(rs.getInt("subjectid"));
	        	posting.setText(rs.getString("text"));
	        	posting.setWhenposted(rs.getDate("whenposted"));
	        	posting.setWhendeleted(rs.getDate("whendeleted"));
	        	postings.add(posting);
	        }
	        
	        con.close();
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return postings;
	}
	
	public static boolean isEmailTaken(String email)
	{
		Connection con = null;
        ResultSet rs; 

        try
        {
            con = MySQLDatabase.getInstance().getConnection();
      
	        String sqlString = "SELECT * FROM USER WHERE email=?;";
	        
	        PreparedStatement ps = con.prepareStatement(sqlString);
	        ps.setString(1, email);
	        
	        rs = ps.executeQuery();
	        
	        while(!rs.next())
	        {
	        	return false;
	        }
	    }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return true;
	}
}