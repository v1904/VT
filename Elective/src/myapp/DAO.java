package myapp;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import java.sql.*;
import beans.*;
import beans.ReportCard.Grade;
import beans.Schedule.Day;

public class DAO
{    
	private static final Logger log = Logger.getLogger(DAO.class);
	static public void CreateSourceStudent(Student std) throws ClassNotFoundException
	{
		String query = "insert into students values (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "students", null);
			if (!tables.next()) 
			{
				tables.close();
				Statement stmt = conn.createStatement();
			    String sql = "CREATE TABLE students " +
			                 "(id INTEGER not NULL, " +
			                 " name VARCHAR(255), " + 
			                 " lastname VARCHAR(255), " + 
			                 " age INTEGER, " + 
			                 " courses VARCHAR(255), " +
			                 " PRIMARY KEY ( id ))"; 
			    stmt.executeUpdate(sql);
			    stmt.close();
			}
			pstt = conn.prepareStatement(query);
			
			pstt.setInt(1, std.getId());
            pstt.setString(2, std.getName());
            pstt.setString(3, std.getLastName());
            pstt.setInt(4, std.getAge());
            String temp = "";
            for(int c : std.getCoursesId())
            	temp += c + " ";
            pstt.setString(5, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());;
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void UpdateSourceStudent(Student std) throws ClassNotFoundException
	{
		String query = "update students set name=?, lastname=?, age=?, courses=? where id=" + std.getId();
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "students", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				CreateSourceStudent(std);
				return;
			}
			pstt = conn.prepareStatement(query);
			
			pstt.setString(1, std.getName());
            pstt.setString(2, std.getLastName());
            pstt.setInt(3, std.getAge());
            String temp = "";
            for(int c : std.getCoursesId())
            	temp += c + " ";
            pstt.setString(4, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void DeleteSourceStudent(Integer std) throws ClassNotFoundException
	{
		String query = "delete from students where id=" + std;
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "students", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				return;
			}
			pstt = conn.prepareStatement(query);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void LoadSourceStudent(ArrayList<Student> list) throws ClassNotFoundException
	{
		String query = "select id, name, lastname, age, courses from students order by id";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rest = stmt.executeQuery(query);

            while (rest.next()) 
            {
                int id = rest.getInt(1);
                String name = rest.getString(2);
                String lastName = rest.getString(3);
                int age = rest.getInt(4);
                ArrayList<Integer> coursesId = new ArrayList<Integer>();
                for(String c : rest.getString(5).split(" "))
                	if(c.length() > 0)
                		coursesId.add(Integer.parseInt(c));
                Student std = new Student();
                std.setId(id);
                std.setName(name);
                std.setLastName(lastName);
                std.setAge(age);
                std.setCoursesId(coursesId);
                
                list.add(std);
            }
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { stmt.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { rest.close(); } catch(Exception ex) { log.error(ex.toString()); }
        }
	}
	
	static public void CreateSourceTeacher(Teacher tch) throws ClassNotFoundException
	{
		String query = "insert into teachers values (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "teachers", null);
			if (!tables.next()) 
			{
				tables.close();
				Statement stmt = conn.createStatement();
			    String sql = "CREATE TABLE teachers " +
			                 "(id INTEGER not NULL, " +
			                 " name VARCHAR(255), " + 
			                 " lastname VARCHAR(255), " + 
			                 " age INTEGER, " + 
			                 " courses VARCHAR(255), " +
			                 " PRIMARY KEY ( id ))"; 
			    stmt.executeUpdate(sql);
			    stmt.close();
			}
			
			pstt = conn.prepareStatement(query);
         
            pstt.setInt(1, tch.getId());
            pstt.setString(2, tch.getName());
            pstt.setString(3, tch.getLastName());
            pstt.setInt(4, tch.getAge());
            String temp = "";
            for(int c : tch.getCoursesId())
            	temp += c + " ";
            pstt.setString(5, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());;
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void UpdateSourceTeacher(Teacher tch) throws ClassNotFoundException
	{
		String query = "update teachers set name=?, lastname=?, age=?, courses=? where id=" + tch.getId();
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "teachers", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				CreateSourceTeacher(tch);
				return;
			}
			pstt = conn.prepareStatement(query);
			
			pstt.setString(1, tch.getName());
            pstt.setString(2, tch.getLastName());
            pstt.setInt(3, tch.getAge());
            String temp = "";
            for(int c : tch.getCoursesId())
            	temp += c + " ";
            pstt.setString(4, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void DeleteSourceTeacher(Integer tch) throws ClassNotFoundException
	{
		String query = "delete from teachers where id=" + tch;
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "teachers", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				return;
			}
			pstt = conn.prepareStatement(query);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void LoadSourceTeacher(ArrayList<Teacher> list) throws ClassNotFoundException
	{
		String query = "select id, name, lastname, age, courses from teachers order by id";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rest = stmt.executeQuery(query);

            while (rest.next()) 
            {
                int id = rest.getInt(1);
                String name = rest.getString(2);
                String lastName = rest.getString(3);
                int age = rest.getInt(4);
                ArrayList<Integer> coursesId = new ArrayList<Integer>();
                for(String c : rest.getString(5).split(" "))
                	if(c.length() > 0)
                		coursesId.add(Integer.parseInt(c));
                Teacher tch = new Teacher();
                tch.setId(id);
                tch.setName(name);
                tch.setLastName(lastName);
                tch.setAge(age);
                tch.setCoursesId(coursesId);
                
                list.add(tch);
            }
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { stmt.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { rest.close(); } catch(Exception ex) { log.error(ex.toString()); }
        }
	}
	
	static public void CreateSourceCourse(Course crs) throws ClassNotFoundException
	{
		String query = "insert into courses values (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "courses", null);
			if (!tables.next()) 
			{
				tables.close();
				Statement stmt = conn.createStatement();
			    String sql = "CREATE TABLE courses " +
			                 "(id INTEGER not NULL, " +
			                 " name VARCHAR(255), " + 
			                 " schedule VARCHAR(255), " + 
			                 " teacherid INTEGER, " + 
			                 " studentsid VARCHAR(255), " + 
			                 " PRIMARY KEY ( id ))"; 
			    stmt.executeUpdate(sql);
			    stmt.close();
			}
			pstt = conn.prepareStatement(query);
         
            pstt.setInt(1, crs.getId());
            pstt.setString(2, crs.getName());
            String temp = "";
            for(Schedule.Day d : crs.getSchedule().getDays())
            	if(d.isChecked())
            		temp = d + ",";
            pstt.setString(3, temp);
            pstt.setInt(4, crs.getTeacherId());
            temp = "";
            for(Integer s : crs.getStudentsId())
            	temp += s + " ";
            pstt.setString(5, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void UpdateSourceCourse(Course crs) throws ClassNotFoundException
	{
		String query = "update courses set name=?, schedule=?, teacherid=?, studentsid=? where id=" + crs.getId();
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "courses", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				CreateSourceCourse(crs);
				return;
			}
			pstt = conn.prepareStatement(query);
	         
            pstt.setString(1, crs.getName());
            String temp = "";
            for(Schedule.Day d : crs.getSchedule().getDays())
            	if(d.isChecked())
            		temp += d + ",";
            pstt.setString(2, temp);
            pstt.setInt(3, crs.getTeacherId());
            temp = "";
            for(Integer s : crs.getStudentsId())
            	temp += s + " ";
            pstt.setString(4, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void DeleteSourceCourse(Integer crs) throws ClassNotFoundException
	{
		String query = "delete from courses where id=" + crs;
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "courses", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				return;
			}
			pstt = conn.prepareStatement(query);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void LoadSourceCourse(ArrayList<Course> list) throws ClassNotFoundException
	{
		String query = "select id, name, schedule, teacherid, studentsid from courses order by id";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rest = stmt.executeQuery(query);

            while (rest.next()) 
            {
                int id = rest.getInt(1);
                String name = rest.getString(2);
                
                Schedule schedule = new Schedule();	
                Day[] days = new Day[7];
                for(Schedule.daysOfWeek d : Schedule.daysOfWeek.values())
                	days[d.ordinal()] = schedule.new Day(false, d, 0, 0);
                
                for(String c : rest.getString(3).split(","))
                {
                	String[] temp = c.split(" ");
                	if(temp.length == 3)
                	{
                		Schedule.daysOfWeek day = 
                				Schedule.daysOfWeek.valueOf(temp[0]);
                		days[day.ordinal()].setChecked(true);
                		days[day.ordinal()].setHours(Integer.parseInt(temp[1]));
                		days[day.ordinal()].setMinutes(Integer.parseInt(temp[2]));
                	}
                }
                schedule.setDays(days);
                int teacherId = rest.getInt(4);
                ArrayList<Integer> studentsId = new ArrayList<Integer>(); 
                for(String s : rest.getString(5).split(" "))
                	if(s.length() > 0)
                		studentsId.add(Integer.parseInt(s));
                
                Course crs = new Course();
                crs.setId(id);
                crs.setName(name);
                crs.setSchedule(schedule);
                crs.setTeacherId(teacherId);
                crs.setStudentsId(studentsId);
                
                list.add(crs);
            }
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { stmt.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { rest.close(); } catch(Exception ex) { log.error(ex.toString()); }
        }
	}
	
	static public void CreateSourceReportCard(ReportCard rpc) throws ClassNotFoundException
	{
		String query = "insert into reportcards values (?, ?)";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "reportcards", null);
			if (!tables.next()) 
			{
				tables.close();
				Statement stmt = conn.createStatement();
			    String sql = "CREATE TABLE reportcards " +
			                 "(studentid INTEGER not NULL, " +
			                 " grades VARCHAR(255), " + 
			                 " PRIMARY KEY ( studentid ))"; 
			    stmt.executeUpdate(sql);
			    stmt.close();
			}
			pstt = conn.prepareStatement(query);
         
            pstt.setInt(1, rpc.getStudentId());
            String temp = "";
            for(Grade g : rpc.getGrades())
            	temp += g + ",";
            pstt.setString(2, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void UpdateSourceReportCard(ReportCard rpc) throws ClassNotFoundException
	{
		String query = "update reportcards set studentid=?, grades=? where studentid=" + rpc.getStudentId();
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "reportcards", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				CreateSourceReportCard(rpc);
				return;
			}
			pstt = conn.prepareStatement(query);
         
            pstt.setInt(1, rpc.getStudentId());
            String temp = "";
            for(Grade g : rpc.getGrades())
            	temp += g + ",";
            
            pstt.setString(2, temp);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void DeleteSourceReportCard(Integer rpc) throws ClassNotFoundException
	{
		String query = "delete from reportcards where studentid=" + rpc;
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "reportcards", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				return;
			}
			pstt = conn.prepareStatement(query);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void LoadSourceReportCard(ArrayList<ReportCard> list) throws ClassNotFoundException
	{
		String query = "select studentid, grades from reportcards order by studentid";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rest = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.createStatement();
            rest = stmt.executeQuery(query);
            
            while (rest.next()) 
            {
            	int studentId = rest.getInt(1);
                ArrayList<Grade> grades = new ArrayList<Grade>();
                ReportCard rpc = new ReportCard();	
                for(String c : rest.getString(2).split(","))
                {
                	String[] temp = c.split(" ");
                	if(temp.length == 3) 
                	{
	                	Grade grade = rpc.new Grade();
	                	grade.setCourseId(Integer.parseInt(temp[0]));
	                	grade.setGrade(Byte.parseByte(temp[1]));
	                	grade.setComment(temp[2]);
	                	grades.add(grade);
                	}
                }
                rpc.setStudent(studentId);
                rpc.setGrades(grades);
                
                list.add(rpc);
            }
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { stmt.close(); } catch(Exception ex) { log.error(ex.toString()); }
            try { rest.close(); } catch(Exception ex) { log.error(ex.toString()); }
        }
	}
	
	@SuppressWarnings("finally")
	static public boolean LoadUser(String login, String password, MyUserStruct struct) 
	{
		String query = "select status, id from users where login=? and password=?";
		Connection conn = null;
		PreparedStatement pstt = null;
		boolean exitSt = false;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "users", null);
			if (!tables.next()) 
				return false;
			
			pstt = conn.prepareStatement(query);
         
            pstt.setString(1, login);            
            pstt.setString(2, password);
            
            ResultSet rest = pstt.executeQuery();
            if(rest.next())
            {
            	struct.status = MyUserStruct.MyStatus.valueOf(rest.getString(1));
            	struct.id = rest.getInt(2);
            	exitSt = true;
            }
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); return false; }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); return false; }
            return exitSt;
        }
	}
	static public void CreateUser(String login, String password, Integer id, MyUserStruct.MyStatus status)
	{
		String query = "insert into users values (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "users", null);
			if (!tables.next()) 
			{
				tables.close();
				Statement stmt = conn.createStatement();
			    String sql = "CREATE TABLE users " +
			                 "(login VARCHAR(255), " +
			                 " password VARCHAR(255), " + 
			                 " id INTEGER, " +
			                 " status VARCHAR(255), " + 
			                 " PRIMARY KEY ( login ))"; 
			    stmt.executeUpdate(sql);
			    stmt.close();
			}
			pstt = conn.prepareStatement(query);
         
			pstt.setString(1, login);
			pstt.setString(2, password);
            pstt.setInt(3, id);
            pstt.setString(4, status.name());
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
	static public void DeleteUser(String usr) throws ClassNotFoundException
	{
		String query = "delete from users where login=?";
		Connection conn = null;
		PreparedStatement pstt = null;
		try 
		{
			conn = ConnectionPool.getInstance().getConnection();
			
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "users", null);
			if (!tables.next()) 
			{
				tables.close();
				conn.close();
				return;
			}
			pstt = conn.prepareStatement(query);
			
			pstt.setString(1, usr);
            pstt.executeUpdate();
        } 
		catch (SQLException ex) 
		{
			log.error(ex.toString());
        } 
		finally 
		{
            try { conn.close(); } catch(SQLException ex) { log.error(ex.toString()); }
            try { pstt.close(); } catch(SQLException ex) { log.error(ex.toString()); }
        }
	}
}