package com.lipop.dao;

import com.lipop.model.Paper;
import com.lipop.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class PaperDao {
    /**
     * 学生选择试卷
     * @return
     * @throws Exception
     */
    public List<Paper> list()throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Paper");
        @SuppressWarnings("unchecked")
		List<Paper> paperList = (List<Paper>)query.list();
        session.getTransaction().commit();
        return paperList;
    }

    /**
     * 通过试卷id获取对象
     * @param paperId
     * @return
     * @throws Exception
     */
    public Paper getPaper(String paperId)throws Exception{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Paper paper = (Paper)session.get(Paper.class,Integer.parseInt(paperId));
        session.getTransaction().commit();
        return paper;
    }

    /**
     * 删除试卷
     * @param paper
     * @throws Exception
     */
    public void deletePaper(Paper paper)throws Exception{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(paper);
        session.getTransaction().commit();
    }
    
    /**
     * 试卷信息修改
     * @Title: updatePaper   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param paper
     * @param: @throws Exception      
     * @return: void      
     * @throws
     */
    public void updatePaper(Paper paper)throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.merge(paper);
	    session.getTransaction().commit();
    }
}
