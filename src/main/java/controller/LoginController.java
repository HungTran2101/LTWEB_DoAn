package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if(action == null || action.equals("login")) {
			int userID = checkCookies(request);
			if(userID == -1) {
				response.sendRedirect("login.tiles");
			}
			else {
				Constants.idMember = userID;
				session.setAttribute("userID", userID);
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewContents.tiles");
				dispatcher.forward(request, response);
			}
		}
		else if(action.equals("logout")) {
			session.removeAttribute("userID");
			Cookie[] cookies = request.getCookies();
			for(Cookie ck : cookies) {
				if(ck.getName().equals("userID")) {
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
			}
			request.getRequestDispatcher("login.tiles").forward(request, response);
		}
	}
	
	private int checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return -1;
		}
		else {
			int userID = -1;
			for(Cookie ck : cookies) {
				if(ck.getName().equals("userID"))
					userID = Integer.parseInt(ck.getValue());
			}
			return userID;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean remember = request.getParameter("rememberMe") != null;
		int id = MemberDAO.loginMember(email, password);
		RequestDispatcher dispatcher;
		if (id != -1)
		{
			Constants.idMember = id;
			session.setAttribute("userID", id);
			
			if(remember) {
				Cookie ckUserID = new Cookie("userID", String.valueOf(id));
				ckUserID.setMaxAge(3600);
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
