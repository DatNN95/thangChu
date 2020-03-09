/**
 * Coppyright(C) [2019] Luvina software company
 *Common.java [Aug 6, 2019]
 */
package manageuser.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.Impl.MstGroupLogicImpl;
import manageuser.logics.Impl.MstJapanLogicImpl;

/**
 * @author QuyetThang chứa những hàm hay dùng
 */
public class Common {
	/**
	 * 
	 * Hàm mã hóa password
	 * 
	 * @param pass : mật khẩu
	 * @param salt : muối
	 * @return : chuỗi đã được mã hóa
	 * @throws NoSuchAlgorithmException // lỗi mã hóa getInstance
	 */
	public static String getSHA1Pass(String pass, String salt) throws NoSuchAlgorithmException {
		StringBuffer sb = null;
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest((pass + salt).getBytes());
			sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("getSHA1" +e.getMessage());
			throw e;
		}
		return sb.toString();
	}

	/**
	 * SO sánh hai chuỗi
	 * @param a : đầu vào 1 
	 * @param b : đầu vào 2
	 * @return : true nếu 2 chuỗi bằng nhau, false : hai chuỗi khác nhau
	 */
	public static Boolean compare(String a, String b) {
		boolean check = false;
		if (a.equals(b)) {
			check = true;
		}
		return check;
	}

	/**
	 * xử lý lỗi wildCard cho mệnh đề like
	 * @param fullName chuỗi truyền vào
	 * @return trả về chuỗi đúng khi có có các kí tự đặc biệt
	 */
	public static String replaceWildCard(String fullName) {
		if (fullName.length() != 0)
			fullName = fullName.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_").replace("[", "\\[");
		return fullName;
	}

	/**
	 * Lấy vị trí cần hiển thị 
	 * @param currentPage : vị trí hiện tại
	 * @param limit giới hạn số record/ page
	 * @return vị trí data cần hiển thị
	 */
	public static int getOffset(int currentPage, int limit) {
		return limit * (currentPage - 1);
	}

	/**
	 * Lấy số lượng max hiển thị record/page
	 * 
	 * @return trả về số giới hạn là 5 record/page
	 */
	public static int getLimit() {
		return Integer.parseInt(ConfigUtils.getData("LimitRecord"));
	}

	/**
	 * Tính tổng số Page
	 * 
	 * @param totalUser tổng số user
	 * @param limit     Số record giới hạn trên page
	 * @return số bản ghi
	 */
	public static int getTotalPage(int totalUser, int limitRecord) {
		// match.ceil làm tròn số nếu còn dư
		int totalPage = (int) Math.ceil((double) totalUser / limitRecord);
		return totalPage;
	}

	/**
	 * Lấy danh sách paging
	 * 
	 * @param totalRecord tổng số bản ghi
	 * @param limit       giới hạn record
	 * @param currenPage  page hiện tại
	 * @return danh sách paging
	 */
	public static List<Integer> getListPaging(int totalRecord, int limit, int currentPage)
			throws NumberFormatException {

		List<Integer> lsPaging = new ArrayList<Integer>();
		try {
			if (totalRecord <= limit) {
				lsPaging = null;
			} else {
				int totalPage = getTotalPage(totalRecord, limit);
				int limitPage = Integer.parseInt(ConfigUtils.getData("LimitPage"));
				int min = 1 + limitPage * ((currentPage - 1) / limitPage);
				int max = min + limitPage - 1;
				// bắt trường hợp dữ liệu k đủ cho để hiện 3 bản ghi vd 50 record,
				if (max > totalPage) {
					max = totalPage;
				}
				// list
				for (int i = min; i <= max; i++) {
					lsPaging.add(i);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Common.getListPaging " + e.getMessage());
		}
		return lsPaging;
	}

	/**
	 * hàm lấy danh sách năm
	 * 
	 * @param startYear năm bắt đầu
	 * @param endYear   năm kết thúc
	 * @return List<Integer> danh sách năm
	 */
	public static List<Integer> getListYear(int startYear, int endYear) {
		// tạo 1 danh sách để lưu danh sách các năm
		List<Integer> lsYear = new ArrayList<>();
		// duyệt các năm từ năm bắt đầu đến năm kết thúc lưu vào danh sách
		for (int year = startYear; year <= endYear; year++) {
			lsYear.add(year);
		}
		return lsYear;
	}

	/**
	 * hàm lấy danh sách tháng
	 * 
	 * @return danh sách tháng
	 */
	public static List<Integer> getListMonth() {
		List<Integer> lsMonth = new ArrayList<Integer>();
		// duyệt các tháng
		for (int i = 1; i <= 12; i++) {
			lsMonth.add(i);
		}
		return lsMonth;
	}

	/**
	 * Lấy danh sách ngày
	 * 
	 * @return list danh sách ngày
	 */
	public static List<Integer> getListDay() {
		List<Integer> lsDay = new ArrayList<Integer>();
		// duyệt các tháng
		for (int i = 1; i <= 31; i++) {
			lsDay.add(i);
		}
		return lsDay;
	}

	/**
	 * hàm check lỗi không nhập ER001
	 * 
	 * @param string chuỗi cần kiểm tra
	 * @return
	 *
	 *         boolean true - lỗi không nhập false - có nhập
	 */
	public static boolean checkEmpty(String inputString) {
		// so sánh chuỗi rỗng với chuối cần kiểm tra
		if ("".equals(inputString.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * hàm check lỗi độ dài trong khoảng ER007
	 * 
	 * @param minLength số kí tự nhỏ nhất
	 * @param maxLength số kí tự lớn nhật
	 * @param string    chuỗi cần kiểm tra
	 * @return boolean true có lỗi false không có lỗi
	 */
	public static boolean checkLength(int minLength, int maxLength, String inputString) {
		// lấy độ dài của chuỗi cần kiểm tra
		int lenght = inputString.length();
		// so sánh độ dài của chuỗi với khoảng độ dài cho phép
		if (lenght < minLength || lenght > maxLength) {
			return true;
		}
		return false;
	}

	/**
	 * hàm check maxlength của dữ liệu đầu vào
	 * 
	 * @param string chuỗi dữ liệu đầu vào
	 * @param length độ dài tối đa của chuỗi
	 * @return true khi đúng nhỏ, flase khi lớn hơn
	 */
	public static boolean checkMaxLength(String inputString, int length) {
		// kiểm tra số kí tự của chuỗi có lớn độ dài tối đa hay không
		if (inputString.length() > length) {
			return false;
		}
		return true;
	}

	/**
	 * hàm kiểm tra năm nhuận
	 * 
	 * @param year năm
	 * @return true: năm nhuận false: năm không nhuận
	 */
	public static boolean checkNamNhuan(int year) {
		// kiểm tra 1 năm có phải là năm nhuận hay không
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra có chọn ác mục select box hay không
	 * @param inputString chuỗi Default
	 * @return true khi không chọn, false khi chọn
	 */
	public static boolean checkNoChoose(String inputString) {
		if (inputString.equals(ConstantUtils.GROUP_DEFAULT)) {
			return true;
		}
		return false;
	}

	/**
	 * kiểm tra tồn tại của codelevel
	 * 
	 * @param codelevel chuỗi cần kiểm tra
	 * @return true : chuỗi cần kiểm tra không tồn tại trong danh sách dữ liệu false
	 *         : chuỗi cần kiểm tra tồn tại trong danh sách dữ liệu
	 */
	public static boolean checkExistNameLevel(String codeLevel) {
		// kiểm tra xem danh sách dữ liệu có chứa chuỗi cần kiểm tra hay không
		boolean check = false;
		MstJapanLogic japanLogic = new MstJapanLogicImpl();
		try {
			if (japanLogic.checkExistNameLevel(codeLevel)) {
				check = true;
			} else {
				check = false;
			}
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			System.out.println("Common.checkExistNameLevel");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Kiểm tra sự tồn tại group
	 * 
	 * @param groupId đầu vào
	 * @return true khi tồn tại
	 */
	public static boolean checkExistGroup(int groupId) throws NullPointerException, ClassNotFoundException, SQLException {
		// kiểm tra xem danh sách dữ liệu có chứa chuỗi cần kiểm tra hay không
		MstGroupLogic groupLogic = new MstGroupLogicImpl();
		String name = "";
		boolean check = false;
		try {
			name = groupLogic.getGroupNameByGroupId(groupId);
			if (name.length() != 0) {
				check = true;
			} else {
				check = false;
			}
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			System.out.println("Common.checkExistGroup" +e.getMessage());
			throw e;
		}
		return check;
	}
	/**
	 * hàm check sai format của dữ liệu đầu vào
	 * 
	 * @param text   dữ liệu đầu vào
	 * @param format chuỗi format
	 * @return true: dữ liệu đầu vào sai format false: dữ liệu đầu vào đúng format
	 */
	public static boolean checkFormat(String text, String format) {
	    boolean check = false;
	    check = text.matches(format);
		if (check) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra ngày hợp lệ hay không
	 * 
	 * @param date đầu vào
	 * @return kiểm tra đúng ngày tháng năm không
	 */
	public static boolean checkDate(String date) {
		String[] lsdate = date.split("/");
		int index = 0;
		boolean check = false;
		int year = Integer.parseInt(lsdate[index++]);
		int month = Integer.parseInt(lsdate[index++]);
		int day = Integer.parseInt(lsdate[index++]);
		// tạo biến mã cho các tháng
		int daymax = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: {
			daymax = 31;

			break;
		}
		case 4:
		case 6:
		case 9:
		case 11: {
			daymax = 30;
			break;
		}
		case 2: {
			if (checkNamNhuan(year)) {
				daymax = 29;

			} else {
				daymax = 28;
			}
			break;
		}
		}
		if (day <= daymax) {
			check = true;
		}
		return check;
	}

	/**
	 * hàm check kiểm tra kí tự 1 byte
	 * 
	 * @param text chuỗi dữ liệu input
	 * @return true: chuối dữ liệu chỉ chứa kí tự 1 byte false: chuỗi dữ liệu không
	 *         chỉ chứa kí tự 1 byte
	 */
	public static boolean checkOneByte(String text) {
		boolean flag = true;
		if (Charset.forName("US-ASCII").newEncoder().canEncode(text)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	/**
	 * 
	 * hàm check ngày hết hạn có lớn hơn ngày bắt đầy hay không Er012
	 * 
	 * @param startDate chuỗi dữ liệu ngày bắt đầu
	 * @param endDate   chuỗi dữ liệu ngày hết hạn
	 * @return true: ngày hết hạn lớn hơn ngày bắt đầu false: ngày hết hạn không lớn
	 *         hơn ngày bắt đầu
	 */
	public static boolean checkEndStartDay(String startDate, String endDate) {
		// chuỗi format ngày
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// ép kiểu ngày cho chuỗi dữ liệu ngày bắt đầu
		LocalDate start = LocalDate.parse(startDate, dateTimeFormatter);
		// ép kiểu ngày cho chuỗi dữ liệu ngày hết hạn
		LocalDate end = LocalDate.parse(endDate, dateTimeFormatter);
		// kiểm tra xem ngày hết hạn có lớn hơn ngày bắt đầu hay không
		// isAfter kiểm tra xem có nằm sau năm chỉ định hay không
		if (!end.isAfter(start)) {
			return true;
		}
		return false;
	}

	/**
	 * hàm lấy thông tin của một ngày từ 1 chuỗi thành ngày tháng năm trong mảng
	 * ArrayList
	 * @param date chuỗi đầu vào format yyyy/mm/dd
	 * @return ArrayList danh sách ngày tháng năm
	 */
	public static List<Integer> splitStringToList(String date) {
		List<Integer> result = new ArrayList<>();
		// tách chuỗi thành những chuỗi con
		String[] lsDate = date.split("/");
		int index = 0;
		int year = Integer.parseInt(lsDate[index++]);
		result.add(year);
		int month = Integer.parseInt(lsDate[index++]);
		result.add(month);
		int day = Integer.parseInt(lsDate[index++]);
		result.add(day);
		return result;
	}

	/**
	 * convert các số năm. tháng, ngày thành 1 chuỗi có format yyyy/mm/dd
	 * 
	 * @param year  năm
	 * @param month tháng
	 * @param day   ngày
	 * @return chuỗi ngày tháng
	 */
	public static String converDaytoString(String year, String month, String day) {
		// nếu chuỗi tháng có độ dài nhỏ hơn 2 thì thêm số 0 đằng trước chuỗi
		month = month.length() < 2 ? "0" + month : month;
		// nếu chuỗi ngày có độ dài nhỏ hơn 2 thì thêm số 0 đằng trước chuỗi
		day = day.length() < 2 ? "0" + day : day;
		return year + "/" + month + "/" + day;
	}

	/**
	 * hàm check kí tự katakana Er009
	 * 
	 * @param text chuỗi dữ liệu đầu vào
	 * @return true: chuỗi dữ liệu đầu vào không phải là chuỗi kí tự katakana false:
	 *         chuỗi dữ liệu đầu vào là chuỗi kí tự katakana
	 */
	public static boolean checkFormatKana(String text) {
		// chuyển chuỗi dữ liệu đầu vào thành mảng các phần tử char
		char[] c = text.toCharArray();
		// duyệt tứng phần tử
		for (int i = 0; i < c.length; i++) {
			// kiểm tra xem có phải là kí tự katakaba hay không
			if ((Character.UnicodeBlock.of(c[i]) != Character.UnicodeBlock.KATAKANA)
					&& (!Character.isWhitespace(c[i]))) {

				return true;
			}
		}
		return false;
	}

	/**
	 * Hàm tạo salt
	 * @return chuỗi salt
	 */
	public static String getSalt() {
		return "" + System.currentTimeMillis();
	}

	/**
	 * Chuyển đổi dữ liệu từ kiểu String sang int
	 * @param id  : tham số truyền vào
	 * @param defau : giá trị default
	 * @return : trả về dữ liệu kiểu số
	 */
	public static int convent(String id, int defau) {
		int i = 0;
		try {
			i = Integer.parseInt(id);
		} catch (Exception e) {
			i = defau;
		}
		return i;
	}

	/**
	 * thêm vào dánh sách lỗi
	 * 
	 * @param a tham số truyền vào
	 * @param list : list cần add vào
	 */
	public static void addListErr(String a, List<String> list) {
		if (a.length() != 0) {
			list.add(a);
		}

	}

	/**
	 * Set giá trị lên trên các ô selectbox
	 * 
	 * @param response                : 
	 * @throws ServletException       : 
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public static void setDataLogic(HttpServletRequest request)
			throws ServletException, SQLException, NullPointerException, ClassNotFoundException {
		MstGroupLogic groupLogic = new MstGroupLogicImpl();
		MstJapanLogic japanLogic = new MstJapanLogicImpl();
		// Khoi tao danh sach ngay thang nam
		List<Integer> lsYear = new ArrayList<Integer>();
		List<Integer> lsMonth = new ArrayList<Integer>();
		List<Integer> lsDay = new ArrayList<Integer>();
		List<Integer> lsEndYear = new ArrayList<>();

		List<MstGroupEntity> groupEntities = new ArrayList<MstGroupEntity>();
		List<MstJapanEntity> japanEntities = new ArrayList<MstJapanEntity>();

		try {
			lsYear = Common.getListYear(ConstantUtils.Year_Default, LocalDate.now().getYear());
			lsEndYear = Common.getListYear(ConstantUtils.Year_Default, LocalDate.now().getYear() + 1);
			lsMonth = Common.getListMonth();
			lsDay = Common.getListDay();
			groupEntities = groupLogic.gettAllMstGroup();
			japanEntities = japanLogic.gettAllMstJapan();

			// xét lên request để lấy các giá trị cho các mục selectBox
			request.setAttribute("lsYear", lsYear);
			request.setAttribute("lsEndYear", lsEndYear);
			request.setAttribute("lsMonth", lsMonth);
			request.setAttribute("lsDay", lsDay);
			request.setAttribute("groupEntities", groupEntities);
			request.setAttribute("japanEntities", japanEntities);

		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("AddUserInputController.getData " + e.getMessage());
			throw e;
		}

	}
	public static void main(String[] args) {
		if(!checkMaxLength("189", ConstantUtils.MAX_09)) {
			System.out.println("sai");
		} else {
			System.out.println("dung");
		}
	}
}
