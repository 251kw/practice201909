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
import dto.ShoutDTO;
import dto.UserDTO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");


		String a = request.getParameter("register");
		if("register".equals(a)) {

			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			String profile = request.getParameter("profile");
			String userName = request.getParameter("userName");
			String icon = request.getParameter("icoi");

			UserDTO userDTO = new UserDTO(loginId, password, userName, icon, profile);

			// ログイン認証を行い、ユーザ情報を取得
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getLoginUser(loginId, password);


			// ユーザ情報を取得できたら、書き込み内容リストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();
			HttpSession session = request.getSession();

			// ログインユーザ情報、書き込み内容リストとしてセッションに保存
			session.setAttribute("user", user);
			session.setAttribute("shouts", list);

			// 処理の転送先を top.jsp に指定
			RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
			dispatch.forward(request, response);


		}else {

			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	// index.jsp の「ログイン」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		String message = null;

		if (loginId.equals("") || password.equals("")) {
			// ログインID かパスワードどちらか、もしくは双方未入力なら
			message = "ログインIDとパスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else {
			// ログイン認証を行い、ユーザ情報を取得
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getLoginUser(loginId, password);

			if (user != null) {
				// ユーザ情報を取得できたら、書き込み内容リストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				HttpSession session = request.getSession();

				// ログインユーザ情報、書き込み内容リストとしてセッションに保存
				session.setAttribute("user", user);
				session.setAttribute("shouts", list);

				// 処理の転送先を top.jsp に指定
				dispatcher = request.getRequestDispatcher("top.jsp");
			} else {
				// ユーザ情報が取得できない場合
				// エラーメッセージをリクエストオブジェクトに保存
				message = "ログインIDまたはパスワードが違います";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("index.jsp");
			}

			// 処理を転送
			dispatcher.forward(request, response);
		}
	}
}
