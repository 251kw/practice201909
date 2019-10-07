package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();
		ArrayList<String> searchList = new ArrayList<>();

		String a = request.getParameter("search");

		if("search".equals(a)) {

			//検索項目に入力された値の取得
			String userNameSearch = request.getParameter("userNameSearch");
			String loginIdSearch = request.getParameter("loginIdSearch");
			String profileSearch = request.getParameter("profileSearch");
			String iconSearch = request.getParameter("icon");

			searchList.add(userNameSearch);
			searchList.add(loginIdSearch);
			searchList.add(profileSearch);
			searchList.add(iconSearch);

			session.setAttribute("searchList", searchList);


			String message = "検索結果はありませんでした";

			//DBManagerのオブジェクト生成
			DBManager dbm = new DBManager();

			//ユーザー名だけで検索された場合
			if (!(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				//listにuserNameSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.userNameSearch(userNameSearch);
				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);


				}else {
					request.setAttribute("searchResult", list);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}

				//ログインIdだけで検索された場合
			}else if (!(StringUtils.isNullOrEmpty(loginIdSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				//listにloginIdSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.loginIdSearch(loginIdSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {

				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);

				}
				//自己紹介だけで検索された場合

			}else if (!(StringUtils.isNullOrEmpty(profileSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
					&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

				//listにprofileSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.profileSearch(profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンだけで検索された場合
				}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && !(iconSearch.equals("null"))) {

				ArrayList<UserDTO> list = dbm.iconSearch(iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とログインIdで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameIdSearch(userNameSearch, loginIdSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.nameIconSearch(userNameSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//自己紹介とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(userNameSearch)) {

				ArrayList<UserDTO> list = dbm.proIconSearch(profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ログインIDと自己紹介で検索された場合

			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					&& StringUtils.isNullOrEmpty(userNameSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.idProSearch(loginIdSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//IDとアイコンで検索された場合

			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.idIconSearch(loginIdSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンとIDと自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(userNameSearch)) {

				ArrayList<UserDTO> list = dbm.idProIconSearch(loginIdSearch, profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンとユーザー名と自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(loginIdSearch)) {

				ArrayList<UserDTO> list = dbm.nameIconProSearch(userNameSearch,iconSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//Idとアイコンとユーザー名で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
					 && !(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.idIconNameSearch(loginIdSearch, iconSearch, userNameSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名と自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameProSearch(userNameSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とログインIdと自己紹介とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& !(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))) {

				ArrayList<UserDTO> list = dbm.nameIdProIconSearch(userNameSearch, loginIdSearch, profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);

				}

				//ユーザー名とログインIdと自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& !(StringUtils.isNullOrEmpty(profileSearch)) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameIdProSearch(userNameSearch, loginIdSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//全て空欄だった場合、全件出力
			}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")){

				ArrayList<UserDTO> list = dbm.allSearch();

				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
			}






		}else {

			//セッションオブジェクトの生成
			HttpSession session1 = request.getSession();

			ArrayList<String> list1 = (ArrayList<String>)session1.getAttribute("searchList");

			String userNameSearch = list1.get(0);
			String loginIdSearch = list1.get(1);
			String profileSearch = list1.get(2);
			String iconSearch = list1.get(3);

			String select = request.getParameter("select");

			String message = "検索結果はありませんでした";

			if("select".equals(select)) {

				request.setAttribute("checked", "checked");

			}

			//DBManagerのオブジェクト生成
			DBManager dbm = new DBManager();

			//ユーザー名だけで検索された場合
			if (!(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				//listにuserNameSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.userNameSearch(userNameSearch);
				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);


				}else {
					request.setAttribute("searchResult", list);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}

				//ログインIdだけで検索された場合
			}else if (!(StringUtils.isNullOrEmpty(loginIdSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				//listにloginIdSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.loginIdSearch(loginIdSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {

				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);

				}
				//自己紹介だけで検索された場合

			}else if (!(StringUtils.isNullOrEmpty(profileSearch)) && StringUtils.isNullOrEmpty(userNameSearch)
					&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

				//listにprofileSearchで戻ってきたリストを代入
				ArrayList<UserDTO> list = dbm.profileSearch(profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンだけで検索された場合
				}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && !(iconSearch.equals("null"))) {

				ArrayList<UserDTO> list = dbm.iconSearch(iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とログインIdで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameIdSearch(userNameSearch, loginIdSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.nameIconSearch(userNameSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//自己紹介とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && StringUtils.isNullOrEmpty(userNameSearch)) {

				ArrayList<UserDTO> list = dbm.proIconSearch(profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ログインIDと自己紹介で検索された場合

			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					&& StringUtils.isNullOrEmpty(userNameSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.idProSearch(loginIdSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//IDとアイコンで検索された場合

			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
					&& StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.idIconSearch(loginIdSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンとIDと自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(userNameSearch)) {

				ArrayList<UserDTO> list = dbm.idProIconSearch(loginIdSearch, profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//アイコンとユーザー名と自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					 && !(iconSearch.equals("null")) && StringUtils.isNullOrEmpty(loginIdSearch)) {

				ArrayList<UserDTO> list = dbm.nameIconProSearch(userNameSearch,iconSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//Idとアイコンとユーザー名で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(loginIdSearch)) && !(iconSearch.equals("null"))
					 && !(StringUtils.isNullOrEmpty(userNameSearch)) && StringUtils.isNullOrEmpty(profileSearch)) {

				ArrayList<UserDTO> list = dbm.idIconNameSearch(loginIdSearch, iconSearch, userNameSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名と自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(profileSearch))
					&& StringUtils.isNullOrEmpty(loginIdSearch) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameProSearch(userNameSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//ユーザー名とログインIdと自己紹介とアイコンで検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& !(StringUtils.isNullOrEmpty(profileSearch)) && !(iconSearch.equals("null"))) {

				ArrayList<UserDTO> list = dbm.nameIdProIconSearch(userNameSearch, loginIdSearch, profileSearch, iconSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);

				}

				//ユーザー名とログインIdと自己紹介で検索された場合
			}else if(!(StringUtils.isNullOrEmpty(userNameSearch)) && !(StringUtils.isNullOrEmpty(loginIdSearch))
					&& !(StringUtils.isNullOrEmpty(profileSearch)) && iconSearch.equals("null")) {

				ArrayList<UserDTO> list = dbm.nameIdProSearch(userNameSearch, loginIdSearch, profileSearch);

				if(list.size() == 0) {

					request.setAttribute("alert", message);

					//SearchResultへ転送
					RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
					dispatch.forward(request, response);

				}else {


				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
				}

				//全て空欄だった場合、全件出力
			}else if(StringUtils.isNullOrEmpty(userNameSearch) && StringUtils.isNullOrEmpty(loginIdSearch)
					&& StringUtils.isNullOrEmpty(profileSearch) && iconSearch.equals("null")){

				ArrayList<UserDTO> list = dbm.allSearch();

				request.setAttribute("searchResult", list);

				//SearchResultへ転送
				RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
				dispatch.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
