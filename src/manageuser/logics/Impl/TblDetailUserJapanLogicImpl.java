/**
 * Coppyright 2019 Luvina company
 * TblDetailUserJapanImpl.java 2019 
 */
package manageuser.logics.Impl;

import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.logics.TblDetailUserJapanLogic;

/**
 * @author QuyetThang
 *
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {
	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblDetailUserJapanLogic#
	 * checkExistTblDetailUserJapanByUserId(int)
	 */
	@Override
	public boolean checkExistCodeLevel(int userId)
			throws ClassNotFoundException, NullPointerException, SQLException {
		boolean check = false; 
		try {
        	TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();  
            check = tblDetailUserJapanDao.checkExistCodeLevel(userId);
        } catch (Exception e) {
			System.out.println("TbldetailUserjapnLogic.checkExist" +e.getMessage());
			throw e;
		}
          return check;
	}
}
