package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.MemberDelete;
import command.member.MemberList;
import command.member.MemberSave;
import command.member.MemberUpdate;
import command.member.MemberView;
import common.CommonExcute;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String gubun = request.getParameter("t_gubun");
		String viewPage = "";
		if(gubun == null) gubun = "list";
		
		// 목록
		if(gubun.equals("list")) {
			MemberList memberList = new MemberList();
			memberList.excute(request);
//			viewPage="member/member_list.jsp";
			viewPage="member/member_list_jstl.jsp";
			
		// 등록 폼
		} else if(gubun.equals("writeForm")) {
			viewPage="member/member_write_jstl.jsp";
			
		// 저장
		} else if(gubun.equals("memberSave")) {
			MemberSave member = new MemberSave();
			member.excute(request);
			viewPage="common_alert.jsp";
			
		// 상세조회
		} else if(gubun.equals("memberView")) {
			CommonExcute member = new MemberView();
			member.excute(request);
			viewPage="member/member_view_jstl.jsp";
			
		// 수정 폼	
		} else if(gubun.equals("memberUpdateForm")) {
			CommonExcute member = new MemberView();
			member.excute(request);
			viewPage="member/member_update_jstl.jsp";
			
		// 수정 저장	
		} else if(gubun.equals("memberUpdate")) {
			CommonExcute member = new MemberUpdate();
			member.excute(request);
			viewPage="common_alert.jsp";
		
		// 삭제	
		} else if(gubun.equals("memberDelete")) {
			CommonExcute member = new MemberDelete();
			member.excute(request);
			viewPage="common_alert.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
