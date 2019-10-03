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
		String iconSearch = request.getParameter("icon");

		//現在のログインユーザーの情報受け渡し
		String nowLoginId = request.getParameter("nowLoginId");
		String nowLoginUser = request.getParameter("nowLoginUser");
		String nowLoginUserId = request.getParameter("nowLoginUserId");
		String nowLoginProfile = request.getParameter("nowLoginProfile");
		String nowLoginIcon = request.getParameter("nowLoginIcon");
		String nowLoginPassword = request.getParameter("nowLoginPassword");


		//DBManagerのオブジェクト生成
		DBManager dbm = new DBManager();

		//ユーザー名だけで検索された場合
		if (!(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(loginIdSearch)
				&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

			//listにuserNameSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.userNameSearch(userNameSearch);
			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ログインIdだけで検索された場合
		}else if (!(StringUtils.isNullOrEmpty(loginIdSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
				&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

			//listにloginIdSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.loginIdSearch(loginIdSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);
			//自己紹介だけで検索された場合

		}else if (!(StringUtils.isNullOrEmpty(profileSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
				&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

			//listにprofileSearchで戻ってきたリストを代入
			ArrayList<UserDTO> list = dbm.profileSearch(profileSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//アイコンだけで検索された場合
			}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
				&& StringUtils.isNullOrEmpty(profileSearch) && !(iconSearch.equals("null"))) {

			ArrayList<UserDTO> list = dbm.iconSearch(iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ユーザー名とログインIdで検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
				&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

			ArrayList<UserDTO> list = dbm.nameIdSearch(userNameSearch, loginIdSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ユーザー名とアイコンで検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(iconSearch.equals("null"))
				&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

			ArrayList<UserDTO> list = dbm.nameIconSearch(userNameSearch, iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//自己紹介とアイコンで検索された場合
		}else if(!(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))
				&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(userNameSearch)) {

			ArrayList<UserDTO> list = dbm.proIconSearch(profileSearch, iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ログインIDと自己紹介で検索された場合

		}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
				&& StringUtils.isNullOrEmpty(userNameSearch) && iconSearch.equals("null")) {

			ArrayList<UserDTO> list = dbm.idProSearch(loginIdSearch, profileSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//IDとアイコンで検索された場合

		}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
				&& StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

			ArrayList<UserDTO> list = dbm.idIconSearch(loginIdSearch, iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);


			//アイコンとIDと自己紹介で検索された場合
		}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
				 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(userNameSearch)) {

			ArrayList<UserDTO> list = dbm.idProIconSearch(loginIdSearch, profileSearch, iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//アイコンとユーザー名と自己紹介で検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
				 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(loginIdSearch)) {

			ArrayList<UserDTO> list = dbm.nameIconProSearch(userNameSearch,iconSearch, profileSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//Idとアイコンとユーザー名で検索された場合
		}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
				 && !(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(profileSearch)) {

			ArrayList<UserDTO> list = dbm.idIconNameSearch(loginIdSearch, iconSearch, userNameSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ユーザー名と自己紹介で検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
				&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

			ArrayList<UserDTO> list = dbm.nameProSearch(userNameSearch, profileSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ユーザー名とログインIdと自己紹介とアイコンで検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
				&& !(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))) {

			ArrayList<UserDTO> list = dbm.nameIdProIconSearch(userNameSearch, loginIdSearch, profileSearch, iconSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//ユーザー名とログインIdと自己紹介で検索された場合
		}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
				&& !(StringUtils.isNullOrEmpty(profileSearch)) && iconSearch.equals("null")) {

			ArrayList<UserDTO> list = dbm.nameIdProSearch(userNameSearch, loginIdSearch, profileSearch);

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

			//SearchResultへ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
			dispatch.forward(request, response);

			//全て空欄だった場合、全件出力
		}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
				&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")){

			ArrayList<UserDTO> list = dbm.allSearch();

			request.setAttribute("searchResult", list);

			//現在のログインユーザーの情報受け渡し
			request.setAttribute("nowLoginId", nowLoginId);
			request.setAttribute("nowLoginUser", nowLoginUser);
			request.setAttribute("nowLoginUserId", nowLoginUserId);
			request.setAttribute("nowLoginProfile", nowLoginProfile);
			request.setAttribute("nowLoginIcon", nowLoginIcon);
			request.setAttribute("nowLoginPassword", nowLoginPassword);

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
