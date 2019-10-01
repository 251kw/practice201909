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
 * Servlet implementation class ShoutDelete
 */
@WebServlet("/ShoutDelete")
public class ShoutDelete extends HttpServlet {


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

		String shoutsId = request.getParameter("shoutsId");

		DBManager dbm = new DBManager();

		try {
			dbm.deleteShouts(shoutsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String nowLoginId = request.getParameter("nowLoginId");
		String nowLoginUser = request.getParameter("nowLoginUser");
		String nowLoginUserId = request.getParameter("nowLoginUserId");
		String nowLoginProfile = request.getParameter("nowLoginProfile");
		String nowLoginIcon = request.getParameter("nowLoginIcon");
		String nowLoginPassword = request.getParameter("nowLoginPassword");


		request.setAttribute("nowLoginId", nowLoginId);
		request.setAttribute("nowLoginUser", nowLoginUser);
		request.setAttribute("nowLoginUserId", nowLoginUserId);
		request.setAttribute("nowLoginProfile", nowLoginProfile);
		request.setAttribute("nowLoginIcon", nowLoginIcon);
		request.setAttribute("nowLoginPassword", nowLoginPassword);

		// 処理の転送先を ShoutsDeleteComplete.jsp に指定
		RequestDispatcher dispatch = request.getRequestDispatcher("ShoutsDeleteComplete.jsp");
		dispatch.forward(request, response);


	}

}
