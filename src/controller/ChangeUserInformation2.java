package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class ChangeUserInformation2
 */
@WebServlet("/CUI2")
public class ChangeUserInformation2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String loginId = request.getParameter("loginId");//loginId取得
		String password = request.getParameter("password");//password取得
		String userName = request.getParameter("userName");//userName取得
		String icon = request.getParameter("icon");//icon取得
		String profile = request.getParameter("profile");//profile取得
		RequestDispatcher dispatcher = null;//RequestDispatcherの初期化
		String btn = request.getParameter("btn");//ボタン情報取得

		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成

		//はいボタンが押された時、DBに登録
		if ("はい".equals(btn)) {

			//ユーザー情報更新
			dbm.ChangeUserInformation(loginId, password, userName, icon, profile);

			// ChangeUserInformationComplete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("ChangeUserInformationComplete.jsp");
			dispatcher.forward(request, response);

			//いいえボタンが押された時
		} else if ("いいえ".equals(btn)) {
			UserDTO user1 = new UserDTO();//UserDTOのインスタンスを作成

			//user1に登録情報
			user1.setLoginId(request.getParameter("loginId"));
			user1.setChecked(request.getParameter("Checked"));
			user1.setPassword(request.getParameter("password"));
			user1.setProfile(request.getParameter("profile"));
			user1.setUserName(request.getParameter("userName"));



			//選択されたアイコンをuser1に格納
			if ("icon-user".equals(icon)) {
				user1.setSelected("selected");
				user1.setSelected1(null);
				user1.setSelected2(null);
			} else if ("icon-user-female".equals(icon)) {
				user1.setSelected(null);
				user1.setSelected1("selected");
				user1.setSelected2(null);
			} else if ("icon-bell".equals(icon)) {
				user1.setSelected(null);
				user1.setSelected1(null);
				user1.setSelected2("selected");
			}

			//ユーザー情報をsessionにuser1として保存
			request.setAttribute("user1", user1);

			// ChangeUserInformation.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("ChangeUserInformation.jsp");
			dispatcher.forward(request, response);
		}

	}

}
