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
import javax.servlet.http.HttpSession;

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

		String a = request.getParameter("delete");

		DBManager dbm = new DBManager();

		ArrayList<UserDTO> deleteUsers = new ArrayList<>();
		HttpSession session = request.getSession();


		if("delete".equals(a)) {

			String checked[] = request.getParameterValues("deletes");


			deleteUsers = dbm.getUsersInformation(checked);

			session.setAttribute("deleteList", deleteUsers);

			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteCheck.jsp");
			dispatch.forward(request, response);


		}else {


			ArrayList<UserDTO> list = new ArrayList<>();


			list = (ArrayList<UserDTO>)session.getAttribute("deleteList");

			try {
				dbm.multipleDelete(list);

				} catch (SQLException e) {
				e.printStackTrace();

			}



			RequestDispatcher dispatch = request.getRequestDispatcher("MultipleDeleteComplete.jsp");
			dispatch.forward(request, response);
		}
	}

}
