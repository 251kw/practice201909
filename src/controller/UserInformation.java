package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserInformation
 */
@WebServlet("/UI")
public class UserInformation extends HttpServlet {

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //文字化け対策
		String[] loginId = request.getParameterValues("loginId");//loginIdを配列で取得
		DBManager dbm = new DBManager();//DBManagerのインスタンスを作成
		String btn = request.getParameter("btn");//btnの値を取得
		HttpSession session = request.getSession();//sessionインスタンスを作成

		String[] loginId2 = request.getParameterValues("loginId2");//loginIdを配列で取得

		RequestDispatcher dispatcher;//RequestDispatcherの定義
		String icon;//icon定義
		String message;//message定義
		String checked;//checked定義
		UserDTO user1 =new UserDTO();//UserDTOクラスのインスタンス化
		Optional<String[]> value;//配列のString型のOptionalを定義

		//UserDTO型が入ってるArraylistが入ってるsupplierインターフェースを実装したArrayListを定義
		Supplier<ArrayList<UserDTO>> supplier = ArrayList::new;
		ArrayList<UserDTO> searchlist;

		//UserDTO型が入ってるArraylistが入ってるsupplierインターフェースを実装したArrayListを定義
		Supplier<ArrayList<UserDTO>> supplier1 = ArrayList::new;
		ArrayList<UserDTO> deletelist;

		//変更が押された場合
		if ("変更".equals(btn)) {
			//OptionalのofNullableでloginIdの中身を確認。valueに入れる
			value=Optional.ofNullable(loginId);

			//関数型インターフェースPredicateを実装した匿名クラスでtestメソッドをオーバーライド
			Predicate<String[]> Pre = a -> a.length>=2;

			// checkboxが未選択の場合
			if (!value.isPresent()) {
				// エラーメッセージを代入
				message = "変更するユーザーを選択してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//searchlist初期化
				searchlist = supplier.get();
				 //loginId2からuser1に情報格納
				for (int j = 0; j < loginId2.length; j++) {

					user1=dbm.getChangeUser2(loginId2[j]);

					//user1を検索リストに格納。
					searchlist.add(user1);
				}
                //searchlistをリクエストオブジェクトに格納
				request.setAttribute("searchlist", searchlist);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);

				//配列の要素が2以上の時
			} else if (Pre.test(loginId)) {
				// エラーメッセージを代入
				message = "変更するユーザーを一人チェックしてください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//searchlistを取得
				searchlist = supplier.get();
				 //loginId2からuser1に情報格納
				for (int j = 0; j < loginId2.length; j++) {

					user1=dbm.getChangeUser2(loginId2[j]);

					//user1を検索リストに格納。
					searchlist.add(user1);
				}
				//searchlistをリクエストオブジェクトに格納
				request.setAttribute("searchlist", searchlist);

				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else {

				for (int j = 0; j < loginId.length; j++) {

					//SearchProcess.JSPの登録情報変更ボタンが押されたときに動くメソッド
					user1 = dbm.getChangeUser2(loginId[j]);// userNameを受け取り、user2に情報を格納。

					//user1のiconを取得し、対応したselectをuser1から取得
					icon = user1.getIcon();

					if ("icon-user".equals(icon)) {
						user1.setSelected("selected");
					} else if ("icon-user-female".equals(icon)) {
						user1.setSelected1("selected");
					} else if ("icon-bell".equals(icon)) {
						user1.setSelected2("selected");
					}

					//チェックボックスにチェックされたloginIdと検索時に取得したloginIdを比べ一致したらuser1にチェックをセットする
					for (int i = 0; i < loginId2.length; i++) {
						if (loginId[j].equals(loginId2[i])) {
							checked = "checked";
							user1.setChecked(checked);
						}
					}
				}
				//loginId2をリクエストオブジェクトに格納
				request.setAttribute("loginId2", loginId2);
				//requestオブジェクトにuser1を保存
				request.setAttribute("user1", user1);

				// ChangeUserInformation.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("ChangeUserInformation.jsp");
				dispatcher.forward(request, response);
			}

			//削除が押された場合
		} else if ("削除".equals(btn)) {
			//OptionalのofNullableでloginIdの中身を確認。valueに入れる
			value = Optional.ofNullable(loginId);

			if (!value.isPresent()) {

				// checkboxが未選択の場合
				message = "削除するユーザーを選択してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				//searchlistを取得
				searchlist = supplier.get();
				 //loginId2からuser1に情報格納
				for (int j = 0; j < loginId2.length; j++) {

					user1=dbm.getChangeUser2(loginId2[j]);

					//user1を検索リストに格納。
					searchlist.add(user1);
				}
				//searchlistをリクエストオブジェクトに格納
				request.setAttribute("searchlist", searchlist);
				//SearchProcess.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
				dispatcher.forward(request, response);
			} else {
				//deletelistを取得」
				deletelist = supplier1.get();

				for (String deleteloginId : loginId) {

					// loginIdを受け取り、user3に情報を格納。
					UserDTO user3 = dbm.getdeleteUser(deleteloginId);

					//deletelistにuser3を格納
					deletelist.add(user3);
				}

				//ユーザー情報をset 戻るときにも情報を残したいのでsessionにuser3として保存
				session.setAttribute("deletelist",deletelist );
				//検索結果をloginIdの配列でリクエストオブジェクトに格納
				request.setAttribute("loginId2", loginId2);
				// UserDelete.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserDelete.jsp");
				dispatcher.forward(request, response);

			}

			//全選択ボタンが押されたとき
		} else if ("全選択".equals(btn)) {

			//searchlistを取得
			searchlist = supplier.get();

			//チェックを代入
			checked = "checked";
			//requestオブジェクトにcheckedを代入
			request.setAttribute("checked", checked);

			//serchlistに検索結果(loginId2)を代入
			for (String loginId1 : loginId2) {
				user1 = dbm.getChangeUser2(loginId1);
				searchlist.add(user1);
			}
			//requestオブジェクトにserchlistを代入
			request.setAttribute("searchlist", searchlist);

			//SearchProcess.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);

		}else if ("名前順に並び替え".equals(btn)) {
			//searchlistを取得
			searchlist = supplier.get();
           
			//serchlistに検索結果(loginId2)を代入
			for (String loginId1 : loginId2) {
				user1 = dbm.getChangeUser2(loginId1);
				searchlist.add(user1);
			}
            //searchlistに名前の順で入れ替えたUserDTO型が入ってるArrayListを格納
			searchlist=(ArrayList<UserDTO>) searchlist.stream().sorted(Comparator.comparing(UserDTO::getUserName)).collect(Collectors.toList());
			//requestオブジェクトにserchlistを格納
			request.setAttribute("searchlist", searchlist);

			//SearchProcess.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);

		}
	}

}
