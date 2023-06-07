package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackUpdate implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		
		SnackDao dao = new SnackDao();
		String p_code = request.getParameter("t_p_code");
		SnackDto dto = dao.getSnackView(p_code);
		ArrayList<SnackDto> dtos = dao.getCompanyList();
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_company_dto", dtos);
	}

}
