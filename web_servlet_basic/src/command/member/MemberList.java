package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class MemberList {
	public void excute(HttpServletRequest request) {
		
		MemberDao dao = new MemberDao();
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		
		System.out.println("select : "+select);
		
		if(select == null) {
			select = "id";
			search = "";
			
		}
		ArrayList<MemberDto> dtos = dao.getMemberList(select, search);
		
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
	}

}
