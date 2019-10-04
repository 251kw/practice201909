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
 * Servlet implementation class Delete
 */
@WebServlet("/D")
public class Delete extends HttpServlet {

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
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成
		//SearchProcess.JSPの削除ボタンが押された時

		for (String deleteloginId : loginId) {

			HttpSession session3 = request.getSession();//sessionインスタンスを作成
			ArrayList<UserDTO> deletelist = dbm.getdeleteUser(deleteloginId);// userNameを受け取り、userに情報を格納。

			//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser3として保存
			session3.setAttribute("deletelist", deletelist);

			// UserDelete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("UserDelete.jsp");
			dispatcher.forward(request, response);
		}
	}

}
