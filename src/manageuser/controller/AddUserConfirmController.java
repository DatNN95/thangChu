/**
 * Coppyright(C) [2019] Luvina software company
 *  AddUserConfim.Controller [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;

/**
 *  @author Quyetthang
 *  Cho trường hợp chuyển AddUser từ ADM003 sang ADM004
 */

public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserConfirmController() {
		super();
	}

	/**
	 * xử lý hiển thị dữ liệu từ màn hình ADM003 sang ADM004 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UserInfor userInfor = new UserInfor();
			// Lấy biến đánh dấu từ bên ADM003 phòng cho trường hợp khi nó không đi từ ADM003 --> ADM004
			String flag = session.getAttribute(ConstantUtils.KEY_ADM003).toString();
			session.removeAttribute(ConstantUtils.KEY_ADM003);	
			// Kiểm tra xem biến đánh dấu có giá trị bằng nhau hay không
			if (Common.compare(flag, ConstantUtils.KEY_ADM003)) {
				String keySession = request.getParameter(ConstantUtils.KEY_SESSION);
				// xét lên request để cho trường hợp nhấn OK
				request.setAttribute(ConstantUtils.KEY_SESSION, keySession);
				// lấy dữ liệu từ session về userInfor
				userInfor = (UserInfor) session.getAttribute(keySession);
				request.setAttribute("userInfor", userInfor);
				request.getRequestDispatcher(ConstantUtils.ADM004).forward(request, response);
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			}			 
		} catch (Exception e) {
			System.out.println("AddUserConfim.doGet " + e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			
		}
	}

	/**
	 * Xử lý khi click nút Ok trên ADM004
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Lấy giá trị key về
	   TblUserLogic tblUserLogic = new TblUserLogicImpl();
			String keySession = request.getParameter("keySession");
			// lấy đối tượng userInfor theo key
			UserInfor userInfor = (UserInfor) session.getAttribute(keySession);	
			// xóa thông tin key trên session
		     session.removeAttribute(keySession);
			try {
				// xét trường hợp loginName và Email n=bị trùng bị trùng phòng trường hợp có người insert 
				if (!tblUserLogic.checkExistLoginName(userInfor.getLoginName()) && !tblUserLogic.checkTblUserByEmail(userInfor.getEmail(), userInfor.getUserId())) {                              
                     if (tblUserLogic.createUser(userInfor)) {
                    	 // nếu check = true thì gửi sang cho thông báo thành công
                    	 response.sendRedirect(ConstantUtils.SUCCESSLINK + "?type=" + ConstantUtils.MSG001);
                     } else {   
                    	 response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
                     }
				} else {   
               	 	response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
                }
			} catch (Exception e) {
				// lỗi chuyển sang màn hình lỗi 
				System.out.println("AddUserConfirmController.doPost: " + e.getMessage());
				response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message=" + ConstantUtils.ERR015);				
			}
	}

}
