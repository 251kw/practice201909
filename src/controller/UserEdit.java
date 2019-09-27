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
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {

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

		String a = request.getParameter("edit");

		if("change".equals(a)) {

			String loginId = request.getParameter("loginId");


			ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

			UserDTO user = userList.get(0);
			String userId = user.getUserId();
			String loginIdChange = user.getLoginId();
			String passwordChange = user.getPassword();
			String userNameChange = user.getUserName();
			String idonChange = user.getIcon();
			String profileChange = user.getProfile();

			request.setAttribute("userId", userId);
			request.setAttribute("loginIdChange", loginIdChange);
			request.setAttribute("passwordChange", passwordChange);
			request.setAttribute("userNameChange", userNameChange);
			request.setAttribute("idonChange", idonChange);
			request.setAttribute("profileChange", profileChange);


			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);


		}


		RequestDispatcher dispatch = request.getRequestDispatcher("Delete.jsp");
		dispatch.forward(request, response);


	}

}
