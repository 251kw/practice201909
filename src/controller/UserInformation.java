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
		RequestDispatcher dispatcher = null;//RequestDispatcherの初期化
		String btn = request.getParameter("btn");//btnの値を取得
		HttpSession session3 = request.getSession();//sessionインスタンスを作成
		ArrayList<UserDTO> deletelist = new ArrayList<UserDTO>();//ArrayListのインスタンスを作成
		HttpSession session = request.getSession();//sessionインスタンスを作成
		@SuppressWarnings("unchecked")
		ArrayList<UserDTO> searchlist = (ArrayList<UserDTO>) session.getAttribute("searchlist");

		String icon = null;//icon初期化
		String message = null;//message初期化
		String checked = null;//checked初期化
		UserDTO user3 = null;//user3初期化
		UserDTO user2 = null;//user2初期化
		UserDTO user1 = null;//user1初期化

		//変更が押された場合
		if ("変更".equals(btn)) {

			// checkboxが未選択の場合
			if (loginId == null) {
				// エラーメッセージを代入
				message = "変更するユーザーを選択してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else if (loginId.length >= 2) {
				// エラーメッセージを代入
				message = "変更するユーザーを一人チェックしてください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else {

				for (String user2loginId : loginId) {

					//SearchProcess.JSPの登録情報変更ボタンが押されたときに動くメソッド
					user2 = dbm.getChangeUser2(user2loginId);// userNameを受け取り、user2に情報を格納。

					//user2のiconを取得し、対応したselectをuser2から取得
					icon = user2.getIcon();

					if ("icon-user".equals(icon)) {
						user2.setSelected("selected");
					} else if ("icon-user-female".equals(icon)) {
						user2.setSelected1("selected");
					} else if ("icon-bell".equals(icon)) {
						user2.setSelected2("selected");
					}


				}
				//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser2として保存
				session.setAttribute("user2", user2);
				// ChangeUserInformation.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("ChangeUserInformation.jsp");
				dispatcher.forward(request, response);
			}

			//削除が押された場合
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
					// loginIdを受け取り、user3に情報を格納。
					user3 = dbm.getdeleteUser(deleteloginId);

					//deletelistにuser3を格納
					deletelist.add(user3);
				}

				//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser3として保存
				session3.setAttribute("deletelist", deletelist);

				// UserDelete.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserDelete.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("全選択".equals(btn)) {
			checked = "checked";
			request.setAttribute("checked", checked);

			//SearchProcess.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);
		}
	}

}
