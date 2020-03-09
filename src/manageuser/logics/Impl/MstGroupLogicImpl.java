/**
 * Coppyright(C) [2019] Luvina software company
 *MstGroupLogicImpl.java [Aug 6, 2019]
 */
package manageuser.logics.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.logics.MstGroupLogic;

/**
 * @author QuyetThang
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	/**
	 * Lấy danh sách các group trong DB
	 */
	@Override
  public List<MstGroupEntity> gettAllMstGroup() throws ClassNotFoundException, NullPointerException , SQLException{
	  List<MstGroupEntity> list = new ArrayList<MstGroupEntity>();
	  MstGroupDao groupDaoImpl = new MstGroupDaoImpl();
	  try {
		  list = groupDaoImpl.getAllMstGroup();
	  } catch (Exception e) {
	      System.out.println("Lỗi không lấy được danh sách");
	      throw e;
	}
	  return list;
  }
	
	/* (non-Javadoc)
	* @see manageuser.logics.MstGroupLogic#getGroupNameByGroupId(int)
	*
	*/
	@Override
	public String getGroupNameByGroupId(int groupId) throws NullPointerException, SQLException, ClassNotFoundException {
		MstGroupDao groupDao = new MstGroupDaoImpl();
		String groupName = "" ;
		try {
		 groupName = groupDao.getGroupNameByGroupId(groupId);
		} catch (NullPointerException| SQLException | ClassNotFoundException e) {
			System.out.println("MstGroupLogic.getGroupNameByGroupId" +e.getMessage());
			throw e;
		}
		return groupName;
		
		
	}
}
