/**
 * Coppyright(C) [2019] Luvina software company
 *BaseDaoImpl.java [Aug 4, 2019]
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import manageuser.dao.BaseDao;
import manageuser.utils.DatabaseProperties;

/**
 * @author QuyetThang Thao tác với DB
 */
public class BaseDaoImpl implements BaseDao {
	protected Connection conn = null;
	protected ResultSet rs = null;
	protected PreparedStatement pre = null;

	/* (non-Javadoc)
	* @see manageuser.dao.BaseDao#setConn(java.sql.Connection)
	*
	*/
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#getConnection()
	 *
	 */
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException , NullPointerException {
		conn = null;
		try {
			Class.forName(DatabaseProperties.getData("CLASS"));
			conn = DriverManager.getConnection(DatabaseProperties.getData("URL"), DatabaseProperties.getData("USER"),
					DatabaseProperties.getData("PASS"));
		} catch (ClassNotFoundException | SQLException | NullPointerException e) {
			System.out.println("BaseDaoImpl.getConntion " + e.getMessage());

		}
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#closeConnect(java.sql.Connection)
	 *
	 */
	@Override
	public void closeConnect()  {
		try {
			if (conn != null && !conn.isClosed()) {			
				conn.close();
				conn = null;
			}
			
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.closeConnect");
		}
	}

	/* (non-Javadoc)
	* @see manageuser.dao.BaseDao#commit()
	*
	*/
	@Override
	public void commit() throws SQLException {
		try {
			// kiểm tra xem đã kết nối với DB hay chưa
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			// in ra phương thức bị bắt ngoại lệ và thông báo lỗi
			System.out.println("BaseDaoImpl.commit : " + e.getMessage());
			throw e;
		}
		
	}
	/* (non-Javadoc)
	* @see manageuser.dao.BaseDao#rollBack()
	*
	*/
	@Override
	public void rollBack() throws SQLException {
		try {
			// kiểm tra xem đã kết nối với DB hay chưa
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// in ra phương thức bị bắt ngoại lệ và thông báo lõi
			System.out.println("BaseDaoImpl.rollBack : " + e.getMessage());
			throw e;
		}
		
	}
	/* (non-Javadoc)
	* @see manageuser.dao.BaseDao#disableAutoCommit()
	*
	*/
	@Override
	public void disableAutoCommit() throws SQLException {
		try {
			// kiểm tra xem đã kết nối với DB hay chưa
			if (conn != null) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// in ra phương thức bị bắt ngoại lệ và thông báo lỗi
			System.out.println("BaseDaoImpl.disableAutoCommit : " + e.getMessage());
			throw e;
		}
		
	}

}
