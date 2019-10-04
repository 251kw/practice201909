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

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * Servlet implementation class Top
 */
@WebServlet("/Top")
public class Top extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		String userId = (String)session.getAttribute("userId");

		UserDTO user = dbm.getLoginUserAgain(userId);

		session.setAttribute("user", user);

		ArrayList<ShoutDTO> shoutsList = new ArrayList<>();

		shoutsList = dbm.getShoutList();

		session.setAttribute("shouts", shoutsList);

		RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
		dispatch.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		DBManager dbm = new DBManager();

		//セッションオブジェクトの生成
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO)session.getAttribute("user");

		String userId = user.getUserId();

		user = dbm.getLoginUserAgain(userId);

		session.setAttribute("user", user);



		ArrayList<ShoutDTO> shoutsList = new ArrayList<>();

		shoutsList = dbm.getShoutList();

		session.setAttribute("shouts", shoutsList);

		RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
		dispatch.forward(request, response);

	}

}
