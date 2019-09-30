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

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		String a = request.getParameter("deletes");

		DBManager dbm = new DBManager();

		ArrayList<UserDTO> list = new ArrayList<>();
		ArrayList<UserDTO> deleteUsers = new ArrayList<>();

		UserDTO user = new UserDTO();

		if("deletes".equals(a)) {

			String checked[] = request.getParameterValues("select[]");

			for(String deleteLoginId : checked) {
				user.setLoginId(deleteLoginId);
				list.add(user);
			}

			deleteUsers = dbm.getUsersInformation(list);

			request.setAttribute("deleteList", deleteUsers);

			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteCheck.jsp");
			dispatch.forward(request, response);




		}else {



			try {
				dbm.multipleDelete(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}


			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteCheck.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
