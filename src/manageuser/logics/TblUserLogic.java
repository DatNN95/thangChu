/**
 * Coppyright(C) [2019] Luvina software company
 *TblUserLogic.java [Aug 3, 2019]
 */
package manageuser.logics;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfor;

/**
 * @author QuyetThang
 *
 */
public interface TblUserLogic {
	/**
	 * Kiểm tra sự tồn tại TblUser khi login
	 * 
	 * @param loginName : tên nhập vào
	 * @param pass      : mật khẩu được truyền vào
	 * @return : true khi tồn tại, false : khi không tồn tại
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 * @throws NoSuchAlgorithmException : bắt lỗi khi SHA1 cho password
	 */
	public abstract Boolean checkUser(String loginName, String pass)
			throws NullPointerException, SQLException, ClassNotFoundException, NoSuchAlgorithmException;

	/**
	 * Tính tổng số user 
	 * @param groupId 
	 * @param fullName
	 * @return
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract int getTotalUsers(int groupId, String fullName)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Lấy danh sách UserInfor
	 * 
	 * @param offset          :vị trí cần lấy ds
	 * @param limit           :số lượng record trên một page
	 * @param groupId         : id của group
	 * @param fullName        : tên đầy đủ
	 * @param sortType        : trường sort được ưu tiên
	 * @param sortByFullName  : sắp xếp full name
	 * @param sortByCodeLevel : sắp xếp code level
	 * @param sortByEndDate   : sắp xếp ngày hết hạn
	 * @return : trả về danh sách user
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 * @throws NoSuchAlgorithmException : bắt lỗi khi SHA1 cho password
	 */
	public abstract List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Lấy đối tượng TblUserEntity theo loginName
	 * 
	 * @param loginName tham số truyền vào đề lấy tblUser
	 * @return trả về đối tượng tblUser
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract TblUserEntity getTblUserByLoginName(String loginName)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Tạo đối tượng mới thêm vào
	 * 
	 * @param userInfor tham số truyền vào 
	 * @return true khi thành công, false khi lỗi
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract Boolean createUser(UserInfor userInfor)
			throws NullPointerException, SQLException, ClassNotFoundException, NoSuchAlgorithmException;

	/**
	 * Lấy đối tượng userinfor theo id
	 * 
	 * @param id: id của userInfor
	 * @return trả về đối tượng userInfor
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract UserInfor getUserInforById(int id)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra xem email đã tồn tại hay chưa
	 * 
	 * @param email tham số truyền vào
	 * @return trả vè true nếu tồn tại , false chưa tồn tại
 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract boolean checkTblUserByEmail(String email, int id)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Lấy đối tượng TblUserEntity theo id
	 * 
	 * @param id: tham số truyền vào
	 * @return : true khi tồn tại , false khi không tồn tại
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract boolean checkExistTblUserByID(int id)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * update đối tượng User
	 * 
	 * @param userInfor : đối tượng userInfor
	 * @return ; true khi update thành công , false khi không thành công
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract boolean updateUser(UserInfor userInfor)
			throws NullPointerException, SQLException, ClassNotFoundException , NoSuchAlgorithmException;

	/**
	 * Gắn giá trị cho đối đượng TblUser theo UserInfor
	 * 
	 * @param userInfor tham số truyền vào 
	 * @return tblUser trả về đối tượng 
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract TblUserEntity getTblUserByUserInfor(UserInfor userInfor) throws NoSuchAlgorithmException;

	/**
	 * Gắn giá trị cho đối đượng TblDetail theo UserInfor 
	 * @param userInfor tham số truyền vào
	 * @return đối tượng tblDetail
	 */
	public abstract TblDetailUserJapanEntity getTblDetailUserByUserInfor(UserInfor userInfor);

	/**
	 * Kiểm tra sự tồn tại User theo loginName
	 * 
	 * @param loginName thám số truyền vào
	 * @return true khi tồn tại , false khi không tồn tại
	 */
	public abstract boolean checkExistLoginName(String loginName)
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Xóa đối tượng UserInfor
	 * 
	 * @param userId
	 * @return true nếu xóa thành công , false : không thành công
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract boolean deleteUser(int userId) throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Kiểm tra xem có đúng là admin hay không
	 * 
	 * @param userId tham số truyền vào
	 * @return true khi đúng , false khi không có
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	public abstract boolean checkRuleAdmin(int userId)
			throws ClassNotFoundException, NullPointerException, SQLException;

}
