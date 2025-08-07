package com.kkuk.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kkuk.dto.BoardDto;
import com.kkuk.dto.memberdto;



/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//request -> mid, mpw
		request.setCharacterEncoding("utf-8");
		//StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI(); // 클라이언트가 요청한 uri
		//System.out.println(uri);// /jsp_webservlet_20250807/loginOk.do
		String con = request.getContextPath(); // 컨텍스트 패스 가져오기
		//System.out.println(con); // /jsp_webservlet_20250807
		//uri - com = loginOk.do
		String command = uri.substring(con.length()); 
		// substring 으로 컨텍스트 패스의 길이 인덱스 끝까지 추출
		System.out.println("클라이언트의 요청 : " + command); // /loginOk.do
		
		
		String viewPage = ""; // 실제 클라이언트에게 전송 될 jsp파일의 이름이 저장될 변수
		HttpSession session = null;
		if(command.equals("/loginOk.do")) {
			String mid = request.getParameter("mid");
			String mpw = request.getParameter("mpw");
			
			if(mid.equals("tiger") && mpw.equals("12345")) {//참이면 로그인성공
				session = request.getSession(); // 세션 생성방법 
				
				session.setAttribute("sid", mid); // 세션에 아이디 값 저장 -> 로그인 상태로 변경
				// response.sendRedirect("welcome.jsp"); 이러한 방법으로 welcome 사이로 보내는 방법도 있지만,
				request.setAttribute("mid", mid); // request 에 아이디 값 저장 후 welcome 으로 같이 보냄
				viewPage = "welcome.jsp";
			} else { // 로그인 실패 시
				System.out.println("로그인 실패");
				
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다. <br> 다시 한번 확인 후 로그인 해주세요.");
				
				viewPage = "login.jsp";
			}
		} else if(command.equals("/login.do")) { // 로그인페이지로 이동
				viewPage = "login.jsp";
		} else if(command.equals("/welcome.do")) { // 웰컴페이지로 이동
			session = request.getSession();
			request.setAttribute("mid", session.getAttribute("sid"));
			viewPage = "welcome.jsp";
		} else if(command.equals("/logout.do")) {
			session = request.getSession(); // 세션 생성방법 
			session.invalidate();
			request.setAttribute("errorMsg", "로그아웃 하신 후 다시 로그인하시려면 아이디와 비밀번호를 입력해주세요.");
			viewPage = "login.jsp";
		} else if(command.equals("/freeboard.do")) { // 게시판 연결
			//게시판 더미데이터만들기
			List<BoardDto> boardList = new ArrayList<BoardDto>();
			
			boardList.add(new BoardDto("안녕하세요. 첫번쨰글입니다..", "일순신", "2025-08-01"));
			boardList.add(new BoardDto("안녕하세요. 두번째글입니다..", "이순신", "2025-08-02"));
			boardList.add(new BoardDto("안녕하세요. 세번째글입니다..", "삼순신", "2025-08-03"));
			boardList.add(new BoardDto("안녕하세요. 네번째글입니다..", "사순신", "2025-08-04"));
			boardList.add(new BoardDto("안녕하세요. 다섯번째글입니다..", "오순신", "2025-08-05"));
			
			request.setAttribute("boardList", boardList); // request 객체에 게시판 목록 싣기
			session = request.getSession();
			request.setAttribute("mid", session.getAttribute("sid"));
			viewPage = "boardList.jsp";
		} else if(command.equals("/member.do")) {
			
			List<memberdto> mdto = new ArrayList<memberdto>();
			
			mdto.add(new memberdto("tiger", "홍길동", "17", "2025-03-01"));
			mdto.add(new memberdto("lion", "김유신", "19", "2025-04-09"));
			mdto.add(new memberdto("blackcat", "이순신", "21", "2025-04-11"));
			mdto.add(new memberdto("whitedog", "강감찬", "27", "2025-05-05"));
			mdto.add(new memberdto("redtiger", "이몽룡", "31", "2025-06-20"));
			
			request.setAttribute("memberList", mdto);
			
			session = request.getSession(); // request 객체에 저장된걸 session으로  옮겨
			request.setAttribute("mid", session.getAttribute("sid")); // request 객체에 값 싣기
			viewPage = "memberList.jsp";
			
		}
		
			//response.sendRedirect(viewPage); 얘는 request 객체를 포함하지않아서 다른걸 써야함
		
			//viewPage에 저장된 jsp 페이지로 이동시킬때 request 객체를 포함해서 이동시키는 방법
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
	}
}
