/**
 * Coppyright(C) [2019] Luvina software company
 *ValidateUser.java [Aug 3, 2019]
 */
package manageuser.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;
import manageuser.utils.MessageErrProperties;

/**
 * @author QuyetThang
 * 
 */
public class ValidateUser {

	/**
	 * Hàm validate cho phần login
	 * 
	 * @param loginName : tên login cần truyền vào
	 * @param pass      : mật khẩu truyền vào
	 * @return trả về danh sách lỗi (nếu có)
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 * @throws NoSuchAlgorithmException : lỗi khi mã hóa pass
	 */
	public static List<String> validateLogin(String loginName, String pass)
			throws NullPointerException, SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		// Tạo danh sách
		List<String> listErr = new ArrayList<String>();
		try {

			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			if (Common.checkEmpty(loginName)) {
				listErr.add(MessageErrProperties.getData("Error_01_loginName"));
			}
			if (Common.checkEmpty(pass)) {
				listErr.add(MessageErrProperties.getData("Error_01_pass"));
			}
			if (listErr.size() == 0) {
				if (!tblUserLogicImpl.checkUser(loginName, pass)) {
					listErr.add(MessageErrProperties.getData("Error_016"));
				}
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
			System.out.println("ValidateUser " + e.getMessage());
			throw e;
		}
		return listErr;
	}

	/**
	 * Lấy danh sach lỗi
	 * 
	 * @param userInfor tham số truyền vào
	 * @return trả về danh sách lỗi
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public static List<String> validateUserInfor(UserInfor userInfor)
			throws ClassNotFoundException, NullPointerException, SQLException {

		List<String> lsErrValidate = new ArrayList<String>();
		try {
			// Validate Login
			if (userInfor.getUserId() == 0) {
				// Validate loginName
				Common.addListErr(validateCheckLoginName(userInfor.getLoginName()), lsErrValidate);
			}
			// validate group
			Common.addListErr(validateCheckGroup(userInfor.getGroupId()), lsErrValidate);
			// Validate fullname
			Common.addListErr(validateCheckFullName(userInfor.getFullName()), lsErrValidate);
			// Validate Katakana
			Common.addListErr(validateCheckKatakana(userInfor.getFullNameKana()), lsErrValidate);
			// Validate birthday
			Common.addListErr(validateCheckBirthday(userInfor.getBirthday()), lsErrValidate);
			// validate Email
			Common.addListErr(validateCheckEmail(userInfor.getEmail(), userInfor.getUserId()), lsErrValidate);
			// Validate Tel
			Common.addListErr(validateCheckTel(userInfor.getTel()), lsErrValidate);

			if (userInfor.getUserId() == 0) {
				Common.addListErr(validateCheckPass(userInfor.getPass()), lsErrValidate);
				Common.addListErr(validateCheckConfirmPass(userInfor.getPass(), userInfor.getConfirmPass()),
						lsErrValidate);
			}
			if (!ConstantUtils.DEFAULT_CODE_LEVEL.equals(userInfor.getCodeLevel())) {
				// validate Code level
				Common.addListErr(validateCheckNameLevel(userInfor.getCodeLevel()), lsErrValidate);
				// validate start day
				Common.addListErr(validateCheckStartDate(userInfor.getStartDate()), lsErrValidate);
				// validate endDay
				Common.addListErr(validateCheckEndDate(userInfor.getStartDate(), userInfor.getEndDate()),
						lsErrValidate);
				// validate Total
				Common.addListErr(validateCheckTotal(userInfor.getTotal()), lsErrValidate);
			}
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("ValidateUser.validateUserInfor " + e.getMessage());
			throw e;
		}
		return lsErrValidate;
	}

	/**
	 * Kiểm tra loginName
	 * 
	 * @param loginName
	 * @return                 
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public static String validateCheckLoginName(String loginName)
			throws ClassNotFoundException, NullPointerException, SQLException {
		String message = "";
		loginName.trim();
		try {
			if (Common.checkEmpty(loginName)) {
				message = MessageErrProperties.getData("Error_01_loginName");
			} else if (!Common.checkFormat(loginName, ConstantUtils.FORMAT_LOGIN_NAME)) {
				message = MessageErrProperties.getData("Error_19_loginName");
			} else if (Common.checkLength(ConstantUtils.MIN_NAME, ConstantUtils.MAX_NAME, loginName)) {
				message = MessageErrProperties.getData("Error_07_loginName");
			} else {
				TblUserLogic tblUserLogic = new TblUserLogicImpl();
				if (tblUserLogic.checkExistLoginName(loginName)) {
					message = MessageErrProperties.getData("Error_03_loginName");
				}

			}
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("ValidateUser.validateCheckLoginName" + e.getMessage());
			throw e;
		}
		return message;
	}

	/**
	 * Kiem tra Group
	 * 
	 * @param groupId : tham số cần truyền vào
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckGroup(int groupId) throws NullPointerException, ClassNotFoundException, SQLException {
		String message = "";
		if (groupId == ConstantUtils.DEFAULT_GROUP_ID) {
			message = MessageErrProperties.getData("Error_02_groupName");
		} else if (!Common.checkExistGroup(groupId)) {
			message = MessageErrProperties.getData("Error_04_groupName");
		}
		return message;
	}

	/**
	 * Kiem tra truong full name
	 * 
	 * @param inputString : full name lấy về
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckFullName(String inputString) {
		String message = "";
		if (Common.checkEmpty(inputString)) {
			message = MessageErrProperties.getData("Error_01_fullName");
		} else if (!Common.checkMaxLength(inputString, ConstantUtils.MAX_LENGTH_255)) {
			message = MessageErrProperties.getData("Error_06_fullName");
		}
		return message;
	}

	/**
	 * Kiểm tra fullName Katakana
	 * 
	 * @param inputString : chữ kata lấy về
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckKatakana(String inputString) {
		String message = "";
		if (!"".equals(inputString)) {
			if (Common.checkFormatKana(inputString)) {
				message = MessageErrProperties.getData("Error_09_fullNameKata");
			} else if (!Common.checkMaxLength(inputString, ConstantUtils.MAX_LENGTH_255)) {
				message = MessageErrProperties.getData("Error_06_fullNameKata");
			}
		}
		return message;
	}

	/**
	 * Kiểm tra ngày sinh
	 * 
	 * @param inputString : ngày sinh
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckBirthday(String date) {
		String message = "";
		if (!Common.checkDate(date)) {
			message = MessageErrProperties.getData("Error_11_birthDay");
		}
		return message;
	}

	/**
	 * Kiểm tra email
	 * 
	 * @param inputString: email đầu vào
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckEmail(String email, int id)
			throws ClassNotFoundException, NullPointerException, SQLException {
		String message = "";

		try {
			if (Common.checkEmpty(email)) {
				message = MessageErrProperties.getData("Error_01_email");
			} else if (!Common.checkFormat(email, ConstantUtils.FORMAT_EMAIL)) {
				message = MessageErrProperties.getData("Error_05_email");
			} else if (!Common.checkMaxLength(email, ConstantUtils.MAX_100)) {
				message = MessageErrProperties.getData("Error_06_email");
			} else {
				TblUserLogic tblUserLogic = new TblUserLogicImpl();
				if (tblUserLogic.checkTblUserByEmail(email, id)) {
					message = MessageErrProperties.getData("Error_03_email");
				}
			}
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("ValidateUser.validateCheckEmail" + e.getMessage());
			throw e;
		}
		return message;
	}

	/**
	 * check số điện thoại
	 * 
	 * @param inputString đầu vào
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckTel(String tel) {
		String message = "";
		if (Common.checkEmpty(tel)) {
			message = MessageErrProperties.getData("Error_01_tel");
		} else if (!Common.checkMaxLength(tel, ConstantUtils.MAX_14)) {
			message = MessageErrProperties.getData("Error_06_tel");
		} else if (!Common.checkFormat(tel, ConstantUtils.FORMAT_PHONE_MUNBER)) {
			message = MessageErrProperties.getData("Error_05_tel");
		}
		return message;
	}

	/**
	 * check password
	 * 
	 * @param inputString chuỗi password
	 * @return chuỗi lõi (nếu có)
	 */
	public static String validateCheckPass(String password) {
		String message = "";
		if (Common.checkEmpty(password)) {
			message = MessageErrProperties.getData("Error_01_pass");
		} else if (Common.checkLength(ConstantUtils.Min_5, ConstantUtils.MAX_15, password)) {
			message = MessageErrProperties.getData("Error_07_pass");
		} else if (!Common.checkOneByte(password)) {
			message = MessageErrProperties.getData("Error_08_pass");
		}
		return message;
	}

	/**
	 * hàm check lỗi dữ liệu xác nhận mật khẩu đầu vào
	 * 
	 * @param pass        mật khẩu
	 * @param confirmPass mật khẩu xác nhận
	 * @return thông báo lỗi khi mật khẩu xác nhận vào mật khẩu không khớp với nhau
	 */

	public static String validateCheckConfirmPass(String pass, String confirmPass) {
		String message = "";
		// check lỗi Er017
		if (!Common.compare(pass, confirmPass)) {
			message = MessageErrProperties.getData("Error_17_confirmPass");
		}
		return message;
	}

	/**
	 * hàm check lỗi dữ liệu trình độ tiếng nhật đầu vào
	 * 
	 * @param list      danh sách tên trình độ tiếng nhật
	 * @param nameLevel trình độ tiếng nhật đã chọn
	 * @return thông báo lỗi khi trình độ tiếng nhật không tồn tại
	 */
	public static String validateCheckNameLevel(String codeLevel) {
		String message = "";
		// kiểm tra lỗi Er004
		if (!Common.checkExistNameLevel(codeLevel)) {
			message = MessageErrProperties.getData("Error_04_nameLevel");
		}
		return message;
	}

	/**
	 * hàm check dữ liệu ngày bắt đầu đầu vào
	 * 
	 * @param startDate ngày bắt đầu
	 * @return thông báo lỗi khi sai ngày bắt đầu
	 */
	public static String validateCheckStartDate(String startDate) {
		String message = "";
		// check lỗi Er011
		if (!Common.checkDate(startDate)) {
			message = MessageErrProperties.getData("Error_11_startDate");
		}
		return message;
	}

	/**
	 * hàm check dữ liệu ngày hết hạn
	 * 
	 * @param startDate chuỗi lưu thông tin của ngày bắt đầu
	 * @param endDate   chuỗi lưu thông tin của ngày hết hạn
	 * @return thông báo lỗi dữ liệu ngày hết hạn vi phạm lỗi
	 */
	public static String validateCheckEndDate(String startDate, String endDate) {
		String message = "";
		// check lỗi Er011
		if (!Common.checkDate(endDate)) {
			message = MessageErrProperties.getData("Error_11_endDate");
		} else if (Common.checkEndStartDay(startDate, endDate)) { // check lỗi Er012
			message = MessageErrProperties.getData("Error_12_endDate");
		}
		return message;
	}

	/**
	 * hàm check dữ liệu total đầu vào
	 * 
	 * @param total dữ liệu total đâu vapf
	 * @return thông báo lỗi nều dữ liệu total vi phạm
	 */
	public static String validateCheckTotal(String inputString) {
		String message = "";
		// check lỗi Er001
		if (Common.checkEmpty(inputString)) {
			return MessageErrProperties.getData("Error_01_total");
		} else if (!Common.checkMaxLength(inputString, ConstantUtils.MAX_09)) { // check lỗi Er006
			return MessageErrProperties.getData("Error_06_total");
		} else if (!Common.checkFormat(inputString, ConstantUtils.FORMAT_HAFT_SIZE)) { // check lỗi Er018
			return MessageErrProperties.getData("Error_18_total");
		}
		return message;
	}

}
