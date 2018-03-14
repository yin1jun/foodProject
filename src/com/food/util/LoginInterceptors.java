package com.food.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.food.model.Customer;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptors extends AbstractInterceptor{

	 
	   private static final long serialVersionUID = 1L;

	   private String sessionName; //�������ݵ�ǰ�û�����Ϣ

	   private String excludeName; //�����action��Ҳ���Ƿ��������action�Ͳ���Ҫ����

	   private List <String> list;

	   /*
	    * ��Ϊ�������ļ��Ĳ���excludeName�У������action���������ɸ����м��ö��ż����������ȡֵʱҪ��excludeName�ֽ�ɵ�����
	   һ����action�����֣�����һ��list�У�strlsit���������ֽ���Щaction������
	                 ���綺��ǰ���пո���ʹ��trim()����ȥ����Щ�ո�
	   */
	   public List<String>  strlsit(String str){

	     String[] s = str.split(",");

	     List <String> list = new ArrayList <String>();

	     for(String ss : s){

	        list.add(ss.trim());

	     }

	     return list;

	   }

	   @Override

	   public void init() {

	      list = strlsit(excludeName);

	   }

	   @Override

	   public String intercept(ActionInvocation invocation) throws Exception {

	     
		 System.out.println("--------����������--------");  //��������������֤�Ƿ��ܽ���intercept����
		 String actionName = invocation.getProxy().getActionName();   //�õ���ת��action������
		 Map <String,Object>  session = invocation.getInvocationContext().getSession();  //�õ���ǰsession

	     if(list.contains(actionName)){   //�������Ϸ���Ҳ���ǽ���¼��ע���action��������
	        
	    	System.out.println(actionName + "û�б�����");
	        return invocation.invoke();     //����֪ͨstruts2���������Ժ���¶���Ҳ���ǲ���������ʱ��ִ�е��Ǹ�action

	     }else {   //������󲻺Ϸ���Ҳ������Ҫ������

	        //�鿴session
	    	System.out.println(actionName + "��������");

	        
	        
	        //�õ���ǰ�û�����ǰ�û���login�������Ѿ�������session�У���CustomerAction�е�login������
	        Customer customer = (Customer) session.get(sessionName);   
	        
	           if(customer==null){   //���customer�����ڣ���˵����¼���ɹ�����ת��login
	        	     // ��ȡHttpServletRequest����  
	                 HttpServletRequest req = ServletActionContext.getRequest();  

	                 // ��ȡ������ĵ�ַ ��Ҳ���ǻ�ȡ����ǰҪ��ת�ĵ�ַ��������ת
	                 String path = req.getRequestURI().replaceAll("/foodProject", "");
	                 System.out.println("path:" + path);
	        
	                 // ����session�����������struts.xml�л���Ϊ��������  
	                 session.put("prePage", path);  
	        	     return "login";
	           }
	           else {                //���customer���ڣ����¼�ɹ�
	        	
	                 return invocation.invoke();    //֪ͨstruts2��ת��action��

	          }

	     }

	   }

	   public String getSessionName() {

	     return sessionName;

	   }

	   public void setSessionName(String sessionName) {

	     this.sessionName = sessionName;

	   }

	   public String getExcludeName() {

	     return excludeName;

	   }

	   public void setExcludeName(String excludeName) {

	     this.excludeName = excludeName;

	   }

	   public List<String> getList() {

	     return list;

	   }

	   public void setList(List<String> list) {

	     this.list = list;

	   }


	}
