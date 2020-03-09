/**
 * Coppyright(C) [2019] Luvina software company
 *TblUserLogicImpl.java [Aug 5, 2019]
 */
package manageuser.logics.Impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.ConstantUtils;

/**
 * @author QuyetThang
 *
 */
public class TblUserLogicImpl implements TblUserLogic {

	/* 
	* (non -Javadoc)
	* @see manageuser.logics.TblUserLogic#checkUser(java.lang.String, java.lang.String)
	*/
	@Override
	public Boolean checkUser(String loginName, String pass) throws NullPointerException, SQLException, ClassNotFoundException , NoSuchAlgorithmException  {
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		TblUserEntity  tblUserEntity = new TblUserEntity();
		boolean check = false;
		try {
			tblUserEntity = tblUserDaoImpl.getTblUserByLoginName(loginName);
			if (tblUserEntity != null && tblUserEntity.getRule() == ConstantUtils.RULE_ADMIN) {				
					pass = Common.getSHA1Pass(pass, tblUserEntity.getSalt());
					if (Common.compare(pass, tblUserEntity.getPass())) {
						check = true;				
				}
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
			System.out.println("TblUserLogicImpl" + e.getMessage());
			throw e;		
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName)
			throws NullPointerException, SQLException, ClassNotFoundException {
		TblUserDao userDao = new TblUserDaoImpl();
		int total = 0;
		try {
			fullName = Common.replaceWildCard(fullName.trim());
			total = userDao.getTotalUsers(groupId, fullName);
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserLogicImpl.getTotalUsers: " + e.getMessage());
			throw e;
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 *
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws NullPointerException, SQLException, ClassNotFoundException {
		
		TblUserDao tblUserDao = new TblUserDaoImpl();
		List<UserInfor> lsUser = new ArrayList<UserInfor>();
		try {
			fullName = Common.replaceWildCard(fullName);
			lsUser = tblUserDao.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
					sortByEndDate);
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserLogicImpl.getListUsers: " + e.getMessage());
			throw e;
		}
		return lsUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getUser(java.lang.String)
	 *
	 */
	@Override
	public TblUserEntity getTblUserByLoginName(String loginName) throws NullPointerException, SQLException, ClassNotFoundException {
		TblUserEntity entity = new TblUserEntity();
		try {
			TblUserDao tblUserDao = new TblUserDaoImpl();
			entity = tblUserDao.getTblUserByLoginName(loginName);
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserLogic.getUser " + e.getMessage());
			throw e;
		}
		return entity;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getUserInforById(int)
	 */
	@Override
	public UserInfor getUserInforById(int id) throws NullPointerException, SQLException, ClassNotFoundException {
		UserInfor userInfor = new UserInfor();
		TblUserDao tblUserDao = new TblUserDaoImpl();
		try {
			userInfor = tblUserDao.getUserInforByID(id);
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserLogic.getUserInfor" + e.getMessage());
			throw e;
		}
		return userInfor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkTblUserByEmail(java.lang.String)
	 *
	 */
	@Override
	public boolean checkTblUserByEmail(String email, int id)
			throws NullPointerException, SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			TblUserDao tblUserDao = new TblUserDaoImpl();
			TblUserEntity entity = new TblUserEntity();
			entity = tblUserDao.getUserByMail(email, id);
			if (entity != null) {
				check = true;
			} else {
				check = false;
			}
			
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("tblUserLogic.checkUserByEmail " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTblUserByID(int)
	 */
	@Override
	public boolean checkExistTblUserByID(int id) throws NullPointerException, SQLException, ClassNotFoundException {
		TblUserDao tblUserDao = new TblUserDaoImpl();
		boolean check = false;
		try {
			check = tblUserDao.checkTblUserByID(id);
		} catch (NullPointerException | SQLException | ClassNotFoundException e) {
			System.out.println("TblUserLogic.getTblUserByID" + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.UserInfor)
	 *
	 */
	@Override
	public Boolean createUser(UserInfor userInfor)
			throws NullPointerException, SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		boolean check = false;
		// tạo đối tượng tblUserDao
		TblUserDao tblUserDao = new TblUserDaoImpl();
		TblDetailUserJapanDao tblDetailUserDao = new TblDetailUserJapanDaoImpl();
		TblUserEntity tblUserEntity = new TblUserEntity();
		TblDetailUserJapanEntity tblDetailUser = new TblDetailUserJapanEntity();
		Connection conn = null;
		// tạo biến để lấy id
		int userID = 0;
		try {
			conn = tblUserDao.getConnection();
			// sét bằng false để thực thi từng câu lệnh insert vào
			tblUserDao.disableAutoCommit();
			// tblUserDao.setConn(conn);

			// thêm vào tblUser
			tblUserEntity = getTblUserByUserInfor(userInfor);
			// lấy id cho user
			userID = tblUserDao.insertUser(tblUserEntity);
			userInfor.setUserId(userID);
			if (!"".equals(userInfor.getNameLevel())) {

				tblDetailUser = getTblDetailUserByUserInfor(userInfor);
				// xét vào BD
				tblDetailUserDao.setConn(conn);
				tblDetailUserDao.insertTblDetailUser(tblDetailUser);
			}
			tblUserDao.commit();
			check = true;
		} catch (NullPointerException | SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
			// Nếu lỗi thì rollback
			System.out.println("TblUserLogicImpl.createUser " + e.getMessage());
			tblUserDao.rollBack();
			throw e;
		} finally {
			tblUserDao.closeConnect();
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#getTblUserByUserInfor(manageuser.entities.
	 * UserInfor)
	 */
	@Override
	public TblUserEntity getTblUserByUserInfor(UserInfor userInfor) throws NoSuchAlgorithmException {
		TblUserEntity tblUserEntity = new TblUserEntity();
		try {
			if (userInfor.getUserId() == 0) {
				tblUserEntity.setPass(Common.getSHA1Pass(userInfor.getPass(), tblUserEntity.getSalt()));
				tblUserEntity.setRule(ConstantUtils.RULE_USER);
				tblUserEntity.setSalt(Common.getSalt());
				tblUserEntity.setLoginName(userInfor.getLoginName());
			}
			tblUserEntity.setGroupId(userInfor.getGroupId());
			tblUserEntity.setFullName(userInfor.getFullName());
			tblUserEntity.setBirthday(userInfor.getBirthday());
			tblUserEntity.setFullNameKana(userInfor.getFullNameKana());
			tblUserEntity.setEmail(userInfor.getEmail());
			tblUserEntity.setTel(userInfor.getTel());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("tblUserLogic.getTblUserByUserInfor" + e.getMessage());
			throw e;
		}
		return tblUserEntity;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTblDetailUserByUserInfor(manageuser.
	 * entities.UserInfor)
	 */
	@Override
	public TblDetailUserJapanEntity getTblDetailUserByUserInfor(UserInfor userInfor) {
		TblDetailUserJapanEntity tblDetailUser = new TblDetailUserJapanEntity();
		tblDetailUser.setUserId(userInfor.getUserId());
		tblDetailUser.setCodeLevel(userInfor.getCodeLevel());
		tblDetailUser.setEndDate(userInfor.getEndDate());
		tblDetailUser.setStartDate(userInfor.getStartDate());
		if (!"".equals(userInfor.getCodeLevel())) {
			tblDetailUser.setTotal(Integer.parseInt(userInfor.getTotal()));
		}
		return tblDetailUser;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#updateUser(manageuser.entities.UserInfor)
	 */
	@Override
	public boolean updateUser(UserInfor userInfor) throws NullPointerException, SQLException, ClassNotFoundException , NoSuchAlgorithmException {
		boolean check = false;
		// tạo đối tượng tblUserDao
		TblUserDao tblUserDao = new TblUserDaoImpl();
		TblDetailUserJapanDao tblDetailUserDao = new TblDetailUserJapanDaoImpl();
		TblUserEntity tblUserEntity = new TblUserEntity();
		TblDetailUserJapanEntity tblDetailUser = new TblDetailUserJapanEntity();
		Connection conn = null;
		try {
			conn = tblUserDao.getConnection();
			if (conn != null) {
				tblUserDao.disableAutoCommit();
				// xét giá trị lên đối tượng TblUser
				tblUserEntity = getTblUserByUserInfor(userInfor);
				// xét giá trị UserId
				tblUserEntity.setUserId(userInfor.getUserId());
				// Update đối tượng tblUser
				tblUserDao.updateTblUser(tblUserEntity);

				// xét giá trị cho DetailUser
				tblDetailUser = getTblDetailUserByUserInfor(userInfor);

				// xét vào DB
				tblDetailUserDao.setConn(conn);
				if (!"".equals(userInfor.getNameLevel())) {
					// TH1 : nếu tồn tại trình độ tiếng nhật và chọn mục trình độ tiếng nhật
					if (userInfor.getIsExistCodelevel()) {
						tblDetailUserDao.updateTblDetailUser(tblDetailUser);
					} else {
						// TH3 : nếu không tồn tại trình độ tiếng nhật và có chọn trình độ tiếng nhật
						// trên DB thì thực hiện them mới
						tblDetailUserDao.insertTblDetailUser(tblDetailUser);
					}
					// TH2 : nếu tồn tại trình độ tiếng nhật trong DB nhưng không chọn trình độ trên
				} else if (userInfor.getIsExistCodelevel()) {
					// web thì xóa
					tblDetailUserDao.deleteTblDetaiUserJapan(tblDetailUser.getUserId());
				}
				tblUserDao.commit();
				check = true;
			}
		} catch (NullPointerException | SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
			// Nếu lỗi thì rollback					
			tblUserDao.rollBack();
			System.out.println("TblUserLogicImpl.updateUser " + e.getMessage());	
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExistLoginName(java.lang.String)
	 *
	 */
	@Override
	public boolean checkExistLoginName(String loginName)
			throws ClassNotFoundException, NullPointerException, SQLException {
		TblUserDao tblUserDao = new TblUserDaoImpl();
		boolean check = false;
		try {
			TblUserEntity tblUserEntity = tblUserDao.getTblUserByLoginName(loginName);
			if (tblUserEntity != null ) {
				check = true;
			} else {
				check = false;
			}
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("tblUserLogic.checkExistLoginName" + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non -Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) throws ClassNotFoundException, NullPointerException, SQLException {
		TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
		TblUserDao tblUserDao = new TblUserDaoImpl();
		Connection conn = null;
		boolean check = false;
		try {
			conn = tblDetailUserJapanDao.getConnection();
			tblDetailUserJapanDao.disableAutoCommit();

			tblDetailUserJapanDao.deleteTblDetaiUserJapan(userId);
			// xét conn cho bên connection của bên UserDao
			tblUserDao.setConn(conn);
			tblUserDao.deleteTblUser(userId);
			tblDetailUserJapanDao.commit();
			check = true;
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			tblDetailUserJapanDao.rollBack();
			System.out.println("tblUserlogic.deleteLogicImpl " + e.getMessage());
			throw e;
		} finally {
			tblDetailUserJapanDao.closeConnect();
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkRuleAdim(int)
	 *
	 */
	@Override
	public boolean checkRuleAdmin(int userId) throws ClassNotFoundException, NullPointerException, SQLException {
		boolean check = false;
		try {
			TblUserDao tblUserDao = new TblUserDaoImpl();
			check = tblUserDao.checkRuleAdmin(userId);
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			System.out.println("TblUserLogic.checkRuleAdmin" + e.getMessage());
			throw e;
		}
		return check;
	}
}
