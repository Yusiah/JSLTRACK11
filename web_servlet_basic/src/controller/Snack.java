package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.snack.DBSnackUpdate;
import command.snack.SnackDelete;
import command.snack.SnackList;
import command.snack.SnackSave;
import command.snack.SnackUpdate;
import command.snack.SnackView;
import common.CommonExcute;


/**
 * Servlet implementation class Snack
 */
@WebServlet("/Snack")
public class Snack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Snack() {
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
			SnackList snackList = new SnackList();
			snackList.excute(request);
			viewPage = "snack/snack_list.jsp";
			
		// 등록 폼	
		} else if(gubun.equals("writeForm")) {
			viewPage="snack/snack_write.jsp";
			
		// 저장	
		} else if(gubun.equals("snackSave")) {
			SnackSave snack = new SnackSave();
			snack.excute(request);
			viewPage="common_alert.jsp";
			
		// 상세조회
		} else if(gubun.equals("snackView")) {
			SnackView snack = new SnackView();
			snack.excute(request);
			viewPage="snack/snack_view.jsp";
		
		// 수정 폼	
		} else if(gubun.equals("snackUpdateForm")) {
			SnackUpdate snack = new SnackUpdate();
			snack.excute(request);
			viewPage="snack/snack_update.jsp";
			
		// 수정 저장	
		} else if(gubun.equals("snackUpdate")) {
			DBSnackUpdate snack = new DBSnackUpdate();
			snack.excute(request);
			viewPage="common_alert.jsp";
			
		// 삭제
		} else if(gubun.equals("snackDelete")) {
			SnackDelete snack = new SnackDelete();
			snack.excute(request);
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
