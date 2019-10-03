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
 * Servlet implementation class MultipleDelete
 */
@WebServlet("/MultipleDelete")
public class MultipleDelete extends HttpServlet {


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

		String nowLoginId = request.getParameter("nowLoginId");
		String nowLoginUser = request.getParameter("nowLoginUser");
		String nowLoginUserId = request.getParameter("nowLoginUserId");
		String nowLoginProfile = request.getParameter("nowLoginProfile");
		String nowLoginIcon = request.getParameter("nowLoginIcon");
		String nowLoginPassword = request.getParameter("nowLoginPassword");


		String a = request.getParameter("delete");

		DBManager dbm = new DBManager();

		ArrayList<UserDTO> list = new ArrayList<>();
		ArrayList<UserDTO> deleteUsers = new ArrayList<>();

		UserDTO user = new UserDTO();

		if("delete".equals(a)) {

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
