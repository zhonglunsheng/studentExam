package com.lipop.action;

import com.lipop.dao.ManagerDao;
import com.lipop.model.Manager;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManagerAction extends ActionSupport implements ServletRequestAware {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
    private Manager Manager;
    private String error;
    private String mainPage;
    private ManagerDao ManagerDao = new ManagerDao();

    public Manager getManager() {
        return Manager;
    }

    public void setManager(Manager Manager) {
        this.Manager = Manager;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMainPage() {
        return mainPage;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    /**
     * 管理员登录
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        HttpSession session = request.getSession();
        Manager currentManager = ManagerDao.login(Manager);
        if (currentManager!=null){
            session.setAttribute("currentManager",currentManager);
            return SUCCESS;
        }else{
            error="账号或密码错误";
            return "login2";
        }
    }


    /**
     * 更新密码
     * @return
     * @throws Exception
     */
    public String updatePassword() throws Exception{
        ManagerDao.updateManager(Manager);
        mainPage="Manager/updateSuccess.jsp";
        return SUCCESS;
    }
    /**
     * 跳转密码更新页面
     * @Title: update   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String update() throws Exception{
        mainPage="Manager/updatePassword.jsp";
        return SUCCESS;
    }
    /**
     * 退出登录
     * @Title: signOut   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String logOut() throws Exception{
        request.getSession().invalidate();
        return "login2";
    }


    @Override
    public void setServletRequest(HttpServletRequest request) {
            this.request=request;
    }
}
