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

		for (String user1loginId : loginId) {
			String loginId1 = user1loginId;

			//SearchProcess.JSPの登録情報変更ボタンが押されたときに動くメソッド
			HttpSession session = request.getSession();//sessionインスタンスを作成
			UserDTO user2 = dbm.getChangeUser2(loginId1);// userNameを受け取り、userに情報を格納。

			//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuserとして保存
			session.setAttribute("user2", user2);

			// ChangeUserInformation.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("ChangeUserInformation.jsp");
			dispatcher.forward(request, response);
		}
	}

}
