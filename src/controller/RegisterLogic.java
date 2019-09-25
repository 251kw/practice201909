package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class RegisterLogic
 */
@WebServlet("/RegisterLogic")
public class RegisterLogic extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

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


			try {

				DBManager dbm = new DBManager();
				dbm.register(loginId, userName, password, icon, profile);

			} catch (SQLException e) {
				e.printStackTrace();

			}


		RequestDispatcher dispatch = request.getRequestDispatcher("RegistrationComplete.jsp");
		dispatch.forward(request, response);
	}

}
