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
 * Servlet implementation class UserDelete
 */
@WebServlet("/UD")
public class UserDelete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String[] loginId = request.getParameterValues("loginId");//loginId取得
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成
		String btn = request.getParameter("btn");//ボタンの情報取得
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成
		String[] loginId2 = request.getParameterValues("loginId2");//loginId取得
		UserDTO user1 = new UserDTO();

		//はいボタンが押された時
		if ("はい".equals(btn)) {
			for (String user3loginId : loginId) {
				// loginIdから検索して一致したユーザー削除
				dbm.Delete(user3loginId);
			}
			// DeleteComplete.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("DeleteComplete.jsp");
			dispatcher.forward(request, response);

			//いいえボタンが押された時
		} else if ("いいえ".equals(btn)) {
            //searchlist初期化
			ArrayList<UserDTO> searchlist = new ArrayList<>();

		    //loginId2からuser1に情報格納
			for (int j = 0; j < loginId2.length; j++) {
				user1 = dbm.getChangeUser2(loginId2[j]);
				for (int i = 0; i < loginId.length; i++) {
					//searchlistとチェックされたユーザーのloginIdを比較して、同じものがあればuser1にcheckedを入れる
					if (loginId[i].equals(loginId2[j])) {
						user1.setChecked("checked");
					}
				}
				//user1を検索リストに格納。
				searchlist.add(user1);
			}

			request.setAttribute("searchlist", searchlist);
			// SearchProcess.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);
		}
	}
}
