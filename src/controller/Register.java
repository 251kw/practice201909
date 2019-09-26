package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");


		String loginId = (String)request.getParameter("loginId");
		String userName = (String)request.getParameter("userName");
		String password = (String)request.getParameter("password");
		String icon = (String)request.getParameter("icon");
		String profile = (String)request.getParameter("profile");
		String message = null;


		if(loginId == null || loginId.equals("")) {
			message = "ログインIDが未入力です";
			request.setAttribute("alert", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");
			dispatch.forward(request, response);

		}else if(userName == null || userName.equals("")) {
			message = "名前が未入力です";
			request.setAttribute("alert", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");
			dispatch.forward(request, response);

		}else if(password == null || password.equals("")) {
			message = "パスワードが未入力です";
			request.setAttribute("alert", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");
			dispatch.forward(request, response);

		}else if(profile == null || profile.equals("")) {
			message = "自己紹介が未入力です";
			request.setAttribute("alert", message);

			RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");
			dispatch.forward(request, response);

		}else {
			DBManager dbm = new DBManager();
			if(dbm.registerCheck(loginId)) {


				request.setAttribute("loginId", loginId);
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("icon", icon);
				request.setAttribute("profile", profile);

				RequestDispatcher dispatch = request.getRequestDispatcher("RegisterCheck.jsp");
				dispatch.forward(request, response);

			}else {

				message = "すでに使われているログインIDです";
				request.setAttribute("alert", message);

				RequestDispatcher dispatch = request.getRequestDispatcher("Register.jsp");
				dispatch.forward(request, response);

			}
		}
	}
}
