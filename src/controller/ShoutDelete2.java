package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoutDelete2
 */
@WebServlet("/SD2")
public class ShoutDelete2 extends HttpServlet {
	// 直接アクセスがあった場合は index.jsp  に処理を転送
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String loginId = request.getParameter("loginId");
		ArrayList<UserDTO> searchlist = new ArrayList<UserDTO>();*/
		/*searchlist.remove(searchlist.indexOf(loginId));
		*/
	}

}
