/**
 * Coppyright(C) [2019] Luvina software company
 *TblUserDaoImpl.java [Aug 4, 2019]
 */
package manageuser.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfor;
import manageuser.utils.ConstantUtils;

/**
 * @author QuyetThang Xử lý các thao tác lấy trong DB
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getUser(java.lang.String)
	 *
	 */
	@Override
	public TblUserEntity getTblUserByLoginName(String loginName)
			throws ClassNotFoundException, NullPointerException, SQLException {
		TblUserEntity tblUserEntity = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT login_name, password , salt , Rule FROM tbl_user ");
			sql.append(" WHERE login_name= ?");
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			int index = 1;
			pre.setString(index++, loginName);
			rs = pre.executeQuery();
			if (rs.next()) {
				tblUserEntity = new TblUserEntity();
				tblUserEntity.setLoginName(rs.getString("login_name"));
				tblUserEntity.setSalt(rs.getString("salt"));
				tblUserEntity.setPass(rs.getString("password"));
				tblUserEntity.setRule(rs.getInt("Rule"));
			}
		} catch (NullPointerException | SQLException |ClassNotFoundException e) {
			System.out.println("TblUserDaoImpl.getUser: " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return tblUserEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getTotalUsers(int, java.lang.String)
	 *
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName)
			throws ClassNotFoundException, NullPointerException, SQLException {
		StringBuilder sql = new StringBuilder("");
		int total = 0;
		try {
			getConnection();
			sql.append("SELECT count(u.user_id) as total ");
			sql.append("from tbl_user u ");
			sql.append("inner join mst_group g ");
			sql.append("on u.group_id = g.group_id ");
			sql.append("left join tbl_detail_user_japan detail ");
			sql.append("on detail.user_id = u.user_id ");
			sql.append("left join mst_japan jp ");
			sql.append("on jp.code_level = detail.code_level ");
			sql.append("where u.rule = ? ");
			if (groupId > 0) {
				sql.append(" and u.group_id = ? ");
			}
			if (!fullName.isEmpty()) {
				sql.append(" and u.full_name like ? ");
			}
			pre = conn.prepareStatement(sql.toString());
			int index = 1;
			pre.setInt(index++, ConstantUtils.RULE_USER);
			if (groupId > 0) {
				pre.setInt(index++, groupId);
			}
			if (!fullName.isEmpty()) {
				pre.setString(index++, "%" + fullName + "%");
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
        
		} catch (ClassNotFoundException| NullPointerException | SQLException e) {
			System.out.println("TblUserDaoImpl.getTotalUsers: " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getListUsers(int, int, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws NullPointerException, SQLException, ClassNotFoundException {
		List<UserInfor> listUsers = new ArrayList<UserInfor>();
		// Lấy danh sách các cột trong bảng tbl_user

		StringBuilder sql = new StringBuilder();
		// tạo danh sách chứa các cột cần order by
		List<String> lsCol = new ArrayList<String>();
		lsCol = listCol();
		try {
			getConnection();
			if (conn != null) {
				sql.append("SELECT u.user_id, u.full_name, u.birthday, g.group_name, u.email, ");
				sql.append("u.tel, jp.name_level, detail.end_date, detail.total ");
				sql.append("from tbl_user u ");
				sql.append("inner join mst_group g ");
				sql.append("on u.group_id = g.group_id ");
				sql.append("left join tbl_detail_user_japan detail ");
				sql.append("on detail.user_id = u.user_id ");
				sql.append("left join mst_japan jp ");
				sql.append("on jp.code_level = detail.code_level ");
				sql.append("where u.rule = ? ");
				if (groupId > 0) {
					sql.append("and u.group_id = ? ");
				}
				if (!fullName.isEmpty()) {
					sql.append("and u.full_name like ? ");
				}
				if (lsCol.contains(sortType)) {
					if ("full_name".equals(sortType)) {
						sql.append("order by u.full_name " + sortByFullName);
						sql.append(", jp.code_level " + sortByCodeLevel);
						sql.append(", detail.end_date " + sortByEndDate);

					} else if ("code_level".equals(sortType)) {
						sql.append("order by jp.code_level " + sortByCodeLevel);
						sql.append(", u.full_name " + sortByFullName);
						sql.append(", detail.end_date " + sortByEndDate);

					} else if ("end_date".equals(sortType)) {
						sql.append("order by detail.end_date " + sortByEndDate);
						sql.append(", u.full_name " + sortByFullName);
						sql.append(", jp.code_level " + sortByCodeLevel);
					}

				} else {
					sql.append("order by u.full_name " + ConstantUtils.ASC);
					sql.append(", jp.code_level " + ConstantUtils.ASC);
					sql.append(", detail.end_date " + ConstantUtils.DESC);
				}
				sql.append(" limit ?,?");
				pre = conn.prepareStatement(sql.toString());
				int index = 1;
				pre.setInt(index++, ConstantUtils.RULE_USER);
				if (groupId > 0) {
					pre.setInt(index++, groupId);
				}
				if (!fullName.isEmpty()) {
					pre.setString(index++, "%" + fullName + "%");
				}
				pre.setInt(index++, offset);
				pre.setInt(index++, limit);
				rs = pre.executeQuery();
				while (rs.next()) {
					UserInfor infor = new UserInfor();
					infor.setUserId(rs.getInt("user_id"));
					infor.setFullName(rs.getString("full_name"));
					infor.setGroupName(rs.getString("group_name"));
					infor.setBirthday(rs.getString("birthday"));
					infor.setEmail(rs.getString("email"));
					infor.setTel(rs.getString("tel"));
					infor.setNameLevel(rs.getString("name_level"));
					infor.setEndDate(rs.getString("end_date"));
					infor.setTotal(rs.getString("total"));
					listUsers.add(infor);
				}
			}
		} catch (ClassNotFoundException| NullPointerException | SQLException e) {
			System.out.println("TblUserDaoImpl.getListUsers: " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}

		return listUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getAllMail()
	 *
	 */
	@Override
	public TblUserEntity getUserByMail(String email, int id)
			throws NullPointerException, SQLException, ClassNotFoundException {
		TblUserEntity tblUserEntity = null;

		StringBuilder selectQuery = new StringBuilder("SELECT full_name, user_id , email ");
		selectQuery.append(" FROM `tbl_user` ");
		selectQuery.append("Where email = ? ");
		if (id != 0) {
			selectQuery.append("and user_id != ? ");
		}
		int index = 1;
		try {
			getConnection();
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setString(index++, email);
			if (id != 0) {
				pre.setInt(index++, id);
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				tblUserEntity = new TblUserEntity();
				tblUserEntity.setEmail(rs.getString("email"));
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserDao.getAllmail " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return tblUserEntity;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#insertUser(manageuser.entities.TblUserEntity)
	 */
	@Override
	public Integer insertUser(TblUserEntity tblUserEntity) throws SQLException {
		int userId = 0;
		int index = 1;
		StringBuilder selectQuery = new StringBuilder("insert into tbl_user ");
		selectQuery.append("(group_id,login_name, password, full_name, full_name_kana, ");
		selectQuery.append("email, tel, birthday, rule, salt) ");
		selectQuery.append(" values(?,?,?,?,?,?,?,?,?,?) ");
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString(), Statement.RETURN_GENERATED_KEYS);
			pre.setInt(index++, tblUserEntity.getGroupId());
			pre.setString(index++, tblUserEntity.getLoginName());
			pre.setString(index++, tblUserEntity.getPass());
			pre.setString(index++, tblUserEntity.getFullName());
			pre.setString(index++, tblUserEntity.getFullNameKana());
			pre.setString(index++, tblUserEntity.getEmail());
			pre.setString(index++, tblUserEntity.getTel());
			pre.setString(index++, tblUserEntity.getBirthday());
			pre.setInt(index++, tblUserEntity.getRule());
			pre.setString(index++, tblUserEntity.getSalt());
			// thực thi câu lệnh
			pre.executeUpdate();

			rs = pre.getGeneratedKeys();
			if (rs.next()) {
				int indexRs = 1;
				userId = rs.getInt(indexRs);
			}
		} catch (SQLException e) {
			System.out.println("TblUserDaoImpl.insertTblUser: " + e.getMessage());
			throw e;
		}
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getUserInforByID(int)
	 *
	 */
	@Override
	public UserInfor getUserInforByID(int id) throws ClassNotFoundException, NullPointerException, SQLException {
		// khởi tạo đối tượng userInfor
		UserInfor userInfor = null;
		try {
			// mở kết nối
			getConnection();
			//
			StringBuilder selectQuery = new StringBuilder();
			int index = 1;
			// thêm chuỗi lệnh vào câu lệnh truy vấn
			selectQuery.append("SELECT u.login_name, u.group_id, u.full_name, u.full_name_kana, u.birthday, u.email, ");
			selectQuery.append("u.tel, detail.code_level, detail.start_date, detail.end_date, ");
			selectQuery.append("detail.total, g.group_name, jp.name_level ");
			selectQuery.append("from tbl_detail_user_japan detail ");
			selectQuery.append("right join tbl_user u ");
			selectQuery.append("on detail.user_id = u.user_id ");
			selectQuery.append("inner join mst_group g ");
			selectQuery.append("on u.group_id = g.group_id ");
			selectQuery.append("left join mst_japan jp ");
			selectQuery.append("on jp.code_level = detail.code_level ");
			selectQuery.append("where u.rule = ? ");
			selectQuery.append("and u.user_id = ? ");

			pre = conn.prepareStatement(selectQuery.toString());
			pre.setInt(index++, ConstantUtils.RULE_USER);
			pre.setInt(index++, id);
			rs = pre.executeQuery();
			while (rs.next()) {
				userInfor = new UserInfor();
				userInfor.setUserId(id);
				userInfor.setLoginName(rs.getString("login_name"));
				userInfor.setGroupId(rs.getInt("group_id"));
				userInfor.setFullName(rs.getString("full_name"));
				userInfor.setFullNameKata(rs.getString("full_name_kana"));
				userInfor.setBirthday(rs.getString("birthday"));
				userInfor.setEmail(rs.getString("email"));
				userInfor.setTel(rs.getString("tel"));
				userInfor.setCodeLevel(rs.getString("code_level"));
				userInfor.setStartDate(rs.getString("start_date"));
				userInfor.setEndDate(rs.getString("end_date"));
				userInfor.setTotal(rs.getString("total"));
				userInfor.setGroupName(rs.getString("group_name"));
				userInfor.setNameLevel(rs.getString("name_level"));
			}

		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("TblUserDao.getUserInfor" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return userInfor;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getTblUserByID(int)
	 */
	@Override
	public boolean checkTblUserByID(int id) throws ClassNotFoundException, NullPointerException, SQLException {
		boolean check = false;
		int index = 1;
		try {
			getConnection();
			StringBuilder selectQuery = new StringBuilder();
			selectQuery.append("SELECT user_id");
			selectQuery.append(" FROM tbl_user ");
			selectQuery.append(" WHERE user_id = ?");
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setInt(index++, id);
			rs = pre.executeQuery();
			check = rs.next();
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("TblUserDao.getTbluserByID" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblUserDao#updateTblUser(manageuser.entities.TblUserEntity)
	 */
	@Override
	public void updateTblUser(TblUserEntity tblUserEntity) throws SQLException {
		int index = 1;
		StringBuilder selectQuery = new StringBuilder();
		selectQuery.append("update tbl_user ");
		selectQuery.append("set group_id = ? , ");
		selectQuery.append("full_name = ? , ");
		selectQuery.append("full_name_kana = ? , ");
		selectQuery.append("email = ? , ");
		selectQuery.append("tel = ? , ");
		selectQuery.append("birthday = ? ");
		selectQuery.append("where user_id = ?");
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString(), Statement.RETURN_GENERATED_KEYS);
			pre.setInt(index++, tblUserEntity.getGroupId());
			pre.setString(index++, tblUserEntity.getFullName());
			pre.setString(index++, tblUserEntity.getFullNameKana());
			pre.setString(index++, tblUserEntity.getEmail());
			pre.setString(index++, tblUserEntity.getTel());
			pre.setString(index++, tblUserEntity.getBirthday());
			pre.setInt(index++, tblUserEntity.getUserId());
			// them du lieu
			pre.executeUpdate();
		} catch (SQLException e) {
			System.out.println("TblUserDaoImpl.updateTblUser: " + e.getMessage());
			throw e;
		}
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#checkRuleAdim(int)
	 */
	@Override
	public boolean checkRuleAdmin(int userId) throws ClassNotFoundException, NullPointerException, SQLException {
		int index = 1;
		boolean check = false;
		StringBuilder selectQuery = new StringBuilder(" Select Rule ");
		selectQuery.append(" From tbl_user ");
		selectQuery.append(" Where user_id = ? ");
		selectQuery.append(" and Rule = ? ");
		try {
			getConnection();
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setInt(index++, userId);
			pre.setInt(index++, ConstantUtils.RULE_ADMIN);
			rs = pre.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("TblUserDaoImpl.checkRuleAdim " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#deleteTblUser(int)
	 *
	 */
	@Override
	public boolean deleteTblUser(int userId) throws ClassNotFoundException, NullPointerException, SQLException {
		int index = 1;
		StringBuilder selectQuery = new StringBuilder("DELETE FROM tbl_user ");
		selectQuery.append(" WHERE rule = ? ");
		selectQuery.append(" AND user_id = ? ");
		boolean check = false;
		try {
			// khởi tạo câu lệnh truy vấn kiểu PreparedStatement
			pre = conn.prepareStatement(selectQuery.toString());
			pre.setInt(index++, ConstantUtils.RULE_USER);
			pre.setInt(index++, userId);

			// them du lieu
			if (pre.executeUpdate() > 0) {
				check = true;
			}
		} catch (SQLException e) {
			System.out.println("TblUserDaoImpl.deleteTblUser: " + e.getMessage());
			throw e;
		} 
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.BaseDao#checkCol(java.lang.String)
	 *
	 */
	@Override
	public List<String> listCol() throws NullPointerException, SQLException, ClassNotFoundException {
		List<String> result = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select column_name from INFORMATION_SCHEMA.columns ");
		sql.append("where TABLE_SCHEMA='chumanhquyetthang_manageuser'");
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			while (rs.next()) {
				String columnName = rs.getString("column_name");
				result.add(columnName);
			}

		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("BaseDaoImpl.listCol: " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return result;
	}
}
