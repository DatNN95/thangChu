/**
 *Coppyright [2019]Luvina company
 *MstJapanDao.java
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapanEntity;

/**
 * @author QuyetThang Sử dụng khi thao tác với DB
 */
public interface MstJapanDao {
	/**
	 * Lấy danh sách trình độ tiếng nhật
	 * 
	 * @return danh sách trình độ
	 * @throws ClassNotFoundException bắt trường hợp khi kết nối DB
	 * @throws NullPointerException
	 * @throws SQLException           lỗi thực hiện câu SQL
	 */
	public abstract List<MstJapanEntity> getAllMstJapan()
			throws ClassNotFoundException, NullPointerException, SQLException;

	/**
	 * hàm lấy trình độ tiếng nhật
	 * 
	 * @param codeLevel mã trình độ tiếng nhật
	 * @return trình độ tiếng nhật
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 *
	 *
	 */
	public abstract String getNameLevelByCodeLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException;

	/**
	 * lấy đối tượng mstjapan
	 * 
	 * @param codelave : mã trình độ
	 * @return : đối tượng mstJapan
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
	 */
	public abstract MstJapanEntity getJapanByCodeLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException;
}
