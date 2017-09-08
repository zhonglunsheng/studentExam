package com.lipop.action;

import com.lipop.dao.PaperDao;
import com.lipop.dao.QuestionDao;
import com.lipop.model.PageBean;
import com.lipop.model.Paper;
import com.lipop.model.Question;
import com.lipop.util.PageUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

public class QuestionAction extends ActionSupport{
    
	private static final long serialVersionUID = 1L;
    private QuestionDao questionDao = new QuestionDao();
    private PaperDao paperDao = new PaperDao();
    private Question question; //后台学生成绩查询
    private List<Paper> paperList;
    private List<Question> questionList;
    private String page;
    private String pageCode;
    private String mainPage;
    private String questionId;
    private String title;


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String pageId) {
		this.page = pageId;
	}
	

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

    public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
    public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	/**
     * 题目列表
     * @Title: questionList   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
	public String questionList() throws Exception{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object o = session.getAttribute("question");
        if (StringUtil.IsEmpty(page)) {
			page="1";
		}
        PageBean pageBean = new PageBean();
        pageBean.setPage(Integer.parseInt(page));
        pageBean.setPageSize(3);
        if (question==null) {
        	question=new Question();
		}else{
			session.setAttribute("question", question);
		}
        if (o!=null) {
        	question = (Question)o;
        	session.setAttribute("question", question);
		}
    	questionList=questionDao.getQuestionList(question,pageBean);
        int total = questionDao.questionTotal(question);
        pageCode=PageUtil.studentPage("question!questionList", pageBean, total);
        mainPage="question/questionList.jsp";
        return SUCCESS;
    }
	
	/**
	 * 查看题目
	 * @Title: getQuestionContent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String getQuestionContent()throws Exception{
	    question = questionDao.getQuestion(questionId);
		mainPage="question/questionShow.jsp";
		return SUCCESS;
	}
	/**
	 * 预栏
	 * @Title: preSave   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String preSave()throws Exception{
		paperList = paperDao.list();
		if (StringUtil.IsEmpty(questionId)) {
			title="添加题目";
		}else{
			title="修改题目";
			question=questionDao.getQuestion(questionId);
		}
		mainPage="question/questionSave.jsp";
		return SUCCESS;
	}
	/**
	 * 添加和修改试题
	 * @Title: save   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String save()throws Exception{
		if (question.getId()==0) {
			question.setJoinTime(new Date());
			questionDao.saveQuestion(question);
		}else{
			questionDao.saveQuestion(question);
		}
		return "save";
	}
	/**
	 * 删除试题
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String delete()throws Exception{
		if (StringUtil.isNotEmpty(questionId)) {
			question = questionDao.getQuestion(questionId);
			questionDao.deleteQuestion(question);
			JSONObject object = new JSONObject();
			object.put("success", true);
			ResponseUtil.write(object,ServletActionContext.getResponse());
		}
		return null;
	}
	
}
