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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBManager dbm = new DBManager();

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");

		String a = request.getParameter("delete");

		if("loginUserDelete".equals(a)) {

			try {

				dbm.delete(loginId);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("userName", userName);

			RequestDispatcher dispatch = request.getRequestDispatcher("LoginUserDeleteComplete.jsp");
			dispatch.forward(request, response);


		}

		try {

			dbm.delete(loginId);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("userName", userName);

		RequestDispatcher dispatch = request.getRequestDispatcher("DeleteComplete.jsp");
		dispatch.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
