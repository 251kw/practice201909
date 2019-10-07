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
 * Servlet implementation class UserInformation
 */
@WebServlet("/UI")
public class UserInformation extends HttpServlet {

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String[] loginId = request.getParameterValues("loginId");//userName取得
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		String btn = request.getParameter("btn");
		HttpSession session3 = request.getSession();//sessionインスタンスを作成
		ArrayList<UserDTO> deletelist = new ArrayList<UserDTO>();

		String message = null;
		UserDTO user3 = null;

		if ("変更".equals(btn)) {
			if (loginId == null) {
				// checkboxが未選択の場合
				message = "変更するユーザーを選択してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else {
				for (String user1loginId : loginId) {

					//SearchProcess.JSPの登録情報変更ボタンが押されたときに動くメソッド
					HttpSession session = request.getSession();//sessionインスタンスを作成
					UserDTO user2 = dbm.getChangeUser2(user1loginId);// userNameを受け取り、userに情報を格納。

					//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuserとして保存
					session.setAttribute("user2", user2);

				}
				// ChangeUserInformation.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("ChangeUserInformation.jsp");
				dispatcher.forward(request, response);
			}

		} else if ("削除".equals(btn)) {
			if (loginId == null) {
				// checkboxが未選択の場合
				message = "削除するユーザーを選択してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else {

				for (String deleteloginId : loginId) {

					 user3 = dbm.getdeleteUser(deleteloginId);// userNameを受け取り、userに情報を格納。
					deletelist.add(user3);
				}

				//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser3として保存
				session3.setAttribute("deletelist", deletelist);

				// UserDelete.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserDelete.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
