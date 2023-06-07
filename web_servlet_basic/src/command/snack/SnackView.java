package command.snack;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackView implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		
		SnackDao dao = new SnackDao();
		
		String p_code = request.getParameter("t_p_code");
		SnackDto dto = dao.getSnackView(p_code);
		
		request.setAttribute("t_dto", dto);

	}

}
