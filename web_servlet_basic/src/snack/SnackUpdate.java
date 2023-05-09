package snack;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SnackDao;
import dto.SnackDto;

/**
 * Servlet implementation class SnackUpdate
 */
@WebServlet("/SnackUpdate")
public class SnackUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SnackDao dao = new SnackDao();
		String p_code = request.getParameter("t_p_code");
		SnackDto dto = dao.getSnackView(p_code);
		ArrayList<SnackDto> dtos = dao.getCompanyList();
		request.setAttribute("t_dto", dto);
		request.setAttribute("t_company_dto", dtos);
		RequestDispatcher rd = request.getRequestDispatcher("snack/snack_update.jsp");
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
