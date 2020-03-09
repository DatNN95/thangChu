/**

 * 
 * Coppyright(C) [2019] Luvina software company
 * ListuserController.java [Aug 4, 2019]
 */
package manageuser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.UserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.MstGroupLogicImpl;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConfigUtils;
import manageuser.utils.ConstantUtils;
import manageuser.utils.MessageErrProperties;

/**
 *  @author Quyetthang
 * Servlet implementation class ListUserController
 */
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
		super();
	}

	/**
	 * List user có 4 TH
	 * TH1 : sort
	 * TH2 : paging
	 * TH3 : back
	 * TH4 : khác với các trường hợp còn lại (search)
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			// lấy đối tượng đối session

			List<UserInfor> lisUserInfors = new ArrayList<UserInfor>();
			List<MstGroupEntity> groupEntities = new ArrayList<MstGroupEntity>();
			MstGroupLogic groupLogic = new MstGroupLogicImpl();
			TblUserLogic tblUserLogic = new TblUserLogicImpl();

			// xét trường hợp mặc định
			int limit = Common.getLimit();
			int limitPage = Integer.parseInt(ConfigUtils.getData("LimitPage"));
			String sortByFullName = ConstantUtils.ASC;
			String sortByCodeLevel = ConstantUtils.ASC;
			String sortByEndDate = ConstantUtils.DESC;
			String sortType = "";
			String sortValue = "";
			int currentPage = ConstantUtils.CURRENT_PAGE;
			int groupId = ConstantUtils.GROUP_ID_DEFAULT;
			String fullName = ConstantUtils.FULL_NAME_DEFAULT;
			int offset = ConstantUtils.OFFSET_DEFAULT;
			List<Integer> lsPaging = new ArrayList<Integer>();
			int totalPage = 0;

			// Lấy danh sách group
			groupEntities = groupLogic.gettAllMstGroup();
			// tạo biến biến action
			String action = "";
			// kiếm tra giá trị của biến action có null hay không
			action = request.getParameter("action");
			// Trường hợp đều sort, paging , search đều lấy chung dữ liệu 
			if (!ConstantUtils.ACTION_BACK.equals(action)) {
				fullName = request.getParameter("name") != null ? request.getParameter("name") : "";
				groupId = Integer.parseInt(request.getParameter("group_id") != null ? request.getParameter("group_id").toString() : "0");
				sortType = request.getParameter("sortType") != null ? request.getParameter("sortType") : "";
			}
			if (ConstantUtils.ACTION_SORT.equals(action)) { // trường hợp sort
				currentPage = Common.convent(session.getAttribute("currentPage").toString(),ConstantUtils.MIN_1);
				// lấy kieu sap xep cua cot uu tien sap xep
				sortValue = request.getParameter("sortValue");
				if ("full_name".equals(sortType)) {
					sortByFullName = sortValue;
				} else if ("code_level".equals(sortType)) {
					// chuyển đổi giá trị kiểu sắp xếp
					sortByCodeLevel = sortValue;
				} else if ("end_date".equals(sortType)) {
					// chuyển đổi giá trị kiểu sắp xếp
					sortByEndDate = sortValue;
				}
			} else if (ConstantUtils.PAGING.equals(action)) { // Trường hợp paging
				currentPage =Common.convent(request.getParameter("page"),ConstantUtils.CURRENT_PAGE);
				// Lấy các giá trị sort về
				sortByFullName = request.getParameter("sortByFullName");
				sortByCodeLevel = request.getParameter("sortByCodeLevel");
				sortByEndDate = request.getParameter("sortByEndDate");

			} else if (ConstantUtils.ACTION_BACK.equals(action)) { // Trường hợp back
				// lấy dữ liệu trên session để giữ được giá trị như lúc chuyển trang
				sortType = session.getAttribute("sortType").toString();
				fullName = session.getAttribute(ConstantUtils.FULL_NAME).toString();
				groupId = Integer.parseInt(session.getAttribute(ConstantUtils.GROUP_ID).toString());
				sortByFullName = session.getAttribute("sortByFullName").toString();
				sortByCodeLevel = session.getAttribute("sortByCodeLevel").toString();
				sortByEndDate = session.getAttribute("sortByEndDate").toString();
				currentPage = Integer.parseInt(session.getAttribute("currentPage").toString());
			}
			// tổng số record
			int totalUser = tblUserLogic.getTotalUsers(groupId, fullName);
			
			// tổng số paging
			if (totalUser > 0) {
				totalPage = Common.getTotalPage(totalUser, limit);
				// list paging     
			
     			if (currentPage >= totalPage) {
					currentPage = totalPage;
				} else if (currentPage <= ConstantUtils.CURRENT_PAGE){
					currentPage = ConstantUtils.CURRENT_PAGE;
				}
				lsPaging = Common.getListPaging(totalUser, limit, currentPage);
				
				// tính điểm bắt đầu của vị trí
				offset = Common.getOffset(currentPage, limit);
				// dánh sách user
				lisUserInfors = tblUserLogic.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName,
						sortByCodeLevel, sortByEndDate);				
			} else {
				String MSG005 = MessageErrProperties.getData(ConstantUtils.MSG005);
				request.setAttribute(ConstantUtils.MSG005, MSG005);
			}

			// xét list user lên request để lấy giá trị phục vụ cho màn hình ADM002
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("lisUserInfors", lisUserInfors);
			request.setAttribute("limitPage", limitPage);
			request.setAttribute("totalUser", totalUser);
			request.setAttribute("listPaging", lsPaging);
			request.setAttribute("groupEntities", groupEntities);

			// Xét lên session
			// xét fullName để phục vụ trường hợp back lại 
			session.setAttribute(ConstantUtils.FULL_NAME, fullName);
			// xét groupId để phục vụ trường hợp back lại 
			session.setAttribute(ConstantUtils.GROUP_ID, groupId);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("sortType", sortType);
			session.setAttribute("sortByFullName", sortByFullName);
			session.setAttribute("sortByCodeLevel", sortByCodeLevel);
			session.setAttribute("sortByEndDate", sortByEndDate);
			request.getRequestDispatcher(ConstantUtils.ADM002).forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			System.out.println("ListUserController.doGet" +e.getMessage());
		}

	}

}
