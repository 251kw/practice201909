package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AllSelect
 */
@WebServlet("/AllSelect")
public class AllSelect extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		String select = request.getParameter("select");
		String stResult = request.getParameter("stResult");
		String idResult = request.getParameter("idResult");

		if("select".equals(select)) {

			request.setAttribute("stResult", stResult);
			request.setAttribute("idResult", idResult);

			RequestDispatcher dispatch = request.getRequestDispatcher("SeachLogic");
			dispatch.forward(request, response);

		}else if("Lifted".equals(select)){

			request.setAttribute("stResult", stResult);
			request.setAttribute("idResult", idResult);

			RequestDispatcher dispatch = request.getRequestDispatcher("SeachLogic");
			dispatch.forward(request, response);

		}else if("allDelete".equals(select)) {

			request.setAttribute("checked", "checked");

			request.setAttribute("stResult", stResult);
			request.setAttribute("idResult", idResult);

			RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
			dispatch.forward(request, response);


		}else if("notAll".equals(select)){

			request.setAttribute("stResult", stResult);
			request.setAttribute("idResult", idResult);

			RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
			dispatch.forward(request, response);


		}


	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



	}

}
