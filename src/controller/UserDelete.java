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
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		String btn = request.getParameter("btn");
		DBManager dbm = new DBManager();

		//SearchProcess.JSPの削除ボタンが押されたときに動くメソッド
		HttpSession session = request.getSession();//sessionインスタンスを作成
		UserDTO user2 = dbm.getChangeUser(userName);// userNameを受け取り、userに情報を格納。

		//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser2として保存
		session.setAttribute("user2", user2);

		// UserDelete.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("UserDelete.jsp");
		dispatcher.forward(request, response);
	}
}
