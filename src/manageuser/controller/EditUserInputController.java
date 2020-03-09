/**
 * Coppyright(C) [2019] Luvina software company
 *  EditUserInput.Controller [Aug 28, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.MstGroupLogicImpl;
import manageuser.logics.Impl.MstJapanLogicImpl;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;
import manageuser.validates.ValidateUser;

/**
 *  @author Quyetthang
 *  Xử lý hiển thị cho màn ADM003 cho chức năng edit 
 */

public class EditUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserInputController() {
		super();

	}

	/**
	 * Lấy giá trị default có 3 trường hợp TH1. lấy từ màn hình ADM005 Th2. từ màn
	 * hình ADM003 lỗi TH3. từ trường hợp ADM004 back sang
	 * 
	 * @param request
	 * @return
	 */
	public UserInfor getDefaultValue(HttpServletRequest request) throws ServletException, IOException {
		UserInfor userInfor = new UserInfor();
		HttpSession session = request.getSession();
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		MstGroupLogic groupLogic = new MstGroupLogicImpl();
		MstJapanLogic japanLogic = new MstJapanLogicImpl();
		try {
			int id  = Common.convent(request.getParameter("id").toString(), ConstantUtils.DEFAULT_ID);
			String action = "";
			String year, month, day;
			
			if (request.getParameter(ConstantUtils.ACTION) != null) {
				action = request.getParameter(ConstantUtils.ACTION);
			}
			if (ConstantUtils.ACTION_BACK.equals(action)) { //TH1 : back
				// lấy key trên request để lấy được userInfor theo key
				String key = request.getParameter("key");
				// lấy dữ liệu từ session về userInfor
				userInfor = (UserInfor) session.getAttribute(key);
			} else if (ConstantUtils.Edit_ERR.equals(action)) { // TH2: ADM003 lấy lại
				// lấy thuộc tính của đổi tượng userInfor trên màn hình ADM003
				userInfor.setUserId(id);

				userInfor.setLoginName(request.getParameter("loginName"));

				userInfor.setGroupId(Integer.parseInt(request.getParameter("groupId")));
				// lấy giá trị group Name theo id group để khi hiển thị ADM004 là groupName
				userInfor.setGroupName(groupLogic.getGroupNameByGroupId(userInfor.getGroupId()));
				userInfor.setFullName(request.getParameter(ConstantUtils.FULL_NAME));
				// lấy đối tượng full Name katakana
				userInfor.setFullNameKata(
						request.getParameter("fullNameKata") != null ? request.getParameter("fullNameKata") : "");
				// lấy giá trị ngày tháng năm
				year = request.getParameter("namSinh");
				month = request.getParameter("thangSinh");
				day = request.getParameter("ngaySinh");
				userInfor.setBirthday(Common.converDaytoString(year, month, day));

				userInfor.setEmail(request.getParameter("email"));
				userInfor.setTel(request.getParameter("tel"));

				userInfor.setCodeLevel(request.getParameter("nameLevel"));
				userInfor.setNameLevel(japanLogic.getNameLevelByCodeLevel(userInfor.getCodeLevel()));
				// Lấy giá trị ngày bắt đầu
				year = request.getParameter("yearStartDate");
				month = request.getParameter("monthStartDate");
				day = request.getParameter("dayStartDate");
				userInfor.setStartDate(Common.converDaytoString(year, month, day));
				// lấy giá trị cho ngày hết hạn
				year = request.getParameter("yearEndDate");
				month = request.getParameter("monthEndDate");
				day = request.getParameter("dayEndDate");
				userInfor.setEndDate(Common.converDaytoString(year, month, day));
				// lấy giá trị total
				userInfor.setTotal(request.getParameter("total"));

			} else {	// TH 3 : Default vào DB lấy dữ liệu		
					userInfor = tblUserLogic.getUserInforById(id);
					userInfor.setBirthday(userInfor.getBirthday().replace("-", "/"));
					if (userInfor.getNameLevel() != null) {
						userInfor.setStartDate(userInfor.getStartDate().replace("-", "/"));
						userInfor.setEndDate(userInfor.getEndDate().replace("-", "/"));
					} else {
						LocalDate dateNow = LocalDate.now();
						year = dateNow.getYear() + "";
						month = dateNow.getMonthValue() + "";
						day = dateNow.getDayOfMonth() + "";
						userInfor.setStartDate(Common.converDaytoString(year, month, day));
						userInfor.setEndDate(Common.converDaytoString((Integer.parseInt(year) + 1) + "", month, day));
					}
				
			}
			// trường hợp dùng chung để xét các giá trị ngày tháng năm khi TH1 : Lấy từ
			// trong DB; TH2: khi Edit bị lỗi
			if (!ConstantUtils.ACTION_BACK.equals(action)) {
				userInfor.setLsBirthday(Common.splitStringToList(userInfor.getBirthday()));
				userInfor.setLsEndDay(Common.splitStringToList(userInfor.getEndDate()));
				userInfor.setLsStartDay(Common.splitStringToList(userInfor.getStartDate()));
			}

		} catch (Exception e) {
			System.out.println("EditUserInputController.getDefaultValue" + e.getMessage());
		}
		return userInfor;
	}

	/**
	 * xử lý từ màn hình ADM005 chuyển sang  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfor userInfor = new UserInfor();
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			int id = 0;
			id = Common.convent(request.getParameter("id").toString(), ConstantUtils.DEFAULT_ID);
			// Kiểm tra giá trị id và kiểm tra sự tồn tại user theo id
			if (id > ConstantUtils.DEFAULT_ID && tblUserLogic.checkExistTblUserByID(id)) {
				userInfor = tblUserLogic.getUserInforById(id);
				userInfor = getDefaultValue(request);
				if (userInfor != null) {
					Common.setDataLogic(request);					
					request.setAttribute("userInfor", userInfor);
					request.getRequestDispatcher(ConstantUtils.ADM003).forward(request, response);
				}
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
			}

		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			System.out.println("EditUserInput.doGet" + e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
		}
	}

	/**
	 * xử lý nút xác nhận bên ADM003
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfor userInfor = null;
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		HttpSession session = request.getSession();
		try {
			int id = 0;
			id = Common.convent(request.getParameter("id"), ConstantUtils.DEFAULT_ID);
			if (id > ConstantUtils.DEFAULT_ID && tblUserLogic.checkExistTblUserByID(id)) {
				userInfor = getDefaultValue(request);
				// tạo list lưu lỗi
				List<String> lsErr = new ArrayList<>();
				lsErr = ValidateUser.validateUserInfor(userInfor);
				if (lsErr.size() > 0) {
					Common.setDataLogic(request);
					request.setAttribute("userInfor", userInfor);
					request.setAttribute("lsErr", lsErr);
					request.getRequestDispatcher(ConstantUtils.ADM003).forward(request, response);
				} else {
					// tạo 1 key
					String keySession = Common.getSalt();
					// tao key 2 de danh dau
					String flag = ConstantUtils.KEY_ADM003;
					session.setAttribute(ConstantUtils.KEY_ADM003, flag);
					// sét lên session
					session.setAttribute(keySession, userInfor);
					response.sendRedirect("EditUserOK.do?keySession=" + keySession);
				}
			} else {
				response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR013);
			}
		} catch (Exception e) {
			System.out.println("EditUserinput.doPos " + e.getMessage());
			response.sendRedirect(ConstantUtils.SYSTEM_ERR + "?message=" + ConstantUtils.ERR015);
		}

	}

}
