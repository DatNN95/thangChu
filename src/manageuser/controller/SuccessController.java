/**
 * Coppyright(C) [2019] Luvina software company
 *SucessController [2019]
 */
package manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.ConstantUtils;
import manageuser.utils.MessageErrProperties;

/**
 *  @author Quyetthang
 *  Hiển thị câu thông báo cho ADM006
 */
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuccessController() {
		super();

	}

	/**
	 * Hiển thị thông báo cho màn hình ADM006
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String type = ""; 
			String message = "";
			type = request.getParameter("type");
			if (type.equals(ConstantUtils.MSG001)) {
				// Nếu là add thành công
				message=MessageErrProperties.getData(ConstantUtils.MSG001);	
			} else if (type.equals(ConstantUtils.MSG002)) {
				// Nếu edit thành công
				message=MessageErrProperties.getData(ConstantUtils.MSG002);	
			} else if (type.equals(ConstantUtils.MSG003)) {
				// Nếu xóa thành công
				message=MessageErrProperties.getData(ConstantUtils.MSG003);	
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher(ConstantUtils.ADM006).forward(request, response);
		} catch(Exception e) {
			System.out.println("SuccessController " +e.getMessage());
		}
	}

}
