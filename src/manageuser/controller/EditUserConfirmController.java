/**
 * Coppyright(C) [2019] Luvina software company
 *EditUserController [2019]
 */
package manageuser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblDetailUserJapanLogicImpl;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;

/**
 * @author Quyetthang
 * thực hiện  chuyển từ ADM003 sang ADM004 cho chức năng Edit
 */
public class EditUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserConfirmController() {
		super();
	}

	/**
	 * hàm thực hiện chuyển từ màn hình ADM003 sang ADM004 cho chức năng Edit
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			// biến để Đánh dấu sang màn ADM004 để tránh lỗi coppy link url ADM004
			String flag = (String) session.getAttribute(ConstantUtils.KEY_ADM003);
			session.removeAttribute(ConstantUtils.KEY_ADM003);
			if (Common.compare(flag, ConstantUtils.KEY_ADM003)) {
				UserInfor userInfor = new UserInfor();
				// tạo biến lấy key 2 tap				
				String keySession = request.getParameter("keySession");
				// xét lên request k bị mất
				request.setAttribute("keySession", keySession);
				// lấy dữ liệu từ session về userInfor
				userInfor = (UserInfor) session.getAttribute(keySession);
				TblUserLogic tblUserLogic = new TblUserLogicImpl();
				if (tblUserLogic.checkExistTblUserByID(userInfor.getUserId())) {
					request.setAttribute("userInfor", userInfor);
					request.getRequestDispatcher(ConstantUtils.ADM004).forward(request, response);
				} else {
					response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
				}
				
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
			}
		} catch (Exception e) {
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
			System.out.println("EditUserConfim.doGet " + e.getMessage());
		}
	}

	/**
	 * hàm thực hiện khi click nút OK trên màn hình ADM004 cho chức năng Edit
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		TblDetailUserJapanLogic detailUserJapanLogic = new TblDetailUserJapanLogicImpl();
		boolean check = false;
		boolean flag = false;
		try {
			HttpSession session = request.getSession();
			String keySession = request.getParameter("keySession");
			// lấy đối tượng userInfor theo key
			UserInfor userInfor = (UserInfor) session.getAttribute(keySession);
			// xóa thông tin key trên session
			session.removeAttribute(keySession);
			if (tblUserLogic.checkExistTblUserByID(userInfor.getUserId())) {
				// Kiểm tra sự tồn tại của Codelevel trong BD
				flag = detailUserJapanLogic.checkExistCodeLevel(userInfor.getUserId());
				userInfor.setIsExistCodelevel(flag);
				// xét sự tồn tai TblUser theo Id
				if (tblUserLogic.checkTblUserByEmail(userInfor.getEmail(), userInfor.getUserId())) {
					// nếu email đã tồn tại
					response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
				} else {
					check = tblUserLogic.updateUser(userInfor);
					if (check) {
						// sang ADM006 thông báo thành công
						response.sendRedirect(ConstantUtils.SUCCESSLINK + "?type=" + ConstantUtils.MSG002);
					} else {
						// nếu không chuyển sang màn hình lỗi
						response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
					}
				}
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
			}
		} catch (Exception e) {
			System.out.println("EditUserConfimController.doPost" + e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
		}
	}

}
