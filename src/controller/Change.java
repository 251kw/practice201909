package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け防止

		request.setCharacterEncoding("UTF-8");

		//ユーザー情報をパラメータより取得し変数に代入
		String userId = request.getParameter("userId");
		String changeUserLoginId = request.getParameter("changeUserLoginId");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		String message = null;

		//入力チェック
		if (userName == null || userName.equals("")) {
			message = "名前が未入力です";
			request.setAttribute("alert", message);

			request.setAttribute("userId", userId);
			request.setAttribute("changeUserLoginId", changeUserLoginId);

			request.setAttribute("passwordChange", password);
			request.setAttribute("iconChange", icon);
			request.setAttribute("profileChange", profile);

			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);

		} else if (password == null || password.equals("")) {
			message = "パスワードが未入力です";
			request.setAttribute("alert", message);

			request.setAttribute("userId", userId);
			request.setAttribute("changeUserLoginId", changeUserLoginId);

			request.setAttribute("userNameChange", userName);
			request.setAttribute("iconChange", icon);
			request.setAttribute("profileChange", profile);

			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);

		} else if (profile == null || profile.equals("")) {
			message = "自己紹介が未入力です";
			request.setAttribute("alert", message);

			request.setAttribute("userId", userId);
			request.setAttribute("changeUserLoginId", changeUserLoginId);

			request.setAttribute("userNameChange", userName);
			request.setAttribute("passwordChange", password);
			request.setAttribute("iconChange", icon);
			request.setAttribute("loginIdChange", loginId);

			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);

		} else {
			//DBManagerのオブジェクト生成
			DBManager dbm = new DBManager();

				//ユーザー情報をAttributeにセット

				request.setAttribute("changeUserLoginId", changeUserLoginId);
				request.setAttribute("userId", userId);
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("icon", icon);
				request.setAttribute("profile", profile);

				//ChangeCheck.jspに転送
				RequestDispatcher dispatch = request.getRequestDispatcher("ChangeCheck.jsp");
				dispatch.forward(request, response);

		}
	}
}
