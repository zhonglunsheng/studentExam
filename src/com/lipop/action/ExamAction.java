package com.lipop.action;

import com.lipop.dao.ExamDao;
import com.lipop.dao.QuestionDao;
import com.lipop.model.Exam;
import com.lipop.model.PageBean;
import com.lipop.model.Question;
import com.lipop.util.PageUtil;
import com.lipop.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ServletRequestAware;


public class ExamAction extends ActionSupport implements ServletRequestAware {
    private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private int totalScore;
	private String mainPage;
	private ExamDao examDao = new ExamDao();
	private QuestionDao questionDao = new QuestionDao();
	private Exam exam;
	private Exam s_exam; //后台学生成绩查询
	private List<Exam> s_examList;
	private String page;
	private String pageCode;


    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getMainPage() {
        return mainPage;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Exam> getS_examList() {
        return s_examList;
    }

    public void setS_examList(List<Exam> s_examList) {
        this.s_examList = s_examList;
    }
    
    public Exam getS_exam() {
		return s_exam;
	}

	public void setS_exam(Exam s_exam) {
		this.s_exam = s_exam;
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

	public String add()throws Exception{
        Map<String,String[]> map = request.getParameterMap();
        Iterator<Map.Entry<String,String[]>> it = map.entrySet().iterator();
        int radioeScore = 0;
        int checkboxScore = 0;
        while (it.hasNext()){
            Map.Entry<String,String[]> entry = it.next();
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (key.equals("exam.paper.id")||key.equals("exam.student.id")){
                continue;
            }
            String type = key.split("-")[1];
            String questionId = key.split("-")[2];
            String value = "";
            if (type.equals("r")){
                String answer = values[0];
                radioeScore+=getScore(questionId,answer,type);
            }else {
                for (String s:values){
                    value+=s+",";
                }
                String answer = value.substring(0,value.length()-1);
                checkboxScore += this.getScore(questionId, answer, type);
            }
        }
        totalScore=radioeScore+checkboxScore;


        exam.setMoreScore(checkboxScore);
        exam.setScore(totalScore);
        exam.setSingleScore(radioeScore);
        exam.setExamDate(new Date());

        examDao.saveExam(exam);
        mainPage="exam/examResult.jsp";
        return SUCCESS;
    }

    public int getScore(String questionId,String answer,String type)throws Exception{
        Question question = questionDao.getQuestion(questionId);
            if (question.getAnswer().equals(answer)){
                if (type.equals("r")){
                    return 20;
                }else {
                    return 30;
                }
            }else {
                return 0;
            }
    }

    public String list()throws Exception{
        s_examList = examDao.getExams(exam);
        mainPage="exam/myExam.jsp";
        return SUCCESS;
    }
    
    public String examList() throws Exception{
        HttpSession session = request.getSession();
        Object o = session.getAttribute("s_exam");
        if (StringUtil.IsEmpty(page)) {
			page="1";
		}
        PageBean pageBean = new PageBean();
        pageBean.setPage(Integer.parseInt(page));
        pageBean.setPageSize(3);
        if (s_exam==null) {
        	s_exam=new Exam();
        	session.setAttribute("s_exam", s_exam);
		}
        if (o!=null) {
        	s_exam = (Exam)o;
        	session.setAttribute("s_exam", s_exam);
		}
    	s_examList=examDao.getExamList(s_exam,pageBean);
        int total = examDao.examTotal(s_exam);
        pageCode=PageUtil.studentPage("exam!examList", pageBean, total);
        mainPage="exam/examList.jsp";
        return SUCCESS;
    }
    /**
     * 后台跳转首页转发
     * @Title: mainPage   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String mainPage()throws Exception{
    	mainPage="common/default.jsp";
    	return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request=request;
    }
}
