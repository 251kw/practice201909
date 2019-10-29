package controller;

import java.io.IOException;
import java.util.function.Predicate;

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

		Predicate<String> Pre = a -> a.equals("");
		Predicate<String> Pre1 = a -> a.equals("icon-user");
		Predicate<String> Pre2= a -> a.equals("icon-user-female");
		Predicate<String> Pre3 = a -> a.equals("icon-bell");

		//SELECTBOXの値保持
		if (Pre.test(selected)) {
			selected = "selected";
			request.setAttribute("selected", selected);
		} else if (Pre1.test(selected)) {
			selected = "selected";
			request.setAttribute("selected1", selected);
		} else if (Pre2.test(selected)) {
			selected = "selected";
			request.setAttribute("selected2", selected);
		} else if (Pre3.test(selected)) {
			selected = "selected";
			request.setAttribute("selected3", selected);
		}
		dispatcher = request.getRequestDispatcher("Search.jsp");
		dispatcher.forward(request, response);
	}

}
