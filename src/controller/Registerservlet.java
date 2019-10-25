package controller;

import java.io.IOException;
import java.util.Optional;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");//loginId取得
		String password = request.getParameter("password");//password取得
		RequestDispatcher dispatcher = null;//RequestDispatcher初期化
		String message = null;//message初期化

		if (loginId.equals("") || password.equals("")) {
			// ログインID かパスワードどちらか、もしくは双方未入力なら
			message = "ログインIDとパスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// Register.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		} else {
			//DBManagerのインスタンスを生成
			DBManager dbm = new DBManager();

			// 取得したログインIDをDBで検索し同じログインIDがある場合はデータ登録しない。
			Optional<Boolean> result =Optional.ofNullable(dbm.Determine(loginId));

			if (result.isPresent()) {
				message = "ログインIDはすでに使用されています別のログインIDを入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// index.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("Register.jsp");
				dispatcher.forward(request, response);
			} else {
				//登録情報の確認画面へ
				dispatcher = request.getRequestDispatcher("RegisterCheck.jsp");
				dispatcher.forward(request, response);

			}
		}
	}
}
