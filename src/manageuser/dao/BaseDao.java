/**
 *Coppyright [2019]Luvina company
 *BaseDao.java
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author QuyetThang
 *
 */
public interface BaseDao {
/**
 * Mowr chuỗi kết nối với Db
 * @return
	 * @throws NullPointerException   : Lỗi khi Conncetion bị null
	 * @throws SQLException           : lỗi khi sử dụng câu sql
	 * @throws ClassNotFoundException : lỗi khi kết nối DB
 */
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException, NullPointerException  ;
/**
 * Đóng chuỗi Connection , ResultSet , PreparedSatement
 * @param rs tham số ResultSet cần đóng
 * @param ps Tham số PreparedStatement cần đóng
 * @throws ClassNotFoundException trường hợp không tìm thấy class 
 * @throws SQLException  
 */
	public abstract void closeConnect() throws ClassNotFoundException, SQLException;
	
	/**
	 * phương thức dùng để hủy commit tự động
	 * @throws SQLException
	 */
	public abstract void disableAutoCommit() throws SQLException;
	
	/**
	 * phương thức thực hiện lệnh connection.commit() 
	 * @throws SQLException 
	 */
	public abstract void commit() throws SQLException;
	
	/**
	 * phương thức thực hiện rollBack data khi có lỗi thực thi câu lệnh sql
	 * @throws SQLException
	 */
	public abstract void rollBack() throws SQLException;
/**
 * cài đặt connect 
 * @param conn tham số truyền vào
 */
	public abstract void setConn(Connection conn);
}
