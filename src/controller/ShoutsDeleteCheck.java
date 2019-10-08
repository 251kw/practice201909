package controller;

import java.io.IOException;
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
 * Servlet implementation class ShoutsDeleteCheck
 */
@WebServlet("/ShoutsDeleteCheck")
public class ShoutsDeleteCheck extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> list = new ArrayList<>();
		HttpSession session = request.getSession();


		//1個削除

		String shoutsId = request.getParameter("id");

		 list = dbm.getUserShout(shoutsId);

		 session.setAttribute("deleteShoutsInfo", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("ShoutsDeleteCheck.jsp");
			dispatch.forward(request, response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> list = new ArrayList<>();
		String message = "";
		HttpSession session = request.getSession();

		//複数削除

		String[] ids = request.getParameterValues("deleteShouts");

		if(ids == null) {

			message = "チェックされていません。もう一度やり直してください。";
			request.setAttribute("alert", message);


			RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
			dispatch.forward(request, response);

		}else {

			list = dbm.getUserShoutsteams(ids);

			session.setAttribute("deleteShoutsInfo", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("ShoutsDeleteCheck.jsp");
			dispatch.forward(request, response);



		}





	}



}
