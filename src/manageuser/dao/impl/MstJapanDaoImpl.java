/**
 * Coppyright(C) [2019] Luvina software company
 *MstJapanDaoImpl.java [Aug 4, 2019]
 */
package manageuser.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapanEntity;

/**
 * @author Quyetthang lấy danh sách trình độ tiếng nhật
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getAllMstJapanEntities()
	 */
	@Override
	public List<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, NullPointerException, SQLException {
		List<MstJapanEntity> mstJapanEntities = new ArrayList<MstJapanEntity>();
		StringBuilder sql = new StringBuilder("select code_level, name_level from mst_japan ");
		sql.append(" order by code_level ASC");
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			while (rs.next()) {
				MstJapanEntity japanEntity = new MstJapanEntity();
				japanEntity.setCodeLevel(rs.getString("code_level"));
				japanEntity.setNameLevel(rs.getString("name_level"));
				mstJapanEntities.add(japanEntity);
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("MstjapanDaoImpl.getAllMstJapan " + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return mstJapanEntities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getNameLevelByCodeLevel(java.lang.String)
	 *
	 */
	@Override
	public String getNameLevelByCodeLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException {
		String nameLevel = "";
		int index = 1;
		StringBuilder sql = new StringBuilder("select name_level from mst_japan ");
		sql.append(" where code_level = ? ");
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setString(index++, codeLevel);
			rs = pre.executeQuery();
			while (rs.next()) {
				nameLevel = rs.getString("name_level");
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnect();
		}
		return nameLevel;

	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#checkExistNameLevel(java.lang.String)
	 */
	@Override
	public MstJapanEntity getJapanByCodeLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException {
		MstJapanEntity japanEntity = null;
		StringBuilder sql = new StringBuilder("select * from mst_japan ");
		sql.append(" where code_level = ? ");
		int index = 1;
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setString(index++, codeLevel);
			rs = pre.executeQuery();
			while (rs.next()) {
				japanEntity = new MstJapanEntity();
				japanEntity.setCodeLevel(rs.getString("code_level"));
				japanEntity.setNameLevel(rs.getString("name_level"));
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnect();
		}

		return japanEntity;
	}
}
