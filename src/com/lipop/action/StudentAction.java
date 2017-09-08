package com.lipop.action;

import com.lipop.dao.StudentDao;
import com.lipop.model.PageBean;
import com.lipop.model.Student;
import com.lipop.util.DateUtil;
import com.lipop.util.PageUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class StudentAction extends ActionSupport implements ServletRequestAware {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
    private Student student;
    private String error;
    private String page;
    private String pageCode;
    private List<Student> studentList;
    private StudentDao studentDao = new StudentDao();
    private String mainPage;
    private String title;
    private String id;
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 学生登陆
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        HttpSession session = request.getSession();
        Student currentStu = studentDao.login(student);
        if (currentStu!=null){
            session.setAttribute("currentStu",currentStu);
            return SUCCESS;
        }else{
            error="准考证号或密码错误";
            return ERROR;
        }
    }


    /**
     * 更新学生密码
     * @return
     * @throws Exception
     */
    public String updatePassword() throws Exception{
        studentDao.updateStudent(student);
        mainPage="student/updateSuccess.jsp";
        return SUCCESS;
    }

    public String update() throws Exception{
        mainPage="student/updatePassword.jsp";
        return SUCCESS;
    }

    /**
     * 学生退出登录
     * @return
     * @throws Exception
     */
    public String logOut() throws Exception{
        request.getSession().invalidate();
        return "logOut";
    }

    /**
     * 学生列表
     * @return
     * @throws Exception
     */
    public String list()throws Exception{
        HttpSession session = request.getSession();
        if (student==null){
            Object o = session.getAttribute("s_student");
            if (o!=null){
                student = (Student)o;
            }else{
                student=new Student();
            }
        }else{
           session.setAttribute("s_student",student);
        }
        PageBean pageBean = new PageBean();
        if (StringUtil.IsEmpty(page)){
            page="1";
        }
        pageBean.setPage(Integer.parseInt(page));
        pageBean.setPageSize(5);
        int totalStudent = studentDao.studentTotal(student);
        studentList=studentDao.getStudents(student,pageBean);
        pageCode= PageUtil.studentPage("student!list",pageBean,totalStudent);
        mainPage="student/studentList.jsp";
        return SUCCESS;
    }

    /**
     * 后台学生信息修改
     * @return
     * @throws Exception
     */
    public String preSave() throws Exception{

      if (StringUtil.IsEmpty(id)){
            title="添加学生信息";
        }else{
            title="修改学生信息";
            student = studentDao.getStudentById(id);
        }
        mainPage="student/studentSave.jsp";
        return SUCCESS;
    }

    /**
     * 保存和更新学生信息
     * @return
     * @throws Exception
     */
    public String add() throws Exception{
        if (StringUtil.isNotEmpty(id)){
            String id = DateUtil.formatDate(new Date(),"yyyyMMddhhmmss");
            student.setId("JS"+id);
        }
        studentDao.add(student);
        return "add";
    }

    /**
     * 删除学生信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception{
        student = studentDao.getStudentById(id);
        studentDao.deleteStudent(student);
        JSONObject object = new JSONObject();
        object.put("success",true);
        ResponseUtil.write(object, ServletActionContext.getResponse());
        return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
            this.request=request;
    }
}
