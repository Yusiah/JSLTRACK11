package command_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberInfo implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
		String msg = "", url = "";
		
		if(id==null) {
			msg = "로그인 정보가 만료 되었습니다";
			url = "Member";
			request.setAttribute("t_msg", msg);
			request.setAttribute("t_url", url);
			request.setAttribute("urlGubun", "noSession");
		} else {
			request.setAttribute("urlGubun", "yesSession");
			
			MemberDto dto = dao.getMemberInfo(id);
			request.setAttribute("t_dto", dto);
		}

	}

}
