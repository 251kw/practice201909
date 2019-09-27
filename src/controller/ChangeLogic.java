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
 * Servlet implementation class ChangeLogic
 */
@WebServlet("/ChangeLogic")
public class ChangeLogic extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");


		try {
			DBManager dbm = new DBManager();
			dbm.change(loginId, userName, password, icon, profile, userId);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);

		//ChangeComplete.jspに転送
		RequestDispatcher dispatch = request.getRequestDispatcher("ChangeComplete.jsp");
		dispatch.forward(request, response);

	}

}
