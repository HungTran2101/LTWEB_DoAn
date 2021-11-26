package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.LogRes;
import dao.MemberDAO;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		RequestDispatcher dispatcher;
		
		if (MemberDAO.findExistedEmail(email)) {
			request.setAttribute(LogRes.requestResult, LogRes.existedEmail);
			dispatcher = request.getRequestDispatcher("register.tiles");
		}		
		else if (MemberDAO.registerMember(username, email, password))
		{
			response.sendRedirect("login.tiles");
			return;
		}
		else {
			request.setAttribute(LogRes.requestResult, LogRes.registerFailed);
			dispatcher = request.getRequestDispatcher("register.tiles");
		}
		dispatcher.forward(request, response);
	}

}
