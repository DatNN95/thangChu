/**
 * Coppyright(C) [2019] Luvina software company
 *ConstantUtils.java [Aug 6, 2019]
 */
package manageuser.utils;
/**
 * 
 * @author QuyetThang
 * Các hằng số trong chương trình
 */
public class ConstantUtils {
	public static final String LINKADM001 = "/JSP/ADM001.jsp";
	public static final String ERR = "/JSP/ERR.jsp";
	public static final String ADM002 =  "/JSP/ADM002.jsp";
	public static final String ADM003 =  "/JSP/ADM003.jsp";
	public static final String ADM004 =  "/JSP/ADM004.jsp";
	public static final String ADM005 =  "/JSP/ADM005.jsp";
	public static final String ADM006 =  "/JSP/ADM006.jsp";
	public static final String LINK_LOGIN =  "/Login.do";
	public static final String URL_LISTUSER_DO =  "/ListUser.do";
	public static final String SYSTEM_ERR =  "SystemErr";
	public static final String SUCCESSLINK = "Success.do";
	
    public static final String ADDUSER_CONFIM = "AddUserConfim.do";
	public static final String MSG001 = "MSG001";
	public static final String MSG002 = "MSG002";
	public static final String MSG003 = "MSG003";
	public static final String MSG005 = "MSG005";
	public static final String ERR015 = "Error_15";
	public static final String ERR013 = "Error_13";
	public static final String ERR014 = "Error_14";
	public static final String ERR020 = "Error_20";
	// giá trị rule của admin
	public static final int RULE_ADMIN = 0;
	// giá trị rule của user
	public static final int RULE_USER = 1;
	public static final int GROUP_ID_DEFAULT = 0;
	public static final String FULL_NAME_DEFAULT = "";
	public static final String SORT_TYPE_DEFAULT = "";
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String ACTION_SEARCH = "search";
	public static final String ACTION_SORT = "sort";
	public static final String ACTION_BACK = "back";
	public static final String PAGING ="paging";
	// message MSG005
	public static final String MESS_MSG = "/message.properties";
	// message err
	public static final String MESS_ERR_MSG = "/message_err.properties";
	// 
	public static final int CURRENT_PAGE = 1;
	public static final int OFFSET_DEFAULT = 0;
	public static final int DEFAULT_GROUP_ID = 0;
	public static final String DEFAULT_CODE_LEVEL = "";
	
	public static final String  Add_ERR = "add";
	public static final String  Edit_ERR = "edit";
	public static final int Year_Default = 1990;
	public static final String GROUP_DEFAULT = "選択してください";
	// key để đánh dấu từ ADM003 sang ADM004
	public static final String KEY_ADM003 = "keyADM003";
	
	
	public static final int MAX_LENGTH_255 = 255;
	public static final int MIN_NAME = 4;
	public static final int MAX_NAME = 15;
	public static final String LOGIN_NAME = "loginName";
	public static final String FULL_NAME = "fullName";
	public static final String GROUP_ID = "groupId";
	public static final String ACTION = "action";
	public static final int MAX_100 = 100;
	public static final int MIN_1 = 1;
	public static final int MAX_14 = 14;
	public static final int Min_5 = 5;
	public static final int MAX_15 = 15;
	public static final int MAX_09 = 11;	
	public static final String FORMAT_EMAIL= ".+@{1}.+[.]{1}.*";
	public static final String FORMAT_HAFT_SIZE= "[0-9]+";
	public static final String FORMAT_PHONE_MUNBER= "[0-9]{1,4}+-+[0-9]{1,4}+-[0-9]{1,4}+$";
	public static final String FORMAT_LOGIN_NAME = "^[a-zA-Z_][0-9a-zA-Z_]*";
	

	public static final String STRING_CONFRIM ="CONFIRM";

	public static final int DEFAULT_ID = 0;
	public static final String KEY_SESSION ="keySession";

	
}
