/**
 * Coppyright(C) [2019] Luvina software company
 *UserInfor.java [Aug 6, 2019]
 */
package manageuser.entities;

import java.util.List;

/**
 * @author QuyetThang
 *
 */
public class UserInfor {
	private int userId;
	// tên đầy đủ của user
	private String fullName;
	// email
	private String email;
	// số điện thoại
	private String tel;
	// ngày sinh
	private String birthday;
	// trình độ tiếng nhật
	private String nameLevel;
	// ngày kết thúc
	private String endDate;
	// điểm
	private String total;   
	// mã nhóm
	private int groupId;
	
	private String groupName;
	// tên đăng nhập
	private String loginName;
	// ngày bắt đầu
	private String startDate;
	// tên katakana
	private String fullNameKana;

	private String pass;

	private String confirmPass;
	
    private String codeLevel;
    
    // tạo thêm thuộc tính để check xem đối tượng có trình độ tiếng nhật hay không
    private boolean isExistCodelevel;
    
    private List<Integer> lsBirthday ;
    private List<Integer> lsStartDay;
    private List<Integer> lsEndDay;
    
    
	/**
	 * @return the lsBirthday
	 */
	public List<Integer> getLsBirthday() {
		return lsBirthday;
	}

	/**
	 * @param lsBirthday the lsBirthday to set
	 */
	public void setLsBirthday(List<Integer> lsBirthday) {
		this.lsBirthday = lsBirthday;
	}

	/**
	 * @return the lsStartDay
	 */
	public List<Integer> getLsStartDay() {
		return lsStartDay;
	}

	/**
	 * @param lsStartDay the lsStartDay to set
	 */
	public void setLsStartDay(List<Integer> lsStartDay) {
		this.lsStartDay = lsStartDay;
	}

	/**
	 * @return the lsEndDay
	 */
	public List<Integer> getLsEndDay() {
		return lsEndDay;
	}

	/**
	 * @param lsEndDay the lsEndDay to set
	 */
	public void setLsEndDay(List<Integer> lsEndDay) {
		this.lsEndDay = lsEndDay;
	}

	/**
	 * @param isExistCodelevel the isExistCodelevel to set
	 */
	public void setExistCodelevel(boolean isExistCodelevel) {
		this.isExistCodelevel = isExistCodelevel;
	}

	public boolean getIsExistCodelevel() {
		return isExistCodelevel;
	}

	public void setIsExistCodelevel(boolean isExistCodelevel) {
		this.isExistCodelevel = isExistCodelevel;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the fullNameKata
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKata the fullNameKata to set
	 */
	public void setFullNameKata(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the confirmPass
	 */
	public String getConfirmPass() {
		return confirmPass;
	}

	/**
	 * @param confirmPass the confirmPass to set
	 */
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
}
