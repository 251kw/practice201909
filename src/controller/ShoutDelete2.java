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

/**
 * Servlet implementation class ShoutDelete2
 */
@WebServlet("/SD2")
public class ShoutDelete2 extends HttpServlet {
	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		String shoutsId = request.getParameter("shoutsId");//loginId取得
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		String btn = request.getParameter("btn");//ボタン情報取得

		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成

		//はいボタンが押された時、DBに登録
		if ("はい".equals(btn)) {

			dbm.ShoutDelete(shoutsId);

		   ArrayList<ShoutDTO> list = dbm.getShoutList();

			// リストをセッションに保存
			HttpSession session = request.getSession();
			session.setAttribute("shouts", list);

			// ShoutDeleteComplete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("ShoutDeleteComplete.jsp");
			dispatcher.forward(request, response);

			//いいえボタンが押された時
		} else if ("いいえ".equals(btn)) {

			 ArrayList<ShoutDTO> list = dbm.getShoutList();

				// リストをセッションに保存
				HttpSession session = request.getSession();
				session.setAttribute("shouts", list);

			// top.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);
		}

	}

}
