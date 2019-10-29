package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class Registerservlet2
 */
@WebServlet(name = "RegisterServlet2", urlPatterns = { "/RS2" })
public class Registerservlet2 extends HttpServlet {
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
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String selected = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String button1 = request.getParameter("button1");
		String button2 = request.getParameter("button2");

		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンス生成
		DBManager dbm = new DBManager();//DBManagerのインスタンスを生成

		//はいボタンが押された時
		if ("btn1".equals(button1)) {

			//関数型インターフェースを定義
			FiveConsumer<String,String,String,String,String> fivecon = dbm::registerUser;
			// ユーザー情報をDBに登録
			fivecon.accept(loginId, password, userName, icon, profile);

			// RegistrationComplete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("RegistrationComplete.jsp");
			dispatcher.forward(request, response);

			//いいえボタンが押された時
		} else if ("btn2".equals(button2)) {

			//選択されたアイコンのselecte情報を保持
			if("icon-user".equals(selected)) {
				selected="selected";
				request.setAttribute("selected", selected);
			}else if("icon-user-female".equals(selected)) {
				selected="selected";
				request.setAttribute("selected1", selected);
			}else if("icon-bell".equals(selected)) {
				selected="selected";
				request.setAttribute("selected2", selected);
			}
			//Register.jspに情報を転送
			dispatcher = request.getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		}
	}

}
