package manageuser.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.TblUserEntity;
import manageuser.logics.TblUserLogic;
import manageuser.logics.Impl.TblUserLogicImpl;
import manageuser.utils.ConstantUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			// Lấy session về
			HttpSession session = req.getSession();
			// lây thông tin loginName trên session
			String loginName =  (String) session.getAttribute(ConstantUtils.LOGIN_NAME);
			// lấy đường dẫn link /09_chumanhquyetthang_manage_user/ 
			String path = req.getRequestURI();
						
		    // nếu loginName khác null
			if (loginName != null) {
				// khơi tạo đối tượng tblUserLogic
				TblUserLogic tblUserLogic = new TblUserLogicImpl();
				// khởi tạo thuộc bảng TblUserEnity để lấy đối tượng theo loginName
				TblUserEntity tblUserEntity = tblUserLogic.getTblUserByLoginName(loginName);
				 // nếu  có đối tượng và nó có quyền bằng 0
				if (tblUserEntity != null && tblUserEntity.getRule() == ConstantUtils.RULE_ADMIN) {
					// Nếu đang ở trang login.do hoặc ADM001.jsp thì chuyển sang trang ADM002
					if (path.endsWith(ConstantUtils.LINK_LOGIN)|| path.endsWith(ConstantUtils.LINKADM001)) {
						resp.sendRedirect(req.getContextPath() + ConstantUtils.URL_LISTUSER_DO);
					} else {
						chain.doFilter(request, response);
					}
				} else { // Khi chưa login 
				    
					// Xóa session khi chưa đăng nhập
					session.removeAttribute(ConstantUtils.LOGIN_NAME);
					// chuyển đến trang login
					resp.sendRedirect(req.getContextPath()+ConstantUtils.LINK_LOGIN);
				} 
				// Nếu chưa login mà nhập đường dẫn login.do
			} else 
				if (path.endsWith(ConstantUtils.LINK_LOGIN)) {
				chain.doFilter(request, response);
			} else {
				//cho trở về MH login vì chưa đăng nhập
				resp.sendRedirect(req.getContextPath()+ConstantUtils.LINK_LOGIN);
			}
		
		} catch (Exception e) {
			System.out.println("LoginFilter.doGet" +e.getMessage());
			resp.sendRedirect(ConstantUtils.SYSTEM_ERR+"?message="+ConstantUtils.ERR015);
			 
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
