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

import dto.UserDTO;

/**
 * Servlet implementation class UserDelete2
 */
@WebServlet("/UD2")
public class UserDelete2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		HttpSession session3 = request.getSession();
		String loginId3=null;
		RequestDispatcher dispatcher = null;
		UserDTO user = (UserDTO) session.getAttribute("user");
		String loginId = user.getLoginId();

		ArrayList<UserDTO> deletelist = (ArrayList<UserDTO>) session.getAttribute("deletelist");
		for (int i = 0; i < deletelist.size(); i++) {
		   loginId3 = deletelist.get(i).getLoginId();
		   if(loginId.equals(loginId3)) {
			   break;
		   }
		}

		//ログインした時のuserと削除したuserが同じだった場合ログイン画面へ
		if (loginId.equals(loginId3)) {

			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else {

			/*ArrayList<UserDTO> searchlist1 = (ArrayList<UserDTO>)session.getAttribute("searchlist");

			session.setAttribute("searchlist", searchlist1);*/
			// Search.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("Search.jsp");
			dispatcher.forward(request, response);
		}
	}
}
