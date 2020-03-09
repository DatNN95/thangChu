/**
 *Coppyright [2019]Luvina company
 *TblDetailUserJapanDao.java
 */
package manageuser.dao;

import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapanEntity;

/**
 * @author QuyetThang
 *
 */
public interface TblDetailUserJapanDao extends BaseDao {
	/**
	 * theem doi tuong vao Detail user japan vào Db
	 * 
	 * @param tblDetailUserJapan
	 * @return : true khi thành công, false không thành công
	 * @throws SQLException : lỗi khi sử dụng câu sql
	 */
	public abstract boolean insertTblDetailUser(TblDetailUserJapanEntity tblDetailUserJapan) throws SQLException;

	/**
	 * Update đối tượng tblDetailUserJapan
	 * 
	 * @param tblDetailUserJapan đôi tượng tblDetailUserJapan
	 * @throws SQLException lỗi do câu lệnh SQL
	 */
	public abstract boolean updateTblDetailUser(TblDetailUserJapanEntity tblDetailUserJapan) throws SQLException;

	/**
	 * xóa đối tượng TblDetailUserJapan
	 * 
	 * @param id : tham số truyền vào là userid
	* @throws SQLException lỗi do câu lệnh SQL
	 */
	public abstract boolean deleteTblDetaiUserJapan(int id) throws SQLException;

	/**
	 * Kiểm tra sự tồn tại của của tbldetailUserJapan theo userId
	 * 
	 * @param userId tham số truyền vào của userId
	 * @return true khi tồn tại , false khi không tồn tại
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract boolean checkExistCodeLevel(int userId)
			throws NullPointerException, ClassNotFoundException, SQLException;
}
