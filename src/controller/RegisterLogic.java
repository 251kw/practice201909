package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class RegisterLogic
 */
@WebServlet("/RegisterLogic")
public class RegisterLogic extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接アクセスされた場合はindex.jspに飛ばす
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け防止
		request.setCharacterEncoding("UTF-8");


		//ユーザー情報をパラメータより取得
		String loginId = (String)request.getParameter("loginId");
		String userName = (String)request.getParameter("userName");
		String password = (String)request.getParameter("password");
		String icon = (String)request.getParameter("icon");
		String profile = (String)request.getParameter("profile");


		try {
			//DBManagerのオブジェクト生成
			DBManager dbm = new DBManager();

			//registerメソッドで情報をDBに登録
			dbm.register(loginId, userName, password, icon, profile);

		} catch (SQLException e) {
				e.printStackTrace();

		}
		//ユーザー情報をAttributeにセット
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);


		//RegistrationComplete.jspに転送
		RequestDispatcher dispatch = request.getRequestDispatcher("RegistrationComplete.jsp");
		dispatch.forward(request, response);
	}
}
