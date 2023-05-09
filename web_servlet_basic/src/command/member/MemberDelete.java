package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.MemberDao;

public class MemberDelete implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		
		int result = dao.memberDelete(id);
		
		String msg = "삭제성공";
		String url = "Member";
//		String url = "MemberList";
		if(result !=1) msg= "삭제실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}

}
