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
import dto.UserDTO;

/**
 * Servlet implementation class SeachLogic
 */
@WebServlet("/SeachLogic")
public class SeachLogic extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		String userNameSearch = request.getParameter("userNameSearch");
		String loginIdSearch = request.getParameter("loginIdSearch");
		String profileSearch = request.getParameter("profileSearch");

		DBManager dbm = new DBManager();
		UserDTO user = new UserDTO();


		if(userNameSearch != null) {

			ArrayList<UserDTO> list = dbm.userNameSearch(userNameSearch);
				request.setAttribute("unSearchResult", list);

				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);

			}


		if(loginIdSearch != null) {

			ArrayList<UserDTO> list = dbm.userNameSearch(loginIdSearch);
			request.setAttribute("liSearchResult", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
		}

		if(profileSearch != null) {

			ArrayList<UserDTO> list = dbm.userNameSearch(profileSearch);
			request.setAttribute("pfSearchResult", list);

			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
