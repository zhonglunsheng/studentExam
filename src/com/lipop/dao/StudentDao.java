package com.lipop.dao;

import com.lipop.model.PageBean;
import com.lipop.model.Student;
import com.lipop.util.HibernateUtil;
import com.lipop.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;

public class StudentDao {
    /**
     * 学生端登录
     * @param student
     * @return
     * @throws Exception
     */
    public Student login(Student student)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Student as s where s.id=:id and s.password=:password");
        query.setString("id",student.getId())
                .setString("password",student.getPassword());
        Student resultStu = (Student)query.uniqueResult();
        session.getTransaction().commit();
        return resultStu;
    }

    /**
     * 学生更新个人密码
     * @param student
     * @throws Exception
     */
    public void updateStudent(Student student)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();
    }

    /**
     * 管理员后台学生列表
     * @param student
     * @param pageBean 
     * @return
     * @throws Exception
     */
    public List<Student> getStudents(Student student, PageBean pageBean)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StringBuilder hql = new StringBuilder("from Student");
        if (StringUtil.isNotEmpty(student.getId())){
            hql.append(" and id like '%"+student.getId()+"%'");
        }
        if (StringUtil.isNotEmpty(student.getName())){
            hql.append(" and name like '%"+student.getName()+"%'");
        }
        Query query = session.createQuery(hql.toString().replaceFirst("and","where"));
        if (pageBean!=null){
            query.setFirstResult(pageBean.getPageStart())
                    .setMaxResults(pageBean.getPageSize());
        }
        @SuppressWarnings("unchecked")
		List<Student> studentList = query.list();
        session.getTransaction().commit();
        return studentList;
    }

    /**
     * 学生个数统计
     * @param student
     * @return
     * @throws Exception
     */
    public int studentTotal(Student student)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StringBuilder sql = new StringBuilder("select count(*) from t_student");
        if (StringUtil.isNotEmpty(student.getId())){
            sql.append(" and id like '%").append(student.getId()).append("%'");
        }
        if (StringUtil.isNotEmpty(student.getName())){
            sql.append(" and name like '%").append(student.getName()).append("%'");
        }
        Query query = session.createSQLQuery(sql.toString().replaceFirst("and","where"));
        int total = ((BigInteger)query.uniqueResult()).intValue();
        session.getTransaction().commit();
        return total;
    }

    /**
     * 管理员添加学生记录
     * @param student
     * @throws Exception
     */
    public void add(Student student)throws Exception{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();
    }

    /**
     * 管理员通过学生id获取信息
     * @return
     * @throws Exception
     */
    public Student getStudentById(String id)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Student s where s.id=:id";
        Query query =session.createQuery(hql);
        query.setString("id",id);
        Student student = (Student) query.uniqueResult();
        session.getTransaction().commit();
        return student;
    }

    /**
     * 管理员删除学生记录
     * @Title: deleteStudent   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param student
     * @param: @throws Exception      
     * @return: void      
     * @throws
     */
    public void deleteStudent(Student student)throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
    }
}
