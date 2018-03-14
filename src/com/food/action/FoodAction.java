package com.food.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.FoodDao;
import com.food.model.*;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class FoodAction extends ActionSupport{
	
	 /*ҵ������*/
    @Resource FoodDao foodDao;
    
    
    private Food food;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	private List<Food> foodList;
	
	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
	private String keyWords;
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	/*���Food*/
	public String addFood() throws Exception{
		
		System.out.println(food.getFoodname());
		foodDao.AddFood(food);
		return "message";
		
	}
	
	/*��ʾ����Food*/
    public String showFood() {
        
        foodList = foodDao.QueryAllFood();
        return "show_view";
    }

    /*��ʾĳһFood����ϸ��Ϣ*/
    public String showDetail() {
    	food = foodDao.GetFoodById(food.getFoodid());
        return "detail_view";
    }
    
    /*��ʾfood���޸���*/
    public String showEdit() throws Exception {
    	food = foodDao.GetFoodById(food.getFoodid());
        return "edit_view";
    }

    /*�༭food*/
    public String editFood() throws Exception {
    	foodDao.UpdateFood(food);
        return "edit_message";
    }
    
    /*ɾ��Food*/
    public String deleteFood() throws Exception {
    	foodDao.DeleteFood(food.getFoodid());
        return "delete_message";
    }
    
    /*��ѯFood*/
    public String queryFoods() throws Exception {
    	foodList = foodDao.QueryFoodInfo(keyWords);
        return "show_view";
    }




}
