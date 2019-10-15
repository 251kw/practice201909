package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchProcess2
 */
@WebServlet("/SP2")
public class SearchProcess2 extends HttpServlet {

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
		String selected = request.getParameter("icon");//iconの値を取得
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成

		//SELECTBOXの値保持
		if ("".equals(selected)) {
			selected = "selected";
			request.setAttribute("selected", selected);
		} else if ("icon-user".equals(selected)) {
			selected = "selected";
			request.setAttribute("selected1", selected);
		} else if ("icon-user-female".equals(selected)) {
			selected = "selected";
			request.setAttribute("selected2", selected);
		} else if ("icon-bell".equals(selected)) {
			selected = "selected";
			request.setAttribute("selected3", selected);
		}
		dispatcher = request.getRequestDispatcher("Search.jsp");
		dispatcher.forward(request, response);
	}

}
