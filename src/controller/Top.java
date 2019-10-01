package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.ShoutDTO;

/**
 * Servlet implementation class Top
 */
@WebServlet("/Top")
public class Top extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

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



		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> shoutsList = new ArrayList<>();

		shoutsList = dbm.getShoutList();

		request.setAttribute("shouts", shoutsList);

		RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
		dispatch.forward(request, response);




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

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



		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> shoutsList = new ArrayList<>();

		shoutsList = dbm.getShoutList();

		request.setAttribute("shouts", shoutsList);

		RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
		dispatch.forward(request, response);

	}

}
