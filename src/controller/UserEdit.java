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

		//文字化け防止
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		String loginId = request.getParameter("loginId");

		//該当ユーザーの全情報をリストでもらう
		ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

		UserDTO user = userList.get(0);
		String userId = user.getUserId();
		String loginIdChange = user.getLoginId();
		String passwordChange = user.getPassword();
		String userNameChange = user.getUserName();
		String iconChange = user.getIcon();
		String profileChange = user.getProfile();

		//値をセットアトリビュート
		request.setAttribute("userId", userId);
		request.setAttribute("passwordChange", passwordChange);
		request.setAttribute("iconChange", iconChange);
		request.setAttribute("userNameChange", userNameChange);
		request.setAttribute("profileChange", profileChange);
		request.setAttribute("changeUserLoginId", loginIdChange);



		//Change.jspに転送
		RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
		dispatch.forward(request, response);



	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
