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

		//現在のログインユーザーの情報受け取り
		String nowLoginId = request.getParameter("nowLoginId");
		String nowUserId = request.getParameter("nowUserId");


		String a = request.getParameter("edit");

		//変更か削除か判定
		if("change".equals(a)) {

			String loginId = request.getParameter("loginId");

			//該当ユーザーの全情報をリストでもらう
			ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

			UserDTO user = userList.get(0);
			String userId = user.getUserId();
			String loginIdChange = user.getLoginId();
			String passwordChange = user.getPassword();
			String userNameChange = user.getUserName();
			String idonChange = user.getIcon();
			String profileChange = user.getProfile();

			//値をセットアトリビュート
			request.setAttribute("userId", userId);
			request.setAttribute("loginIdChange", loginIdChange);
			request.setAttribute("passwordChange", passwordChange);
			request.setAttribute("userNameChange", userNameChange);
			request.setAttribute("idonChange", idonChange);
			request.setAttribute("profileChange", profileChange);

			//現在のログインユーザーの情報渡す
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowUserId", nowUserId);

			//Change.jspに転送
			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);

		//変更か削除か判定
		}else if("delete".equals(a)){

			String loginId = request.getParameter("loginId");

			//該当ユーザーの全情報をリストでもらう
			ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

			UserDTO user = userList.get(0);
			String loginIdDelete = user.getLoginId();
			String userNameDelete = user.getUserName();
			String idonDelete = user.getIcon();
			String profileDelete = user.getProfile();

			//値をセットアトリビュート
			request.setAttribute("loginId", loginIdDelete);
			request.setAttribute("icon", idonDelete);
			request.setAttribute("userName", userNameDelete);
			request.setAttribute("profile", profileDelete);

			//現在のログインユーザーの情報渡す
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowUserId", nowUserId);

			//削除するユーザーと現在ログインしているユーザーが同じ場合LoginUserDelete.jspに飛ばす
			if(loginId.equals(nowLoginId)) {

				RequestDispatcher dispatch = request.getRequestDispatcher("LoginUserDelete.jsp");
				dispatch.forward(request, response);


			}

			//Delete.jspに転送
			RequestDispatcher dispatch = request.getRequestDispatcher("Delete.jsp");
			dispatch.forward(request, response);
		}




	}

}
