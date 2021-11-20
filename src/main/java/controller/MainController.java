package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyLib;

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = MyLib.checkCookies(request);
		if(userID == -1) {
			response.sendRedirect("login.tiles");
		}
		else {
			RequestDispatcher dispatcher;
			String action = request.getParameter("action");
			if(action == null) {
				dispatcher = request.getRequestDispatcher("viewContents.tiles");
			}
			else if(action.equals("editProfile")) {
				dispatcher = request.getRequestDispatcher("editProfile.tiles");
			}
			else if(action.equals("editContent")) {
				dispatcher = request.getRequestDispatcher("editContent.tiles");
			}
			else if(action.equals("addContent")) {
				dispatcher = request.getRequestDispatcher("addContent.tiles");
			}
			else if(action.equals("logout") || action.equals("login")) {
				if(action.equals("logout")) {
					Cookie[] cookies = request.getCookies();
					for(Cookie ck : cookies) {
						if(ck.getName().equals("userID")) {
							ck.setMaxAge(0);
							response.addCookie(ck);
						}
					}
				}	
				dispatcher = request.getRequestDispatcher("login.tiles");
			}
			else {
				dispatcher = request.getRequestDispatcher("viewContents.tiles");
			}
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
