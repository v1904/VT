package myapp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import beans.*;
import beans.ReportCard.Grade;

public class Service {
	private static final Logger log = Logger.getLogger(DAO.class);
	
	@SuppressWarnings("unchecked")
	public static Object createInstance(HttpServletRequest request, int id, Object temp)
	throws ClassNotFoundException
	{
		Object result = null;
		switch(request.getParameter("type"))
		{
			case "Student":
				Student student = new Student();
				student.setId(id+1);
				student.setName(request.getParameter("name"));
				student.setLastName(request.getParameter("lastName"));
				student.setAge(Integer.parseInt(request.getParameter("age")));
				student.setCoursesId(new ArrayList<Integer>());
				
					ReportCard rpc = new ReportCard();
					rpc.setStudent(student.getId());
					rpc.setGrades(new ArrayList<Grade>());
					DAO.CreateSourceReportCard(rpc);
				
				DAO.CreateSourceStudent(student);
				DAO.CreateUser("Student"+(id+1), "Student"+(id+1), id+1, MyUserStruct.MyStatus.Student);
				result = student;
				break;
			case "Teacher":
				Teacher teacher = new Teacher();
				teacher.setId(id+1);
				teacher.setName(request.getParameter("name"));
				teacher.setLastName(request.getParameter("lastName"));
				teacher.setAge(Integer.parseInt(request.getParameter("age")));
				teacher.setCoursesId(new ArrayList<Integer>());
				
				DAO.CreateSourceTeacher(teacher);
				DAO.CreateUser("Teacher"+(id+1), "Teacher"+(id+1), id+1, MyUserStruct.MyStatus.Teacher);
				result = teacher;
				break;
			case "Course":
				Course course = new Course();
				course.setId(id+1);
				course.setName(request.getParameter("name"));
				
				course.setSchedule(new Schedule());
				Schedule schedule = course.getSchedule();
				schedule.setDays(new Schedule.Day[7]);
				for(Schedule.daysOfWeek d : Schedule.daysOfWeek.values())
					schedule.getDays()[d.ordinal()] = schedule.new Day(false, d, 0, 0);
				
				course.setStudentsId(new ArrayList<Integer>());
				
				Teacher tch = null;
				for(Teacher t : (ArrayList<Teacher>)temp)
				{
					if(t.getId() == Integer.parseInt(request.getParameter("teacher")))
					{
						tch = t;
						break;
					}
				}
				
				course.setTeacherId(tch.getId());
				tch.getCoursesId().add(id+1);
				
				DAO.CreateSourceCourse(course);
				DAO.UpdateSourceTeacher(tch);
				result = course;
				break;
			default:
		}
		return result;
	}
	
	public static void UpdateStudent(HttpServletRequest request, Student std)
	{
		try 
		{
			std.setName(request.getParameter("name"));
			std.setLastName(request.getParameter("lastName"));
			std.setAge(Integer.parseInt(request.getParameter("age")));
			DAO.UpdateSourceStudent(std);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void UpdateTeacher(HttpServletRequest request, Teacher tch)
	{
		try 
		{
			tch.setName(request.getParameter("name"));
			tch.setLastName(request.getParameter("lastName"));
			tch.setAge(Integer.parseInt(request.getParameter("age")));
			DAO.UpdateSourceTeacher(tch);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void UpdateCourse(HttpServletRequest request, Course crs, ArrayList<Teacher> teachers)
	{
		try 
		{
			crs.setName(request.getParameter("name"));
			int tid = Integer.parseInt(request.getParameter("teacherId"));
			
			Teacher tch = null;
			for(Teacher t : teachers)
			{
				if(t.getId() == crs.getTeacherId())
				{
					tch = t;
					break;
				}
			}
			try
			{
				int i = 0;
				for(int c : tch.getCoursesId())
				{
					if(crs.getId() == c)
						tch.getCoursesId().remove(i);
					i++;
				}
			}
			catch(Exception ex) {log.error(ex.toString());}
			DAO.UpdateSourceTeacher(tch);
			
			tch = null;
			for(Teacher t : teachers)
			{
				if(t.getId() == tid)
				{
					tch = t;
					break;
				}
			}
			tch.getCoursesId().add(crs.getId());
			DAO.UpdateSourceTeacher(tch);
			
			crs.setTeacherId(tid);
			
			for(Schedule.daysOfWeek dow : Schedule.daysOfWeek.values())
			{
				String chb = request.getParameter(dow.name());
				if(chb != null)
				{
					crs.getSchedule().getDays()[dow.ordinal()].setChecked(true);
					crs.getSchedule().getDays()[dow.ordinal()].setHours(
							Integer.parseInt(request.getParameter(dow.name()+"h")));
					crs.getSchedule().getDays()[dow.ordinal()].setMinutes(
							Integer.parseInt(request.getParameter(dow.name()+"m")));
				}
				else
				{
					crs.getSchedule().getDays()[dow.ordinal()].setChecked(false);
					crs.getSchedule().getDays()[dow.ordinal()].setHours(0);
					crs.getSchedule().getDays()[dow.ordinal()].setMinutes(0);
				}
			}
			DAO.UpdateSourceCourse(crs);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void UpdateReportCards(HttpServletRequest request, ReportCard rpc)
	{
		try 
		{
			ReportCard.Grade g = rpc.new Grade();
			g.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			g.setGrade(Byte.parseByte(request.getParameter("grade")));
			g.setComment(request.getParameter("comment"));
			rpc.getGrades().add(g);
			DAO.UpdateSourceReportCard(rpc);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void UpdateReportCards(ReportCard rpc)
	{
		try 
		{
			DAO.UpdateSourceReportCard(rpc);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	
	public static void GetStudents(ArrayList<Student> std)
	{
		try 
		{
			DAO.LoadSourceStudent(std);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void GetTeachers(ArrayList<Teacher> tch)
	{
		try 
		{
			DAO.LoadSourceTeacher(tch);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void GetCourses(ArrayList<Course> crs)
	{
		try 
		{
			DAO.LoadSourceCourse(crs);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	public static void GetReportCards(ArrayList<ReportCard> rpc)
	{
		try 
		{
			DAO.LoadSourceReportCard(rpc);
		} 
		catch (ClassNotFoundException ex) {log.error(ex.toString());}
	}
	
	public static boolean AuthorizeMe(String login, String password, MyUserStruct struct)
	{
		if(DAO.LoadUser(login, password, struct))
			return true;
		else
			return false;
	}
	
	@SuppressWarnings("finally")
	public static void AddStudent(int courseId, int studentId, ArrayList<Course> courses, ArrayList<Student> students)
	{
		for(Course c : courses)
			if(c.getId() == courseId)
				if(!c.getStudentsId().contains(studentId))
				{
					c.getStudentsId().add(studentId);
					try
					{
						DAO.UpdateSourceCourse(c);
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
					finally { break; }
				}
		for(Student s : students)
			if(s.getId() == studentId)
				if(!s.getCoursesId().contains(courseId))
				{
					s.getCoursesId().add(courseId);
					try
					{
						DAO.UpdateSourceStudent(s);
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
					finally { break; }
				}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static void DeleteStudent(Student student, HttpSession session)
	{
		Object crs = session.getAttribute("courses");
		if(crs == null || !(crs instanceof ArrayList))
		{
			crs = new ArrayList<Course>();
			Service.GetCourses((ArrayList<Course>)crs);
			session.setAttribute("courses", crs);
		}
		
		for(Course c : (ArrayList<Course>)crs)
		{
			int counter = 0;
			for(Integer s : c.getStudentsId())
			{
				if(s == student.getId())
				{
					c.getStudentsId().remove(counter);
					try
					{
						DAO.UpdateSourceCourse(c);
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
					break;
				}
				counter++;
			}
		}
		
		try
		{
			session.removeAttribute("students");
			DAO.DeleteSourceStudent(student.getId());
			session.removeAttribute("repcards");
			DAO.DeleteSourceReportCard(student.getId());
			DAO.DeleteUser("Student"+student.getId());
		}
		catch(ClassNotFoundException ex) {log.error(ex.toString());}
	}
	@SuppressWarnings({ "unchecked" })
	public static void DeleteTeacher(Teacher teacher, HttpSession session)
	{
		Object crs = session.getAttribute("courses");
		if(crs == null || !(crs instanceof ArrayList))
		{
			crs = new ArrayList<Course>();
			Service.GetCourses((ArrayList<Course>)crs);
			session.setAttribute("courses", crs);
		}
		
		Object std = session.getAttribute("students");
		if(std == null || !(std instanceof ArrayList))
		{
			std = new ArrayList<Student>();
			Service.GetStudents((ArrayList<Student>)std);
			session.setAttribute("students", std);
		}
		
		if(((ArrayList<Teacher>)session.getAttribute("teachers")).size() > 1)
		{
			for(Course c : (ArrayList<Course>)crs)
				if(c.getTeacherId() == teacher.getId())
				{
					c.setTeacherId(((Teacher)(((ArrayList<Teacher>)session.getAttribute("teachers")).toArray())[0]).getId());
					try
					{
						DAO.UpdateSourceCourse(c);
						((Teacher)((ArrayList<Teacher>)session.getAttribute("teachers")).toArray()[0]).getCoursesId().add(c.getId());
						break;
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
				}
			session.removeAttribute("courses");
			
		}
		else
		{
			for(Student s : (ArrayList<Student>)std)
			{
				s.getCoursesId().clear();
				try {
					DAO.UpdateSourceStudent(s);
				} catch (ClassNotFoundException ex) {log.error(ex.toString());}
			}
			
			for(Course c : (ArrayList<Course>)crs)
				try {
					DAO.DeleteSourceCourse(c.getId());
				} catch (ClassNotFoundException ex) {log.error(ex.toString());}
			session.removeAttribute("courses");
		}
		
		try
		{
			session.removeAttribute("teachers");
			DAO.DeleteSourceTeacher(teacher.getId());
			DAO.DeleteUser("Teacher"+teacher.getId());
		}
		catch(ClassNotFoundException ex) {log.error(ex.toString());}
	}
	@SuppressWarnings({ "unchecked" })
	public static void DeleteCourse(Course course, HttpSession session)
	{
		Object tch = session.getAttribute("teachers");
		if(tch == null || !(tch instanceof ArrayList))
		{
			tch = new ArrayList<Teacher>();
			Service.GetTeachers((ArrayList<Teacher>)tch);
			session.setAttribute("teachers", tch);
		}
		
		Object std = session.getAttribute("students");
		if(std == null || !(std instanceof ArrayList))
		{
			std = new ArrayList<Student>();
			Service.GetStudents((ArrayList<Student>)std);
			session.setAttribute("students", std);
		}
		
		for(Student s : (ArrayList<Student>)std)
		{
			int counter = 0;
			for(Integer c : s.getCoursesId())
			{
				if(c == course.getId())
				{
					s.getCoursesId().remove(counter);
					try
					{
						DAO.UpdateSourceStudent(s);
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
					break;
				}
				counter++;
			}
		}
		
		for(Teacher t : (ArrayList<Teacher>)tch)
		{
			int counter = 0;
			for(Integer c : t.getCoursesId())
			{
				if(c == course.getId())
				{
					t.getCoursesId().remove(counter);
					try
					{
						DAO.UpdateSourceTeacher(t);
					}
					catch(ClassNotFoundException ex) {log.error(ex.toString());}
					break;
				}
				counter++;
			}
		}
		
		try
		{
			session.removeAttribute("coursess");
			DAO.DeleteSourceCourse(course.getId());
		}
		catch(ClassNotFoundException ex) {log.error(ex.toString());}
	}
}