/**
 * Coppyright 2019 Luvina company
 * MstJapanLogicImpl.java 2019 
 */
package manageuser.logics.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapanEntity;
import manageuser.logics.MstJapanLogic;

/**
 * @author QuyetThang
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {
 
	/* 
	* (non -Javadoc)
	* @see manageuser.logics.MstJapanLogic#gettAllMstJapan()
	*/
	@Override
	public List<MstJapanEntity> gettAllMstJapan() throws NullPointerException , SQLException, ClassNotFoundException{	 
		 List<MstJapanEntity> japanEntities = new ArrayList<MstJapanEntity>();
		 try {
			MstJapanDao japanDao = new MstJapanDaoImpl();
			 japanEntities = japanDao.getAllMstJapan();
		 } catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("Mstjapanlogic.getAll" +e.getMessage());
			throw e;
		}
	   return japanEntities;
	 }
	
	/* (non-Javadoc)
	* @see manageuser.logics.MstJapanLogic#getNameLevelByCodeLevel(java.lang.String)
	*
	*/
	@Override
	public String getNameLevelByCodeLevel(String codeLevel) throws NullPointerException, SQLException, ClassNotFoundException {
		MstJapanDao japanDao = new MstJapanDaoImpl();
		String nameLevel = "";
		try {
			nameLevel = japanDao.getNameLevelByCodeLevel(codeLevel);
		} catch (NullPointerException| SQLException | ClassNotFoundException e) {
			System.out.println("MstJapanLogic.getNameLevelByCodeLevel " +e.getMessage());
			throw e;
		}
		return nameLevel;
	}
	/* 
	* (non -Javadoc)
	* @see manageuser.logics.MstJapanLogic#checkExistNameLevel(java.lang.String)
	*/
	@Override
	public boolean checkExistNameLevel(String codeLevel)
			throws NullPointerException, SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			MstJapanDao japanDao = new MstJapanDaoImpl();
			 MstJapanEntity entity = new MstJapanEntity();
			 entity = japanDao.getJapanByCodeLevel(codeLevel);
			 if (entity != null) {
				 check = true;
			 } else {
				 check = false;
			 }
		} catch (NullPointerException| SQLException| ClassNotFoundException e) {
			System.out.println("MstJapanLogic.checkExistNameLevel " +e.getMessage());
			throw e;
		}
		
		return check;
	}
}
