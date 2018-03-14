package com.food.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.CustomerDao;
import com.food.dao.FoodDao;
import com.food.dao.OrderDao;
import com.food.model.*;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class OrderAction extends ActionSupport{
	
	 /*ҵ������*/
    @Resource OrderDao orderDao;
    @Resource CustomerDao customerDao;
    @Resource FoodDao foodDao;
    
    private Order order;
    private List<Order> orderList;
    private Customer customer;
    private Food food;
    
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	/*���Order*/
	public String addOrder() throws Exception{
		

		customer = customerDao.QueryCustomerInfo(customer.getName()).get(0);
		Order ord =new Order();
		ord.setCustomer(customer);
		ord.setFood(food);
		//��Ϊ�ǡ���һ�ݡ���������Ϊ1
		ord.setFoodnum(1);
		ord.setTotal(foodDao.GetFoodById(food.getFoodid()).getUnitprice()*1);
		orderDao.AddOrder(ord);
		return "order_message";
		
	}
	
	/*��ʾ����Order*/
    public String showOrder() {
    	
        //��ϵͳ�趨Ϊ�û������ظ��������ϵͳ�в�ѯ����һ���������û�����
    	System.out.println(customer.getName());
        Customer cus= customerDao.QueryCustomerInfo(customer.getName()).get(0);
        //ע�ⲻ��Ҫfood�Ĳ�ѯ����ʱ����������д����ֱ�ӽ�food��������Ϊnull
        orderList = orderDao.QueryOrderInfo(cus,null);

        return "show_view";
    }

    /*��ʾĳһOrder����ϸ��Ϣ*/
    public String showDetail() {
    	System.out.print(order.getOrderid());
    	order = orderDao.GetOrderById(order.getOrderid());
        return "detail_view";
    }
    
    /*��ʾOrder���޸���*/
    /*public String showEdit() throws Exception {
    	order = orderDao.GetOrderById(order.getOrderid());
        return "edit_view";
    }*/

    /*�༭Order*/
    /*public String editOrder() throws Exception {
    	orderDao.UpdateOrder(order);
        return "edit_message";
    }*/
    
    /*ɾ��Order*/
    /*public String deleteOrder() throws Exception {
    	orderDao.DeleteOrder(food.getFoodid());
        return "delete_message";
    }*/
    
    /*��ѯOrder*/
    public String queryOrders() throws Exception {
    	orderList = orderDao.QueryOrderInfo(customer,food);
        return "show_view";
    }


}
