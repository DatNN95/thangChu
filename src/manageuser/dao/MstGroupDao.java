/**
 *Coppyright [2019]Luvina company
 *MstGroupDao.java
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroupEntity;

/**
 * @author QuyetThang
 *
 */
public interface MstGroupDao {

	/**
	 * Lấy danh sách nhóm
	 * 
	 * @return danh sách các nhóm
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract List<MstGroupEntity> getAllMstGroup()
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * hàm lấy tên nhóm
	 * 
	 * @param groupId mã nhóm 
	 * @return tên nhóm
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 *
	 *                        
	 */
	public abstract String getGroupNameByGroupId(int groupId)
			throws NullPointerException, SQLException, ClassNotFoundException;
}
