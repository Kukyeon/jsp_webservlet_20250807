package com.kkuk.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DoTest
 */
@WebServlet("*.do")
public class DoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); // 클라이언트가 요청한 uri
		// uri -> 나중에 주소 입력해놓기
		String con = request.getContextPath();
		// con -> 주소입력
		String command = uri.substring(con.length());
		// command -> /login.do
		
		String viewPage = "";
		if(command.equals("/login.do")) {
			viewPage = "login.jsp";
		}else if(command.equals("/loginOk.do")) {
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
				//request.getRequestDispatcher("loginFail.jsp").forward(request, response); 
				// request에 담은 아이디를 forward를 이용해 loginFail로보냄 
				// 이후 loginFail 에서 자바코드없이 아이디값 사용가능
				request.setAttribute("errorMsg", "로그인에 실패하였습니다. <br> 아이디와 비밀번호를 다시 한번 확인해주세요.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				// 로그인 실패 시 경고메세지 하단에 출력 
			}
			viewPage = "loginOk.jsp";
		}else if(command.equals("/welcome.do")) {
			viewPage = "welcome.jsp";
		}
		// viewPage 값으로 request 객체를 가지고 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
