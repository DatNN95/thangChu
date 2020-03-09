/**
 *Coppyright [2019]Luvina company
 *TblMstJapanEntities.java
 */
package manageuser.entities;

/**
 * @author QuyetThang
 *
 */
public class MstJapanEntity {
	private String codeLevel;
	// trình độ tiếng nhật
	private String nameLevel;
	
	
	/**
	 * 
	 */
	public MstJapanEntity() {
		super();
	}
	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}
	/**
	 * @param codeLevel the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}
	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
 
}
