package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeBack
 */
@WebServlet("/ChangeBack")
public class ChangeBack extends HttpServlet {

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

		String changeUserLoginId = request.getParameter("changeUserLoginId");
		String userId = request.getParameter("userId");
		String loginId = request.getParameter("loginIdChange");
		String password = request.getParameter("passwordChange");
		String userName = request.getParameter("userNameChange");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profileChange");

		request.setAttribute("changeUserLoginId", changeUserLoginId);
		request.setAttribute("userId", userId);
		request.setAttribute("loginIdChange", loginId);
		request.setAttribute("passwordChange", password);
		request.setAttribute("userNameChange", userName);
		request.setAttribute("icon", icon);
		request.setAttribute("profileChange", profile);

		String nowLoginId = request.getParameter("nowLoginId");
		String nowLoginUser = request.getParameter("nowLoginUser");
		String nowLoginUserId = request.getParameter("nowLoginUserId");
		String nowLoginProfile = request.getParameter("nowLoginProfile");
		String nowLoginIcon = request.getParameter("nowLoginIcon");
		String nowLoginPassword = request.getParameter("nowLoginPassword");

		request.setAttribute("nowLoginId", nowLoginId);
		request.setAttribute("nowLoginUser", nowLoginUser);
		request.setAttribute("nowLoginUserId", nowLoginUserId);
		request.setAttribute("nowLoginProfile", nowLoginProfile);
		request.setAttribute("nowLoginIcon", nowLoginIcon);
		request.setAttribute("nowLoginPassword", nowLoginPassword);




		RequestDispatcher dispatch = request.getRequestDispatcher("Change.jsp");
		dispatch.forward(request, response);

	}

}
