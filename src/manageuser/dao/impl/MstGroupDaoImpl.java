/**
 * Coppyright(C) [2019] Luvina software company
 *MstGroupDaoImpl.java [Aug 6, 2019]
 */
package manageuser.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroupEntity;

/**
 * @author QuyetThang
 *  Lấy danh sách Group
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {
	
	/* (non-Javadoc)
	* @see manageuser.dao.MstGroupDao#getAllGroup()
	*
	*/
	@Override
	public List<MstGroupEntity> getAllMstGroup() throws NullPointerException, SQLException , ClassNotFoundException {
		List<MstGroupEntity> groupEntities = new ArrayList<MstGroupEntity>();
		StringBuilder sql = new StringBuilder("select group_id, group_name from mst_group ");
		sql.append(" order by group_id ASC");
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			rs = pre.executeQuery();
			while (rs.next()) {
				MstGroupEntity group = new MstGroupEntity();
				group.setGroupId(rs.getInt("group_id"));
				group.setGroupName(rs.getString("group_name"));
				groupEntities.add(group);
			}
		} catch (NullPointerException | SQLException |ClassNotFoundException e) {
			System.out.println("MstGroupDaoImpl" + e.getMessage());	
			throw e;
		} finally {
			closeConnect();
		}
		return groupEntities;
	}
	
	/* (non-Javadoc)
	* @see manageuser.dao.MstGroupDao#getGroupNameByGroupId(int)
	*
	*/
	@Override
	public String getGroupNameByGroupId(int groupId) throws NullPointerException, SQLException, ClassNotFoundException {
		String groupName = "";
		int index = 1;
		StringBuilder sql = new StringBuilder("select group_name from mst_group ");
		sql.append(" where group_id = ? ");
		try {
			getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setInt(index++,groupId );
			rs = pre.executeQuery();
			while (rs.next()) {
				groupName = rs.getString("group_name");
			}
			
		} catch (NullPointerException| SQLException| ClassNotFoundException e) {
		System.out.println("MstGroupDao.getGroup " +e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
		return groupName;
	}
}
