package command.snack;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SnackDao;

public class SnackDelete implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		
		SnackDao dao = new SnackDao();
		String p_code = request.getParameter("t_p_code");
		int result = dao.snackDelete(p_code);
		
		String msg = "삭제성공";
		String url = "Snack";
		if(result !=1) msg= "삭제실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);

	}

}
