package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constants;
import common.LogRes;
import dao.MemberDAO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("MainController?action=login").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean remember = request.getParameter("rememberMe") != null;
		Integer id = MemberDAO.loginMember(email, password);
		RequestDispatcher dispatcher;
		if (id != -1)
		{
			Constants.idMember = id;
			//session.setAttribute("userID", id);
			
			if(remember) {
				Cookie ckUserID = new Cookie("userID", id.toString());
				ckUserID.setMaxAge(20);
				response.addCookie(ckUserID);
			}
			
			dispatcher = request.getRequestDispatcher("viewContents.tiles");
		}
		else 
		{
			request.setAttribute(LogRes.requestResult, LogRes.wrongAccount);
			dispatcher = request.getRequestDispatcher("login.tiles");
		}
		dispatcher.forward(request, response);
		
	}

}
