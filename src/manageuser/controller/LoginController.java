/**
 * Coppyright(C) [2019] Luvina software company
 *TblUserDaoImpl.java [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.ConstantUtils;
import manageuser.validates.ValidateUser;

/**
 *  @author Quyetthang
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/JSP/ADM001.jsp").forward(request, response);
	}

	/**
	 * xử lý khi click đăng nhập 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lấy địa chỉ url
		String contextPath = request.getContextPath();
		// tạo mới đối tượng HttpSession
		HttpSession session = request.getSession();
		try {
			String loginName = request.getParameter("loginName").toString();
			String pass = request.getParameter("pass").toString();

			// khai báo danh sách lỗi
			List<String> lsErrMessage = new ArrayList<String>();
			// lấy danh sách lỗi
			lsErrMessage = ValidateUser.validateLogin(loginName, pass);
             
			// kiểm tra xem danh sách lỗi có lỗi hay không
			if (lsErrMessage.size() != 0) {
				// lưu thông tin của tên đăng nhập vào request
				request.setAttribute("loginID", loginName);
				// lưu thông tin của danh sách lỗi vào request với tên biến là listError
				request.setAttribute("listError", lsErrMessage);
				// chuyển sang trang ADM001 và chuyển cả thông tin request và response
				request.getRequestDispatcher("/" + ConstantUtils.LINKADM001).forward(request, response);
			} else {
				// lưu dữ liệu vào session
				session.setAttribute(ConstantUtils.LOGIN_NAME, loginName);
				// chuyển sang màn hình ADM002
				response.sendRedirect(contextPath + ConstantUtils.URL_LISTUSER_DO);
			}
		} catch (Exception e) {
			response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			System.out.println("LoginController " +e.getMessage());
		}
	}

}
