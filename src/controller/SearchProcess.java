package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String userName = request.getParameter("userName");//userNameを取得
		String icon = request.getParameter("icon");//iconを取得
		String profile = request.getParameter("profile");//profileを取得

		String message;//messageを定義
		DBManager dbm = new DBManager();//DBManagerのインスタンスを生成
		RequestDispatcher dispatcher = null;//RequestDispatcherのインスタンスを作成

		// 検索欄未入力だった場合
		if (userName.equals("") && icon.equals("") && profile.equals("")) {
			//messageに代入
			message = "検索ワードを入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// Search.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("Search.jsp");
			dispatcher.forward(request, response);
		} else {
			//引数３の戻り値１の関数型インターフェースを作成getUserListに適応させた
			TriFunction<String,String,String,ArrayList<UserDTO>> trifun = dbm::getUserList;

			//名前,アイコン,プロフィールをDBから検索
			ArrayList<UserDTO> searchlist =trifun.apply(userName, icon, profile);

			//UserDTOが入ってるArrayList型が入ってる関数型インターフェースPredicateを実装
			//メソッド参照でtestの中身をisEmptyにオーバーライド
			Predicate<ArrayList<UserDTO>> Pre = ArrayList::isEmpty;
			//関数型インターフェースのメソッドでsearchlistの中身を確認してbooleanで返す。
            boolean test = Pre.test(searchlist);
			//検索結果がなかったら
			if (test) {
				message = "検索結果がありませんでした";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
                //searchlistをリクエストオブジェクトに保存
				request.setAttribute("searchlist", searchlist);

				// SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			}else {

			//DBからの検索結果をリクエストオブジェクトに保存
			request.setAttribute("searchlist", searchlist);

			// SearchProcess.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);
			}
		}
	}
}
