/**
 * Coppyright(C) [2019] Luvina software company
 *  DeleteUserController.Controller [Step 3, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;

/**
 *@author Quyetthang
 *thực hiện cho trường hợp xóa  
 */
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserController() {
		super();
	}

	/**
	 * xu ly khi bam nut xoa
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			int id = Common.convent(request.getParameter("id").toString(), ConstantUtils.DEFAULT_ID);
			//KIểm tra id có phải là số hay không và tồn tại user theo id
			if (id > ConstantUtils.DEFAULT_ID && tblUserLogic.checkExistTblUserByID(id)) { 
				// Kiểm tra xem có phải là admin không
			  	if (!tblUserLogic.checkRuleAdmin(id)) {
			  		// thực hiện xóa 
			  		if (tblUserLogic.deleteUser(id)) {
			  			// trường hơp xóa thành công
			  			response.sendRedirect(ConstantUtils.SUCCESSLINK +"?type=" + ConstantUtils.MSG003);
			  		} else {
			  			// trường hợp xóa không thành công 
			  			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" +ConstantUtils.ERR015);
			  		}
			  	} else {
			  		// không được xóa Admin
			  		response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" +ConstantUtils.ERR020);
			  	}
			} else {
				// User không tồn tại
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" +ConstantUtils.ERR014);
			}
		} catch (Exception e) {
			System.out.println("DeleteUsercontroller.doPost " +e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" +ConstantUtils.ERR015);
		}
	}
}
