package com.lipop.util;

import com.lipop.model.PageBean;

public class PageUtil {
	/**
	 * 跳转的url 分页bean 总计的数量
	 * @Title: studentPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param tagerUrl
	 * @param: @param pageBean
	 * @param: @param totalStudent
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
    public static String studentPage(String tagerUrl, PageBean pageBean,int totalStudent){
       int totalPage = (totalStudent%pageBean.getPageSize()==0)?totalStudent/pageBean.getPageSize():(totalStudent/pageBean.getPageSize()+1);
       StringBuilder pageCode = new StringBuilder();
       pageCode.append("<li><a href='").append(tagerUrl).append("?page=1'>首页</a></li>");
       for (int i=pageBean.getPage()-2;i<=pageBean.getPage()+2;i++){
           if (i<1||i>totalPage){
               continue;
           }
           if (pageBean.getPage()==i){
               pageCode.append("<li class='active'><a href='#'>").append(i).append("</a></li>");
           }else {
               pageCode.append("<li><a href='").append(tagerUrl).append("?page=").append(i).append("'>").append(i).append("</a></li>");
           }
       }
       pageCode.append("<li><a href='").append(tagerUrl).append("?page=").append(totalPage).append("'>尾页</a></li>");
       return pageCode.toString();
    }

}
