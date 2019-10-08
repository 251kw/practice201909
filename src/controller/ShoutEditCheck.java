package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoutEditCheck
 */
@WebServlet("/ShoutEditCheck")
public class ShoutEditCheck extends HttpServlet {
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


		String writing = request.getParameter("writing");
		String shoutsId = request.getParameter("shoutsId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");

		String message;

		if (writing == null || writing.equals("")) {
			message = "叫びが入力されていません";
			request.setAttribute("alert", message);

			request.setAttribute("shoutsId", shoutsId);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);

			RequestDispatcher dispatch = request.getRequestDispatcher("ShoutEdit.jsp");
			dispatch.forward(request, response);

		}else {

			request.setAttribute("writing", writing);
			request.setAttribute("shoutsId", shoutsId);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);

			RequestDispatcher dispatch = request.getRequestDispatcher("shoutEditCheck.jsp");
			dispatch.forward(request, response);



		}



	}

}
