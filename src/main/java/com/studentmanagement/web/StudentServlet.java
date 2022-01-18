package com.studentmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studentmanagement.bean.Student;
import com.studentmanagement.dao.StudentDao;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentDao studentDao;
	
	//private String query="";
	
    public StudentServlet() {
        super();
        
    }

	
	public void init() throws ServletException {
		studentDao=new StudentDao();
		
	}
	
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		
		String myAction=request.getServletPath();
		switch(myAction)
		{
		case "/new":
			showNewForm(request,response);
			break;
				
		case "/insert":
			try {
				insertStudent(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			break;
			
		case "/delete":
			try {
				deleteStudent(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			break;
			
		case "/edit":
			try {
				displayEditStudentForm(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			break;
			
		case "/update":
			try {
				updateStudent(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			break;
			
				
			
		default:
			try {
				listStudent(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			break;					
		}	
		}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		RequestDispatcher dispatcher=request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request,response);
	}
	
	//insert student

   private void insertStudent(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException
   {
	   
	   String name=request.getParameter("name");
	   String dob=request.getParameter("dob");
	   String doj=request.getParameter("doj");
	   
	   Student newStudent=new Student(name,dob,doj);
	  studentDao.insertStudent( newStudent);
	   response.sendRedirect("list");
	   	    
	  }
   
   //delete student
   private void deleteStudent(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException 
   {
	   int id=Integer.parseInt(request.getParameter("id"));
	   try {
		   studentDao.deleteStudent(id);
	   } catch (Exception e) {
		   e.printStackTrace();
		}
	   response.sendRedirect("list");   
	   
   }
   
  
   private void displayEditStudentForm(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException  {
	   int id=Integer.parseInt(request.getParameter("id"));
	   Student existingStudent;
	   try {
		   existingStudent=studentDao.selectStudent(id);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("student-form.jsp");
		   request.setAttribute("student",  existingStudent);
		   dispatcher.forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
   }
   
   //update Student
   private void updateStudent(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException  {
	   int id=Integer.parseInt(request.getParameter("id"));
	   String name=request.getParameter("name");
	   String dob=request.getParameter("dob");
	   String doj=request.getParameter("doj");
	   
	   Student student=new Student(id,name,dob,doj);
	   studentDao.updateStudent(student);
	   response.sendRedirect("list");
	   
   }
   
   
   
     
   //default
   
   private void listStudent(HttpServletRequest request, HttpServletResponse response)throws SQLException,ServletException,IOException
   {
	   try {
		   List<Student> listStudent=studentDao.selectAllStudents();
		   request.setAttribute("listStudent",  listStudent);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("student-list.jsp");
		   dispatcher.forward(request, response);
		   
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	   
	   }
   
 }






