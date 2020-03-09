/**
 *Coppyright [2019]Luvina company
 *TblMstGroupEntities.java
 */
package manageuser.entities;

/**
 * @author QuyetThang
 *
 */
public class MstGroupEntity {
	private int groupId;
	//tên nhóm
	private String groupName;
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 
	 */
	public MstGroupEntity() {
		super();
	}
	
}
