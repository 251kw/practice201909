package controller;

import java.io.IOException;

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
 * Servlet implementation class SearchProcess
 */
@WebServlet("/SP")
public class SearchProcess extends HttpServlet {

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
		//登録確認情報を取得
		String userName = request.getParameter("userName");
		String message = null;
		DBManager dbm = new DBManager();//DBMのインスタンスを生成
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成

		// セッションからログインユーザ情報を取得
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		if (userName.equals("")) {
			// 検索欄未入力なら
			message = "検索する名前を入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);
			
		}
	}
}
