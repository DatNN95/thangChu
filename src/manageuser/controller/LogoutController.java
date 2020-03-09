/**
 * Coppyright(C) [2019] Luvina software company
 * [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  @author Quyetthang
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			// xóa session
			session.invalidate();
			// chuyển lại màn hình ADM001
			response.sendRedirect(request.getContextPath());
			} catch (Exception e) {
				System.out.println("LogoutController.doGet: " + e.getMessage());
			}
	}

	

}
