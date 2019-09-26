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
 * Servlet implementation class Registerservlet
 */
@WebServlet("/RS")
public class Registerservlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message = null;

		if (loginId.equals("") || password.equals("")) {
			// ログインID かパスワードどちらか、もしくは双方未入力なら
			message = "ログインIDとパスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		} else {
			// 取得したログインIDをDBで検索し同じログインIDがある場合はデータ登録しない。
			DBManager dbm = new DBManager();
			boolean result = dbm.Determine(loginId);
			if (result == false) {
				message = "ログインIDはすでに使用されています別のログインIDを入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// index.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("Register.jsp");
				dispatcher.forward(request, response);
			} else {
				// ユーザー情報をDBに登録
				dbm.registerUser(loginId, password, userName, icon, profile);

				// RegistrationComplete.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("RegistrationComplete.jsp");
				dispatcher.forward(request, response);
			}
		}

	}
}
