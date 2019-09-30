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
import dto.UserDTO;

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


		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> shoutsList = new ArrayList<>();
		ArrayList<UserDTO> userList = new ArrayList<>();

		shoutsList = dbm.getShoutList();

		request.setAttribute("shouts", shoutsList);

		String loginId = request.getParameter("loginId");
		userList = dbm.getUserInformation(loginId);

		for(UserDTO user : userList) {

			String userName = user.getUserName();
			String icon = user.getIcon();
			String profile = user.getProfile();

			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);
		}

		RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
		dispatch.forward(request, response);




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
