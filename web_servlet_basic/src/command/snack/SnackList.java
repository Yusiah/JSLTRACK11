package command.snack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackList implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {

		SnackDao dao = new SnackDao();
		
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		String make = request.getParameter("t_make");
		
		System.out.println("select : "+select);
		
		if(select==null) {
			select = "p_code";
			search = "";
			make = "";
		}
		ArrayList<SnackDto> dtos = dao.getSnackList(select, search, make);
		
		ArrayList<SnackDto> dto2 = dao.getCompanyList();
		
		
		
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", select);
		request.setAttribute("t_search", search);
		request.setAttribute("t_make", make);
		request.setAttribute("t_dto2", dto2);
	}

}
