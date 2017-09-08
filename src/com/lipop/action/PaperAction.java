package com.lipop.action;

import com.lipop.dao.PaperDao;
import com.lipop.dao.QuestionDao;
import com.lipop.model.Paper;
import com.lipop.model.Question;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

import java.util.*;


import org.apache.struts2.ServletActionContext;

public class PaperAction extends ActionSupport{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	private List<Paper> paperList;
    private String mainPage;
    private PaperDao paperDao = new PaperDao();
    private QuestionDao questionDao = new QuestionDao();

    private Paper paper;
    private String paperId;
    private String title; //操作标题

    private List<Question> squestionList = new ArrayList<Question>();
    private List<Question> mquestionList = new ArrayList<Question>();

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    public String getMainPage() {
        return mainPage;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<Question> getSquestionList() {
        return squestionList;
    }

    public void setSquestionList(List<Question> squestionList) {
        this.squestionList = squestionList;
    }

    public List<Question> getMquestionList() {
        return mquestionList;
    }

    public void setMquestionList(List<Question> mquestionList) {
        this.mquestionList = mquestionList;
    }
    
    

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String list()throws Exception{
        paperList=paperDao.list();
        mainPage="exam/selectPaper.jsp";
        return SUCCESS;
    }

    public String paperList()throws Exception{
        paperList=paperDao.list();
        mainPage="paper/paperList.jsp";
        return SUCCESS;
    }

    public String getDetailPaper()throws Exception{
        paper = paperDao.getPaper(paperId);
        Set<Question> questions = paper.getQuestions();
        @SuppressWarnings("rawtypes")
		Iterator iterator = questions.iterator();
        while (iterator.hasNext()){
            Question question = (Question)iterator.next();
            if (question.getType().equals("1")){
                squestionList.add(question);
            }else {
                mquestionList.add(question);
            }
        }
        squestionList = this.getRandomQuestion(squestionList,3);
        mquestionList = this.getRandomQuestion(mquestionList,2);
        mainPage="exam/paper.jsp";
        return SUCCESS;
    }

    public List<Question> getRandomQuestion(List<Question> questions,int num)throws Exception{
        List<Question> resultList = new ArrayList<Question>();
        Random random = new Random();
        if (num>0){
            if (questions.size()>num){
                for (int i=0;i<num;i++){
                    int n = random.nextInt(questions.size());
                    Question q = questions.get(n);
                    if (!resultList.contains(q)){
                        resultList.add(q);
                    }else{
                        i--;
                    }
                }
            }
        }
        return resultList;
    }
    /**
     * 删除试卷
     * @Title: delete   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String delete()throws Exception{
    	JSONObject object = new JSONObject();
		if (questionDao.isExistQuestion(paperId)) {
			object.put("error", "该试卷下有题目不能删除");
		}else{
			paper = paperDao.getPaper(paperId);
	    	paperDao.deletePaper(paper);
	    	object.put("success", true);
		}
    	ResponseUtil.write(object, ServletActionContext.getResponse());
    	return null;
    }
    
    /**
     * 更新试卷
     * @Title: update   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String update()throws Exception{
    	if (StringUtil.isNotEmpty(paperId)) {
			paper.setId(Integer.parseInt(paperId));
		}else{
			paper.setJoinDate(new Date());
		}
    	paperDao.updatePaper(paper);
    	return "update";
    }
    
    /**
     * 查看试卷
     * @Title: preSave   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public String preSave()throws Exception{
    	if (StringUtil.IsEmpty(paperId)) {
			title="添加试卷";
		}else{
			title="修改试卷";
			paper=paperDao.getPaper(paperId);
		}
    	mainPage="paper/paperSave.jsp";
    	return SUCCESS;
    }
}
