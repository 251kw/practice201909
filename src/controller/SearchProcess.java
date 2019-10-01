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
 * Servlet implementation class SearchProcess
 */
@WebServlet("/SP")
public class SearchProcess extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//登録確認情報を取得
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String btn1 = request.getParameter("btn1");
		String btn2 = request.getParameter("btn2");
		String btn3 = request.getParameter("btn3");

		String message = null;
		DBManager dbm = new DBManager();//DBMのインスタンスを生成
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		if ("検索".equals(btn1)) {

			if (userName.equals("")) {
				// 検索欄未入力なら
				message = "検索する名前を入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// Search.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("Search.jsp");
				dispatcher.forward(request, response);
			} else {
				//名前をDBから検索してtop.jspに表示
				ArrayList<UserDTO> searchlist = dbm.getUserList(userName);
				HttpSession session = request.getSession();
				//DBからの検索結果をリクエストオブジェクトに保存
				session.setAttribute("searchlist", searchlist);

				// SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			}
		}else if("検索".equals(btn2)) {

				//iconをDBから検索してtop.jspに表示
				ArrayList<UserDTO> searchlist = dbm.getUserList2(icon);
				HttpSession session = request.getSession();
				//DBからの検索結果をリクエストオブジェクトに保存
				session.setAttribute("searchlist", searchlist);

				// SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);

		}else if("検索".equals(btn3)) {


			if (profile.equals("")) {
				// 検索欄未入力なら
				message = "検索ワードを入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// Search.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("Search.jsp");
				dispatcher.forward(request, response);
			} else {
				//profileをDBから検索してSearchProcess.jspに表示
				ArrayList<UserDTO> searchlist = dbm.getUserList3(profile);
				HttpSession session = request.getSession();
				//DBからの検索結果をリクエストオブジェクトに保存
				session.setAttribute("searchlist", searchlist);

				// SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			}


		}
	}
}
