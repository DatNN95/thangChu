/**
 * Coppyright 2019 Luvina company
 * TblDetailUserJapanLogic.java 2019 
 */
package manageuser.logics;

import java.sql.SQLException;

/**
 * @author QuyetThang
 *
 */
public interface TblDetailUserJapanLogic  {
	/**
	 * Kiểm tra có tồi tại trình độ tiếng nhật hay không
	 * @param userId tham số userId truyền vào
	 * @return true là tồn tại, false không tồn tại
	 * @throws NullPointerException     : Lỗi khi Conncetion bị null
	 * @throws SQLException             : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException   : lỗi khi kết nối DB
	 */
	 public abstract boolean checkExistCodeLevel( int userId) throws ClassNotFoundException , NullPointerException , SQLException;
}
