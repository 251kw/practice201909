package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class ChangeUserInformation
 */
@WebServlet("/CUI")
public class ChangeUserInformation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); //文字化け対策
		RequestDispatcher dispatcher = null;//RequestDispatcherの初期化
		String btn = request.getParameter("btn");//ボタン情報取得
		String[] loginId2 = request.getParameterValues("loginId2");
		String loginId = request.getParameter("loginId");
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成
		UserDTO user1 = new UserDTO();//UserDTOのインスタンスを作成

		//更新ボタンが押された時
		if ("更新".equals(btn)) {
			//選択されたユーザーのloginIdからユーザー情報をuser1に代入
			user1=dbm.getChangeUser2(loginId);
			//user1にchekedを代入
			user1.setChecked("checked");

			//requestオブジェクトに情報を格納
			request.setAttribute("user1",user1);
			request.setAttribute("loginId2", loginId2);
			//変更情報の確認画面へ
			dispatcher = request.getRequestDispatcher("ChangeUserInformationCheck.jsp");
			dispatcher.forward(request, response);

			//戻るボタンが押された時
		} else if ("戻る".equals(btn)) {
			//searchlist初期化
			ArrayList<UserDTO> searchlist = new ArrayList<>();
			 //loginId2からuser1に情報格納
			for (int j = 0; j < loginId2.length; j++) {

				user1=dbm.getChangeUser2(loginId2[j]);

				//searchlistとチェックされたユーザーのloginIdを比較して、同じものがあればuser1にcheckedを入れる
				if (loginId.equals(loginId2[j])) {
					user1.setChecked("checked");
				}
				//user1を検索リストに格納。
				searchlist.add(user1);
			}

			request.setAttribute("searchlist", searchlist);

			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);
		}
	}
}
