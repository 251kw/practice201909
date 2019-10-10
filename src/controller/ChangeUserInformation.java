package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;

/**
 * Servlet implementation class ChangeUserInformation
 */
@WebServlet("/CUI")
public class ChangeUserInformation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接アクセスがあった場合は index.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8"); //文字化け対策
		RequestDispatcher dispatcher = null;//RequestDispatcherの初期化
		String btn = request.getParameter("btn");//ボタン情報取得

		//更新ボタンが押された時
		if ("更新".equals(btn)) {

			//変更情報の確認画面へ
			dispatcher = request.getRequestDispatcher("ChangeUserInformationCheck.jsp");
			dispatcher.forward(request, response);

		//戻るボタンが押された時
		} else if ("戻る".equals(btn)) {
			/*HttpSession session = request.getSession();//sessionの取得
			UserDTO user1 =(UserDTO)session.getAttribute("user1");//UserDTOのインスタンスを作成
			ArrayList<UserDTO> searchlist = (ArrayList<UserDTO>) session.getAttribute("searchlist");

			request.setAttribute("user1", user1);
			request.setAttribute("searchlist", searchlist);*/
			// SearchProcess.jsp に処理を転送
			UserDTO user1 = (UserDTO) request.getAttribute("user1");
			ArrayList<UserDTO> searchlist = (ArrayList<UserDTO>) request.getAttribute("searchlist");
			request.setAttribute("searchlist",searchlist);
			request.setAttribute("user1",user1);

			dispatcher = request.getRequestDispatcher("SearchProcess.jsp");
			dispatcher.forward(request, response);
		}
	}
}
