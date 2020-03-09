/**
 * Coppyright(C) [2019] Luvina software company
 *AddUserInputController [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
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
import manageuser.logics.Impl.MstGroupLogicImpl;
import manageuser.logics.Impl.MstJapanLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;
import manageuser.validates.ValidateUser;

/**
 *@author Quyetthang
 * Class để hiển thị trường hợp hiển thị ADM003 cho trường hợp add 
 */
public class AddUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserInputController() {
		super();
	}

	/**
	 * Lấy giá trị mặc định của màn hình ADM003 gán vào đối tượng UserInfor. Lưu ý:
	 * Giá trị mặc định của màn hình ADM003 có các trường hợp: 1. Từ ADM002 sang
	 * ADM003 2. Từ ADM003 submit nhưng bị lỗi 3. Từ ADM004 quay về ADM003
	 * 
	 * @param request dùng để lấy giá trị trên req
	 * @return 1 userInfor
	 */
	public UserInfor getDefaultValue(HttpServletRequest request) throws ServletException, IOException {

		UserInfor userInfor = new UserInfor();
		HttpSession session = request.getSession();
		MstJapanLogic japanLogic = new MstJapanLogicImpl();
		MstGroupLogic groupLogic = new MstGroupLogicImpl();
		try {
			String action = "";
			String year, month, day;
			// kiểm tra action

			if (request.getParameter(ConstantUtils.ACTION) != null) {
				action = request.getParameter(ConstantUtils.ACTION);
			}
			// trường hợp back từ ADM004 sang
			if (ConstantUtils.ACTION_BACK.equals(action)) {
				String key = request.getParameter("key");
				// lấy dữ liệu từ session về userInfor
				userInfor = (UserInfor) session.getAttribute(key);
			} else if (ConstantUtils.Add_ERR.equals(action)) { // trường hợp xác nhận bị lỗi              
				userInfor.setUserId(0);				
				userInfor.setLoginName(request.getParameter("loginName"));

				userInfor.setGroupId(Integer.parseInt(request.getParameter("groupId")));
				// lấy giá trị group Name theo id group để khi hiển thị ADM004 là groupName
				userInfor.setGroupName(groupLogic.getGroupNameByGroupId(userInfor.getGroupId()));
				// lấy giá trị FullName
				userInfor.setFullName(request.getParameter(ConstantUtils.FULL_NAME));
				// lấy giá trị FullNamekata
				userInfor.setFullNameKata(request.getParameter("fullNameKata") != null ?
						request.getParameter("fullNameKata").toString(): "");
				// lấy giá trị ngày tháng năm
				year = request.getParameter("namSinh").toString();
				month = request.getParameter("thangSinh").toString();
				day = request.getParameter("ngaySinh").toString();

				userInfor.setBirthday(Common.converDaytoString(year, month, day));

				userInfor.setEmail(request.getParameter("email"));
				userInfor.setTel(request.getParameter("tel"));
				userInfor.setPass(request.getParameter("password"));
				userInfor.setConfirmPass(request.getParameter("confirmPass"));

				userInfor.setCodeLevel(request.getParameter("nameLevel"));
				
				userInfor.setNameLevel(japanLogic.getNameLevelByCodeLevel(userInfor.getCodeLevel()));

				year = request.getParameter("yearStartDate");
				month = request.getParameter("monthStartDate");
				day = request.getParameter("dayStartDate");
				userInfor.setStartDate(Common.converDaytoString(year, month, day));
				// ngày tháng năm của ngày hết hạn
				year = request.getParameter("yearEndDate");
				month = request.getParameter("monthEndDate");
				day = request.getParameter("dayEndDate");
				userInfor.setEndDate(Common.converDaytoString(year, month, day));
				userInfor.setTotal(request.getParameter("total"));

			} else {
				// set giá trị mặc định của các hạng mục textbox là blank ("")
				userInfor.setLoginName("");
				userInfor.setGroupId(0);
				userInfor.setFullName("");
				userInfor.setFullNameKata("");
				// set ngày tháng năm sinh hiện tại
				// tạo biến lưu ngày hiện tại
				LocalDate dateNow = LocalDate.now();
				year = Integer.toString(dateNow.getYear());
				month = Integer.toString(dateNow.getMonthValue());
				day = Integer.toString(dateNow.getDayOfMonth());
				// set các trường cho userInfor
				userInfor.setBirthday(Common.converDaytoString(year, month, day));
				userInfor.setEmail("");
				userInfor.setTel("");
				userInfor.setPass("");
				userInfor.setConfirmPass("");
				userInfor.setCodeLevel("");
				// set giá trị cho ngày bắt đầu theo kiểu year + "/" + month + "/" + day;
				userInfor.setStartDate(Common.converDaytoString(year, month, day));
				userInfor.setEndDate(Common.converDaytoString((Integer.parseInt(year) + 1) + "", month, day));
				userInfor.setTotal("");								
			}
			// trường hợp dùng chung để xét các giá trị ngày tháng năm khi TH1 : default; TH2: khi add bị lỗi
			if (!ConstantUtils.ACTION_BACK.equals(action)) {
				userInfor.setLsBirthday(Common.splitStringToList(userInfor.getBirthday()));
				userInfor.setLsEndDay(Common.splitStringToList(userInfor.getEndDate()));
				userInfor.setLsStartDay(Common.splitStringToList(userInfor.getStartDate()));
			}
					
		} catch (Exception e) {
			System.out.println("AddUserInputController.getDefaultValue: " + e.getMessage());
		}
		return userInfor;
	}

	/**
	 * Xử lý nút chuyển từ Mh ADM002 vào ADM003
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// xét giá trị cho các ô select box
			Common.setDataLogic(request);
			UserInfor userInfor = new UserInfor();
			userInfor = getDefaultValue(request);
			request.setAttribute("userInfor", userInfor);
			request.getRequestDispatcher(ConstantUtils.ADM003).forward(request, response);
			
		} catch (Exception e) {
			System.out.println("AddUserInput.doGet " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Xử lý button xác nhận bên ADM003 check validate
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UserInfor userInfor = new UserInfor();
			userInfor = getDefaultValue(request);
			// tạo list lưu lỗi
			List<String> lsErr = new ArrayList<>();
			lsErr = ValidateUser.validateUserInfor(userInfor);
			
			if (lsErr.size() > 0) {
				// lay laij gia tri cho cac muc
				Common.setDataLogic(request);
				request.setAttribute("userInfor", userInfor);
				request.setAttribute("lsErr", lsErr);
				request.getRequestDispatcher(ConstantUtils.ADM003).forward(request, response);
			} else {
				
				// tạo 1 key phòng tránh trường hợp 2 tap phân biệt giữa các user
				String keySession = Common.getSalt();
				// tao key 2 de danh dau : gửi lên ression để đỡ bị lộ
				String flag = ConstantUtils.KEY_ADM003;
				session.setAttribute(ConstantUtils.KEY_ADM003, flag);
				// sét lên session
				session.setAttribute(keySession, userInfor);
				response.sendRedirect(request.getContextPath() + "/"+ConstantUtils.ADDUSER_CONFIM+"?"+ConstantUtils.KEY_SESSION+ "=" + keySession);
			}
		} catch (Exception e) {
			response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			System.out.println("AddUserinput.doPos " + e.getMessage());
		
		}
	}
}
