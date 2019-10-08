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
import dto.ShoutDTO;

/**
 * Servlet implementation class ShoutDelete
 */
@WebServlet("/SD")
public class ShoutDelete extends HttpServlet {

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String shoutsId = request.getParameter("shoutsId");//shoutsIdを取得
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成

		//ShoutIdからテーブルを検索しsessionに保存
		HttpSession session4 = request.getSession();//sessionインスタンスを作成
		ShoutDTO shouts = dbm.getshout(shoutsId);// userNameを受け取り、userに情報を格納

		//sessionに保存
		session4.setAttribute("shouts", shouts);

		// UserDelete.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("ShoutDelete.jsp");
		dispatcher.forward(request, response);

	}

}
