package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm; // ログインユーザ情報、書き込み内容管理クラス

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
		RequestDispatcher dispatcher;
		Optional<String> value1 = Optional.ofNullable(writing);
		// 書き込み内容があれば、リストに追加
		if (value1.isPresent()) {
			HttpSession session = request.getSession();
			// セッションからログインユーザ情報を取得
			UserDTO user = (UserDTO) session.getAttribute("user");

			//optionalクラスのインスタンスを生成。ofNullableメソッドでdbmがnullかを判定する。
			Optional<DBManager> value = Optional.ofNullable(dbm);

			// １度だけ DataManager オブジェクトを生成
			if (!value.isPresent()) {
				dbm = new DBManager();
			}

			//関数型インターフェースを定義
			BiPredicate<UserDTO,String> Bip = dbm::setWriting;
			// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
			Bip.test(user, writing);

			//関数型インターフェースを定義
			Supplier<ArrayList<ShoutDTO>> sup = dbm::getShoutList;
			// 書き込み内容追加後のリストを取得
			ArrayList<ShoutDTO> list =sup.get();

			// リストをセッションに保存
			session.setAttribute("shouts", list);
		}

		// top.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}
}
