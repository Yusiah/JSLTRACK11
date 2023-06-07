package command.snack;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import dao.SnackDao;
import dto.SnackDto;

public class SnackSave implements CommonExcute {

	@Override
	public void excute(HttpServletRequest request) {
		
		SnackDao dao = new SnackDao();
		
		String p_code = request.getParameter("t_p_code");
		String p_name = request.getParameter("t_p_name");
		String price = request.getParameter("t_price");
		String m_code = request.getParameter("t_m_code");
		if(price.equals("")) price="0";
		
		int price2 = Integer.parseInt(price);
		
		
		SnackDto dto = new SnackDto(p_code, p_name, price2, m_code);
		
		int result = dao.snackSave(dto);
		
		String msg = "등록성공";
		String url = "Snack";
		if(result !=1) {
			msg = "등록실패";
//			url = "SnackWrite";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);

	}

}
