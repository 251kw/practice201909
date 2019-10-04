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

@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// top.jsp の「叫ぶ」ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO)session.getAttribute("user");

		String loginId = user.getLoginId();
		String userName = user.getUserName();
		String icon = user.getIcon();

		DBManager dbm = new DBManager();

		RequestDispatcher dispatcher;

		// 書き込み内容があれば、リストに追加
		if (!writing.equals("")) {

			dbm.setWriting(loginId, userName, icon, writing);
			}

		// top.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("Top");
		dispatcher.forward(request, response);
	}
}
