/**
 * Coppyright(C) [2019] Luvina software company
 *MstGroupLogic.java [Aug 6, 2019]
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroupEntity;

/**
 * @author QuyetThang
 *
 */
public interface MstGroupLogic {
	/**
	 * Lấy danh sách nhóm
	 * 
	 * @return danh sách các nhóm
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract List<MstGroupEntity> gettAllMstGroup()
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * lấy tên group theo group id
	 * 
	 * @param groupId tham số truyền vào
	 * @return lấy tên Group
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract String getGroupNameByGroupId(int groupId)
			throws NullPointerException, SQLException, ClassNotFoundException;

}
