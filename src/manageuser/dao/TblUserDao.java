/**
 *Coppyright [2019]Luvina company
 *TblUserDao.java
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfor;

/**
 * @author QuyetThang
 *
 */
public interface TblUserDao extends BaseDao {
	/**
	 * Hàm lấy TblUser trong Dao ra
	 * 
	 * @param loginName tham số loginName của user
	 * @param Rule      : điều kiện cần lấy quyền là gì
	 * @return trả về đối tượng TblUser
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract TblUserEntity getTblUserByLoginName(String loginName)
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * hàm lấy tổng số user theo group
	 * 
	 * @param groupId  mã phòng
	 * @param fullName tên user
	 * @return trả về tổng số user
	 * @throws NullPointerException   : Lỗi khiConncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract int getTotalUsers(int groupId, String fullName)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * hàm kết nối cơ sở dữ liệu để lấy danh sách thông tin user
	 * 
	 * @param offset
	 * @param limit           giới hạn
	 * @param groupId         mã group_id của user
	 * @param fullName        full name của user
	 * @param sortType        cột sắp xếp được ưu tiên
	 * @param sortByFullName  cột sắp xếp của trường fullName
	 * @param sortByCodeLevel cột sắp xếp của trường code_level
	 * @param sortByEndDate   cột sắp xếp của trường ngày tháng
	 * @return danh sách thông tin của user
	 */
	public abstract List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Lấy đối tượng user theo email
	 * 
	 * @param email : tham số truyền vào 
	 * @return : trả về đối tượng tblUser
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public TblUserEntity getUserByMail(String email, int id)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Thêm đối tượng trong tblUser
	 * 
	 * @param tblUserEntity đối tượng tbl user
	 * @return trả về giá trị
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract Integer insertUser(TblUserEntity tblUserEntity)
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Lấy về đối tượng userinfor theo id dùng cho ADM005
	 * 
	 * @param id : id của đối tượng
	 * @return : trẻ về đối tượng
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract UserInfor getUserInforByID(int id)
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Lấy đối tượng tblUserId
	 * 
	 * @param id : id của tbluser
	 * @return true khi tồn tại , false khi không tồn tại
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract boolean checkTblUserByID(int id) throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Update đối tượng tblUser
	 * 
	 * @param tblUserEntity : tham số truyền vào 
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract void updateTblUser(TblUserEntity tblUserEntity) throws SQLException;

	/**
	 * Kiểm tra quyền xóa xem có phải là Admin không
	 * 
	 * @param userId
	 * @return true : là admin , false : user
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract boolean checkRuleAdmin(int userId)
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Xóa đối tượng tblUser
	 * 
	 * @param userId tham số id cần truyền vào
	 * @return true khi xóa thành công
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract boolean deleteTblUser(int userId) throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * Lấy các cột trong một bảng
	 * 
	 * @param tableName
	 * @return  Danh sach các cột có trong Db
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract List<String> listCol() throws NullPointerException, SQLException, ClassNotFoundException;

}
