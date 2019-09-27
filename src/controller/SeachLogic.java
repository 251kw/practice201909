package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class SeachLogic
 */
@WebServlet("/SeachLogic")
public class SeachLogic extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//検索項目に入力された値の取得
		String userNameSearch = request.getParameter("userNameSearch");
		String loginIdSearch = request.getParameter("loginIdSearch");
		String profileSearch = request.getParameter("profileSearch");

		//DBManagerのオブジェクト生成
		DBManager dbm = new DBManager();

		//ユーザー名で検索された場合
		if (!(StringUtils.isNullOrEmpty(userNameSearch))) {

			//listにuserNameSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.userNameSearch(userNameSearch);
			request.setAttribute("searchResult", list);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

		} else if (!(StringUtils.isNullOrEmpty(loginIdSearch))) {

			//listにloginIdSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.loginIdSearch(loginIdSearch);

			request.setAttribute("searchResult", list);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
		} else if (!(StringUtils.isNullOrEmpty(profileSearch))) {

			//listにprofileSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.profileSearch(profileSearch);

			request.setAttribute("searchResult", list);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
		}else {
			ArrayList<UserDTO> list = dbm.allSearch();

			request.setAttribute("searchResult", list);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
