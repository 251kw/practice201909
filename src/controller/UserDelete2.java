package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiPredicate;

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

		HttpSession session = request.getSession();//sesstionを取得

		String loginId3=null;// loginId3を初期化
		RequestDispatcher dispatcher;//RequestDispatcherを定義
		UserDTO user = (UserDTO) session.getAttribute("user");//sessionからuserDTOを取得
		String loginId = user.getLoginId();//loginIdをuserDTOから取得

		// sessionからdeletelistを取得
		@SuppressWarnings("unchecked")
		ArrayList<UserDTO> deletelist = (ArrayList<UserDTO>) session.getAttribute("deletelist");

		// deletelistからuserdtoのloginIdを取得
		// loginIdがログインしたユーザーと一致するまでfor文で繰り返し処理
		for (int i = 0; i < deletelist.size(); i++) {
		   loginId3 = deletelist.get(i).getLoginId();
		   if(loginId.equals(loginId3)) {
			   break;
		   }
		}
		BiPredicate<String,String> BiP1 = (a,b) -> a.equals(b);
		//ログインした時のuserと削除したuserが同じだった場合ログイン画面へ
		if (BiP1.test(loginId,loginId3)) {

			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else {
            //Search.jspに処理を転送
			dispatcher = request.getRequestDispatcher("Search.jsp");
			dispatcher.forward(request, response);
		}
	}
}
