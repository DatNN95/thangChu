/**
 * Coppyright(C) [2019] Luvina software company
 *TblDetailUserJapanImpl.java [Aug 22, 2019]
 */
package manageuser.dao.impl;

import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapanEntity;

/**
 * @author QuyetThang
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#insertTblDetailUser(manageuser.entities.
	 * TblDetailUserJapanEntity)
	 *
	 */
	@Override
	public boolean insertTblDetailUser(TblDetailUserJapanEntity tblDetailUserJapan) throws SQLException {
		int index = 1;
		boolean check = false;
		StringBuilder selectQuery = new StringBuilder("insert into tbl_detail_user_japan ");
		selectQuery.append("( user_id,code_level, start_date, end_date, total) ");
		selectQuery.append(" values(?,?,?,?,?) ");
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString());

			pre.setInt(index++, tblDetailUserJapan.getUserId());
			pre.setString(index++, tblDetailUserJapan.getCodeLevel());
			pre.setString(index++, tblDetailUserJapan.getStartDate());
			pre.setString(index++, tblDetailUserJapan.getEndDate());
			pre.setInt(index++, tblDetailUserJapan.getTotal());
			// them du lieu
			if (pre.executeUpdate() > 0) {
				check = true;
			}
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapanDaoImpl.insertTblDetailUser: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#updateTblDetailUser(manageuser.entities.
	 * TblDetailUserJapanEntity)
	 */
	@Override
	public boolean updateTblDetailUser(TblDetailUserJapanEntity tblDetailUserJapan) throws SQLException {
		int index = 1;
		boolean check = false;
		StringBuilder selectQuery = new StringBuilder("update tbl_detail_user_japan ");
		selectQuery.append(" set code_level = ? , ");
		selectQuery.append(" start_date = ? , ");
		selectQuery.append(" end_date = ? , ");
		selectQuery.append(" total = ? ");
		selectQuery.append("where user_id = ?");
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setString(index++, tblDetailUserJapan.getCodeLevel());
			pre.setString(index++, tblDetailUserJapan.getStartDate());
			pre.setString(index++, tblDetailUserJapan.getEndDate());
			pre.setInt(index++, tblDetailUserJapan.getTotal());
			pre.setInt(index++, tblDetailUserJapan.getUserId());
			// them du lieu
			if (pre.executeUpdate() > 0) {
				check = true;
			}

		} catch (SQLException e) {
			System.out.println("TblDetailUserJapanDaoImpl.updateTblDetailUser: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#deleteTblDetaiUer(manageuser.entities.
	 * TblDetailUserJapanEntity)
	 */
	@Override
	public boolean deleteTblDetaiUserJapan(int id) throws SQLException {
		int index = 1;
		boolean check = false;
		StringBuilder selectQuery = new StringBuilder("delete from tbl_detail_user_japan ");
		selectQuery.append(" where user_id = ?");
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setInt(index++, id);
			// thực thi câu lệnh
			if (pre.executeUpdate() > 0) {
				check = true;
			}
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapanDaoImpl.deleteTblDetailUser: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#checkExistTblDetailUserJapanByUserId(
	 * int)
	 *
	 */
	@Override
	public boolean checkExistCodeLevel(int userId)
			throws NullPointerException, ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT user_id, code_level  ");
			sql.append(" from tbl_detail_user_japan ");
			sql.append(" Where user_id = ?");
		   // mở connection
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			int index = 1;
			pre.setInt(index++, userId);
			rs = pre.executeQuery();
			if (rs.next()) {
				check = true;
			} else {
				check = false;
			}
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			System.out.println("TblDetailUserJapanDao.checkExistTblDetail" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return check;
	}
}
