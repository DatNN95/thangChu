/**
 * Coppyright 2019 Luvina company
 * MstJapanLogic.java 2019 
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;
import manageuser.entities.MstJapanEntity;

/**
 * @author QuyetThang
 *
 */
public interface MstJapanLogic {
	/**
	 * lấy danh sách trình độ tiếng nhật trong DB
	 * 
	 * @return trả danh sách trình độ tiếng nhật
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract List<MstJapanEntity> gettAllMstJapan()
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Lấy tên Level theo code level
	 * 
	 * @param codeLevel
	 * @return tên level 
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract String getNameLevelByCodeLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * Check kieem tra su ton tai namelevel
	 * 
	 * @param nameLevel tham số truyền vào
	 * @return true khi tồn tại, false khi không tồn tại
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract boolean checkExistNameLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException;
}
