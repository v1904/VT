package myapp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.*;

import beans.*;

public class Controller extends HttpServlet 
{
	private static final Logger log = Logger.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;
	public Controller() { super(); }
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try
		{
			switch(request.getParameter("command"))
			{
				case "baseUpdate":
					readList(request, response);
					request.getRequestDispatcher("/showUpd")
					.forward(request, response);
					break;
				case "studentUpd":
					request.getRequestDispatcher("/studentUpd")
					.forward(request, response);
					break;
				case "teacherUpd":
					request.getRequestDispatcher("/teacherUpd")
					.forward(request, response);
					break;
				case "courseUpd":
					request.getRequestDispatcher("/courseUpd")
					.forward(request, response);
					break;
				case "repcardUpd":
					HttpSession session = request.getSession(true);
					Object rpc = session.getAttribute("repcards");
					if(rpc == null || !(rpc instanceof ArrayList))
					{
						rpc = new ArrayList<ReportCard>();
						Service.GetReportCards((ArrayList<ReportCard>)rpc);
						session.setAttribute("repcards", rpc);
					}
					
					request.getRequestDispatcher("/repcardUpd")
					.forward(request, response);
					break;
				case "studentHome":
					session = request.getSession(true);
					Object std = session.getAttribute("students");
					if(std == null || !(std instanceof ArrayList))
					{
						std = new ArrayList<Student>();
						Service.GetStudents((ArrayList<Student>)std);
						session.setAttribute("students", std);
					}
					
					Object crs = session.getAttribute("coursess");
					if(crs == null || !(crs instanceof ArrayList))
					{
						crs = new ArrayList<Course>();
						Service.GetCourses((ArrayList<Course>)crs);
						session.setAttribute("coursess", crs);
					}
					
					if(session.getAttribute("status").toString().equals("Student"))
						request.getRequestDispatcher("/studentHome")
						.forward(request, response);
					else
						request.getRequestDispatcher("/errorPage")
						.forward(request, response);
					break;
				case "teacherHome":
					session = request.getSession(true);
					Object tch = session.getAttribute("teachers");
					if(tch == null || !(tch instanceof ArrayList))
					{
						tch = new ArrayList<Teacher>();
						Service.GetTeachers((ArrayList<Teacher>)tch);
						session.setAttribute("teachers", tch);
					}
					
					crs = session.getAttribute("coursess");
					if(crs == null || !(crs instanceof ArrayList))
					{
						crs = new ArrayList<Course>();
						Service.GetCourses((ArrayList<Course>)crs);
						session.setAttribute("coursess", crs);
					}
					
					if(session.getAttribute("status").toString().equals("Teacher"))
						request.getRequestDispatcher("/teacherHome")
						.forward(request, response);
					else
						request.getRequestDispatcher("/errorPage")
						.forward(request, response);
					break;
				case "request":
					session = request.getSession(true);
					std = session.getAttribute("students");
					if(std == null || !(std instanceof ArrayList))
					{
						std = new ArrayList<Student>();
						Service.GetStudents((ArrayList<Student>)std);
						session.setAttribute("students", std);
					}
					
					crs = session.getAttribute("coursess");
					if(crs == null || !(crs instanceof ArrayList))
					{
						crs = new ArrayList<Course>();
						Service.GetCourses((ArrayList<Course>)crs);
						session.setAttribute("coursess", crs);
					}
					
					if(session.getAttribute("status").toString().equals("Student"))
					{
						Service.AddStudent(Integer.parseInt(request.getParameter("id")),
								(Integer)session.getAttribute("userId"),
								(ArrayList<Course>)session.getAttribute("coursess"),
								(ArrayList<Student>)session.getAttribute("students"));
						request.getRequestDispatcher("/index")
						.forward(request, response);
					}
					else
						request.getRequestDispatcher("/errorPage")
						.forward(request, response);
					break;
				case "delete":
					deleteList(request, response);
					break;
				default:
					PrintWriter out = response.getWriter();
					out.println("GETGETGETGETGET");
			}
		}
		catch(Exception ex) {log.error(ex.toString());}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		try
		{
			switch(request.getParameter("command"))
			{
				case "create":
					createList(request, response);
					request.getRequestDispatcher("/show")
					.forward(request, response);
					break;
				case "read":
					readList(request, response);
					request.getRequestDispatcher("/show")
					.forward(request, response);
					break;
				case "update":
					updateList(request, response);
					break;
				case "authorize":
					authorizeFunc(request, response);
					break;
				case "local":
					request.getSession(true).setAttribute("local", request.getParameter("local"));
					request.getRequestDispatcher("/index").forward(request, response);
					break;
				default:
					PrintWriter out = response.getWriter();
					out.println("Error: unknown command!");
			}
		}
		catch(Exception ex) {log.error(ex.toString());}
	}
	
	@SuppressWarnings("unchecked")
	protected void createList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		switch(request.getParameter("type"))
		{
			case "Student":
				HttpSession session = request.getSession(true);
				Object temp = session.getAttribute("students");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Student>();
					Service.GetStudents((ArrayList<Student>)temp);
				}
				
				ArrayList<Student> s = (ArrayList<Student>)temp;
				Object o = Service.createInstance(request, s.size() == 0 ? 0 : ((Student)s.toArray()[s.size()-1]).getId(), null);
				if(o != null)
					((ArrayList<Student>)temp).add((Student)o);
				
				session.setAttribute("students", temp);
				break;
			case "Teacher":
				session = request.getSession(true);
				temp = session.getAttribute("teachers");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Teacher>();
					Service.GetTeachers((ArrayList<Teacher>)temp);
				}
				
				ArrayList<Teacher> t = (ArrayList<Teacher>)temp;
				o = Service.createInstance(request, t.size() == 0 ? 0 : ((Teacher)t.toArray()[t.size()-1]).getId(), null);
				if(o != null)
					((ArrayList<Teacher>)temp).add((Teacher)o);

				session.setAttribute("teachers", temp);
				break;
			case "Course":
				session = request.getSession(true);
				Object tch = session.getAttribute("teachers");
				if(tch == null || !(tch instanceof ArrayList))
				{
					tch = new ArrayList<Teacher>();
					Service.GetTeachers((ArrayList<Teacher>)tch);
					session.setAttribute("teachers", tch);
				}
				
				
				session = request.getSession(true);
				temp = session.getAttribute("coursess");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Course>();
					Service.GetCourses((ArrayList<Course>)temp);
				}
				
				ArrayList<Course> c = (ArrayList<Course>)temp;
				o = Service.createInstance(request, c.size() == 0 ? 0 : ((Course)c.toArray()[c.size()-1]).getId(), tch);
				if(o != null)
				{
					try
					{
						((ArrayList<Course>)temp).add((Course)o);
					}
					catch(Exception ex) {log.error(ex.toString());}
				}
				
				session.setAttribute("coursess", temp);
				break;
			default:
				PrintWriter out = response.getWriter();
				out.println("Error: unknown type!");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void readList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		switch(request.getParameter("type"))
		{
			case "Student":
				HttpSession session = request.getSession(true);
				Object temp = session.getAttribute("students");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Student>();
					Service.GetStudents((ArrayList<Student>)temp);
					session.setAttribute("students", temp);
				}
				break;
			case "Teacher":
				session = request.getSession(true);
				temp = session.getAttribute("teachers");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Teacher>();
					Service.GetTeachers((ArrayList<Teacher>)temp);
					session.setAttribute("teachers", temp);
				}
				break;
			case "Course":
				session = request.getSession(true);
				temp = session.getAttribute("coursess");
				if(temp == null || !(temp instanceof ArrayList)	)
				{
					temp = new ArrayList<Course>();
					Service.GetCourses((ArrayList<Course>)temp);
					session.setAttribute("coursess", temp);
				}
				break;
			default:
				PrintWriter out = response.getWriter();
				out.println("Error: unknown type!");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void updateList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		switch(request.getParameter("type"))
		{
			case "Student":
				HttpSession session = request.getSession(true);
				Object temp = session.getAttribute("students");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Student>();
					Service.GetStudents((ArrayList<Student>)temp);
				}
				
				for(Student s : ((ArrayList<Student>)temp))
					if(s.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.UpdateStudent(request, s);
						break;
					}
				
				session.setAttribute("students", temp);
				request.getRequestDispatcher("/showUpd")
				.forward(request, response);
				break;
			case "Teacher":
				session = request.getSession(true);
				temp = session.getAttribute("teachers");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Teacher>();
					Service.GetTeachers((ArrayList<Teacher>)temp);
				}
				
				for(Teacher t : ((ArrayList<Teacher>)temp))
					if(t.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.UpdateTeacher(request, t);
						break;
					}
				
				session.setAttribute("teachers", temp);
				request.getRequestDispatcher("/showUpd")
				.forward(request, response);
				break;
			case "Course":
				session = request.getSession(true);
				temp = session.getAttribute("course");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<Course>();
					Service.GetCourses((ArrayList<Course>)temp);
				}
				
				for(Course c : ((ArrayList<Course>)temp))
					if(c.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.UpdateCourse(request, c, (ArrayList<Teacher>)session.getAttribute("teachers"));
						break;
					}
				
				session.setAttribute("coursess", temp);
				request.getRequestDispatcher("/showUpd")
				.forward(request, response);
				break;
			case "ReportCard":
				session = request.getSession(true);
				temp = session.getAttribute("repcards");
				if(temp == null || !(temp instanceof ArrayList))
				{
					temp = new ArrayList<ReportCard>();
					Service.GetReportCards((ArrayList<ReportCard>)temp);
					session.setAttribute("repcards", temp);	
				}	
				
				for(ReportCard r : ((ArrayList<ReportCard>)temp))
					if(r.getStudentId() == Integer.parseInt(request.getParameter("studentId")))
					{
						Service.UpdateReportCards(request, r);
						break;
					}
				
				session.setAttribute("repcards", temp);
				request.getRequestDispatcher("/repcardUpd")
				.forward(request, response);
				break;
			default:
				PrintWriter out = response.getWriter();
				out.println("Error: unknown type!");
		}
	}
	
	protected void authorizeFunc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException 
	{
		HttpSession session = request.getSession(true);
		MyUserStruct struct = new MyUserStruct();
		struct.id = 0;
		struct.status = MyUserStruct.MyStatus.Guest;
		
		if(Service.AuthorizeMe(request.getParameter("login"), request.getParameter("password"), struct))
		{
			session.setAttribute("status", struct.status.name());
			session.setAttribute("userId", struct.id);
			request.getRequestDispatcher("/index")
			.forward(request, response);
		}
		else
		{

        	session.setAttribute("status", MyUserStruct.MyStatus.Error);
			request.getRequestDispatcher("/authorize")
			.forward(request, response);
		}
	}
	@SuppressWarnings("unchecked")
	protected void deleteList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		switch(request.getParameter("type"))
		{
			case "Student":
				HttpSession session = request.getSession(true);
				
				ArrayList<Student> students =(ArrayList<Student>)session.getAttribute("students");
				for(Student s : students)
					if(s.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.DeleteStudent(s, session);
						students.remove(s);
						break;
					}
				
				session.setAttribute("students", students);
				request.getRequestDispatcher("/show")
				.forward(request, response);
				break;
			case "Teacher":
				session = request.getSession(true);
				
				ArrayList<Teacher> teachers =(ArrayList<Teacher>)session.getAttribute("teachers");
				for(Teacher t : teachers)
					if(t.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.DeleteTeacher(t, session);
						teachers.remove(t);
						break;
					}
				
				session.setAttribute("teachers", teachers);
				request.getRequestDispatcher("/show")
				.forward(request, response);
				break;
			case "Course":
				session = request.getSession(true);
				
				ArrayList<Course> courses =(ArrayList<Course>)session.getAttribute("coursess");
				for(Course c : courses)
					if(c.getId() == Integer.parseInt(request.getParameter("id")))
					{
						Service.DeleteCourse(c, session);
						courses.remove(c);
						break;
					}
				
				session.setAttribute("coursess", courses);
				request.getRequestDispatcher("/show")
				.forward(request, response);
				break;
			case "Grade":
				session = request.getSession(true);
				exit:
				for(ReportCard rc : (ArrayList<ReportCard>)session.getAttribute("repcards"))
					if(rc.getStudentId() == Integer.parseInt(request.getParameter("id")))
						for(ReportCard.Grade g : rc.getGrades())
							if(g.getCourseId() == Integer.parseInt(request.getParameter("courseId")))
							{
								rc.getGrades().remove(g);
								Service.UpdateReportCards(rc);
								break exit;
							}
					
				request.getRequestDispatcher("/repcardUpd")
				.forward(request, response);
				break;
			default:
				PrintWriter out = response.getWriter();
				out.println("Error: unknown type!");
		}
	}
}