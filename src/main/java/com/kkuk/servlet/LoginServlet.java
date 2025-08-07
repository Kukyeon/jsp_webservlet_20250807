package com.kkuk.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginOk")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("로그인 호출! <br>");
		 String mid = request.getParameter("mid");
		// System.out.println("login.jsp 에서 넘겨받은 아이디값 : " + mid);
		 String mpw = request.getParameter("mpw");
		// System.out.println("login.jsp 에서 넘겨받은 비밀번호값 : " + mpw );
		 
		if(mid.equals("tiger") && mpw.equals("12345")) { // ture 면 로그인 성공
			//session 에 id 값을 저장 - > 로그인 성공
			HttpSession session = request.getSession(); // 세션 생성 , request 객체안에 들어있었음
			session.setAttribute("sid", mid); // 세션에 id 갑 올리기
			response.sendRedirect("welcome.jsp"); // 로그인 성공페이지로 이동
		} else { // 로그인 실패 시
			//response.sendRedirect("loginFail.jsp"); // 로그인 실패페이지로 이동
			request.setAttribute("failid", mid); // failid = 로그인실패한 아이디 request객체에 아이디를 담음
			request.getRequestDispatcher("loginFail.jsp").forward(request, response); 
			// request에 담은 아이디를 forward를 이용해 loginFail로보냄 
			// 이후 loginFail 에서 자바코드없이 아이디값 사용가능
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
