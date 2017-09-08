package com.lipop.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;


public class loginFilter implements Interceptor{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;

	@Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
       Map<String,Object> session= invocation.getInvocationContext().getSession();
       Object currentStu = session.get("currentStu");
       String result=null;
       if (currentStu!=null){
           result = invocation.invoke();
       }else{
           result = "error";
       }
       return result;
    }
}
