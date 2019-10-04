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
import dto.UserDTO;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();


		String loginId = request.getParameter("loginId");

		//該当ユーザーの全情報をリストでもらう
		ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

		UserDTO user = userList.get(0);
		String loginIdDelete = user.getLoginId();
		String userNameDelete = user.getUserName();
		String iconDelete = user.getIcon();
		String profileDelete = user.getProfile();

		//値をセットアトリビュート
		request.setAttribute("loginId", loginIdDelete);
		request.setAttribute("icon", iconDelete);
		request.setAttribute("userName", userNameDelete);
		request.setAttribute("profile", profileDelete);


		//削除するユーザーと現在ログインしているユーザーが同じ場合LoginUserDelete.jspに飛ばす
		UserDTO user1 = (UserDTO) session.getAttribute("user");
		String nowLoginId = user1.getLoginId();

		if(loginId.equals(nowLoginId)) {

			RequestDispatcher dispatch = request.getRequestDispatcher("LoginUserDelete.jsp");
			dispatch.forward(request, response);


		}else {

		//Delete.jspに転送
		RequestDispatcher dispatch = request.getRequestDispatcher("Delete.jsp");
		dispatch.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
