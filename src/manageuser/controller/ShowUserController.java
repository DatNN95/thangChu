/**
 * Coppyright(C) [2019] Luvina software company
 * ShowUserController.java [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;
import manageuser.utils.MessageErrProperties;

/**
 * @author Quyetthang Servlet implementation class ShowUserController
 */
public class ShowUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowUserController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		UserInfor userInfor = new UserInfor();
		try {
			// kiêm tra parseint có đúng là số hay không
			int id = Common.convent(request.getParameter("id").toString(), ConstantUtils.DEFAULT_ID);
			// id ==0 nó là lỗi
			if (id > ConstantUtils.DEFAULT_ID && tblUserLogic.checkExistTblUserByID(id)) {
				if (tblUserLogic.checkRuleAdmin(id)) {
					response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
				} else {
					userInfor = tblUserLogic.getUserInforById(id);
					// xét chuỗi Confirm để xác nhận trước khi xóa
					String messageConfirm = MessageErrProperties.getData(ConstantUtils.STRING_CONFRIM);
					request.setAttribute("messageConfirm", messageConfirm);
					// set đối tượng userInfor lên request
					request.setAttribute("userInfor", userInfor);
					request.getRequestDispatcher(ConstantUtils.ADM005).forward(request, response);
				}
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
			}
		} catch (Exception e) {
			System.out.println("ShowUserController.doGet" + e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
		}

	}
}
