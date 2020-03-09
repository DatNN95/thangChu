/**
 * Coppyright(C) [2019] Luvina software company
 *SystemErrController [2019]
 */
package manageuser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.MessageErrProperties;

/**
 *  @author Quyetthang
 * Servlet implementation class SystemErrController
 */

public class SystemErrController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemErrController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// lấy thông báo lỗi từ mã lỗi và đẩy lên request
			String messageError = MessageErrProperties.getData(request.getParameter("message"));
			request.setAttribute("messageError", messageError);

			// di chuyển sang màn hình systemError
			request.getRequestDispatcher("/JSP/ERR.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("SystemErrorController.doGet : " + e.getMessage());
		}
	}

	
}
