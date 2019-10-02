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
 * Servlet implementation class UserDelete
 */
@WebServlet("/UD")
public class UserDelete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String userName = request.getParameter("userName");//userName取得
		String loginId = request.getParameter("loginId");//loginId取得
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		String btn = request.getParameter("btn");//ボタンの情報取得
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成

		//はいボタンが押された時
		if("はい".equals(btn)) {
            // loginIdから検索して一致したユーザー削除
			dbm.Delete(loginId);

         // DeleteComplete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("DeleteComplete.jsp");
			dispatcher.forward(request, response);

			//いいえボタンが押された時
		}else if("いいえ".equals(btn)) {
			// Search.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("Search.jsp");
			dispatcher.forward(request, response);
		}


		//SearchProcess.JSPの削除ボタンが押された時
		HttpSession session = request.getSession();//sessionインスタンスを作成
		UserDTO user3 = dbm.getChangeUser(userName);// userNameを受け取り、userに情報を格納。

		//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser2として保存
		session.setAttribute("user3", user3);

		// UserDelete.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("UserDelete.jsp");
		dispatcher.forward(request, response);
	}
}
