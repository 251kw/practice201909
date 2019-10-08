package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;

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
		HttpSession session = request.getSession();
		ArrayList<ShoutDTO> list = new ArrayList<>();
		DBManager dbm = new DBManager();

		list = (ArrayList<ShoutDTO>)session.getAttribute("deleteShoutsInfo");



		try {
			dbm.deleteShoutslist(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 処理の転送先を ShoutsDeleteComplete.jsp に指定
		RequestDispatcher dispatch = request.getRequestDispatcher("ShoutsDeleteComplete.jsp");
		dispatch.forward(request, response);


	}

}
