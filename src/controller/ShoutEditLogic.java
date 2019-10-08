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
 * Servlet implementation class shoutDeleted
 */
@WebServlet("/ShoutEditLogic")
public class ShoutEditLogic extends HttpServlet {
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

		DBManager dbm = new DBManager();


		String writing = request.getParameter("writing");
		String shoutsId = request.getParameter("shoutsId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");

		try {
			dbm.shoutEdit(shoutsId, writing);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("writing", writing);
		request.setAttribute("shoutsId", shoutsId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon", icon);


		RequestDispatcher dispatch = request.getRequestDispatcher("ShoutEditComplete.jsp");
		dispatch.forward(request, response);


	}

}
