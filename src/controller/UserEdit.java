package controller;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();


		String a = request.getParameter("change");
		String b = request.getParameter("delete");
		String c = request.getParameter("multipleDelete");

		//現在のログインユーザーの情報受け取り
		String nowLoginId = request.getParameter("nowLoginId");
		String nowLoginUser = request.getParameter("nowLoginUser");
		String nowLoginUserId = request.getParameter("nowLoginUserId");
		String nowLoginProfile = request.getParameter("nowLoginProfile");
		String nowLoginIcon = request.getParameter("nowLoginIcon");
		String nowLoginPassword = request.getParameter("nowLoginPassword");

		if("編集".equals(a)) {


			String[] loginIds = request.getParameterValues("loginId");
			String loginId = loginIds[1];

			//該当ユーザーの全情報をリストでもらう
			ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

			UserDTO user = userList.get(0);
			String userId = user.getUserId();
			String loginIdChange = user.getLoginId();
			String passwordChange = user.getPassword();
			String userNameChange = user.getUserName();
			String iconChange = user.getIcon();
			String profileChange = user.getProfile();
			String changeUserLoginId = user.getLoginId();

			//値をセットアトリビュート
			request.setAttribute("userId", userId);
			request.setAttribute("changeUserLoginId", changeUserLoginId);
			request.setAttribute("loginIdChange", loginIdChange);
			request.setAttribute("passwordChange", passwordChange);
			request.setAttribute("userNameChange", userNameChange);
			request.setAttribute("iconChange", iconChange);
			request.setAttribute("profileChange", profileChange);

			//現在のログインユーザーの情報渡す
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//Change.jspに転送
			RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
			dispatch.forward(request, response);


		}else  if("削除".equals(b)) {

			String loginId = request.getParameter("loginId");

			//該当ユーザーの全情報をリストでもらう
			ArrayList<UserDTO> userList = dbm.getUserInformation(loginId);

			UserDTO user = userList.get(0);
			String loginIdDelete = user.getLoginId();
			String userNameDelete = user.getUserName();
			String iconDelete = user.getIcon();
			String profileDelete = user.getProfile();

			//値をセットアトリビュート
			request.setAttribute("loginId", loginIdDelete);
			request.setAttribute("icon", iconDelete);
			request.setAttribute("userName", userNameDelete);
			request.setAttribute("profile", profileDelete);

			//現在のログインユーザーの情報渡す
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//削除するユーザーと現在ログインしているユーザーが同じ場合LoginUserDelete.jspに飛ばす
				if(loginId.equals(nowLoginId)) {

				RequestDispatcher dispatch = request.getRequestDispatcher("LoginUserDelete.jsp");
				dispatch.forward(request, response);
				}else {

					//Delete.jspに転送
					RequestDispatcher dispatch = request.getRequestDispatcher("Delete.jsp");
					dispatch.forward(request, response);

				}

		}else if("まとめて削除".equals(c)) {

			//現在のログインユーザーの情報渡す
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			ArrayList<UserDTO> list = new ArrayList<>();
			ArrayList<UserDTO> deleteUsers = new ArrayList<>();
			UserDTO user = new UserDTO();

			String checked[] = request.getParameterValues("deletes");

			for(String deleteLoginId : checked) {
				user.setLoginId(deleteLoginId);
				list.add(user);
			}

			deleteUsers = dbm.getUsersInformation(list);

			request.setAttribute("deleteList", deleteUsers);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);


			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteCheck.jsp");
			dispatch.forward(request, response);


		}else {

			UserDTO user2 = new UserDTO();
			ArrayList<UserDTO> list2 = new ArrayList<>();

			String[] userInformation = request.getParameterValues("list");

			for(int i = 0; i >= userInformation.length; i++) {

				user2.setLoginId(userInformation[i]);
				list2.add(user2);
			}

			try {

				dbm.multipleDelete(list2);

				} catch (SQLException e) {
				e.printStackTrace();
			}

			String nowLoginId2 = request.getParameter("nowLoginId");
			String nowLoginUser2 = request.getParameter("nowLoginUser");
			String nowLoginUserId2 = request.getParameter("nowLoginUserId");
			String nowLoginProfile2 = request.getParameter("nowLoginProfile");
			String nowLoginIcon2 = request.getParameter("nowLoginIcon");
			String nowLoginPassword2 = request.getParameter("nowLoginPassword");

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId2);
			request.setAttribute("nowLoginUser", nowLoginUser2);
			request.setAttribute("nowLoginUserId", nowLoginUserId2);
			request.setAttribute("nowLoginProfile", nowLoginProfile2);
			request.setAttribute("nowLoginIcon", nowLoginIcon2);
			request.setAttribute("nowLoginPassword", nowLoginPassword2);




			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteComplete.jsp");
			dispatch.forward(request, response);
		}
	}
}
