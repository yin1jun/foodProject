package com.food.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Customer;
import com.food.model.Food;


@Service @Transactional
public class FoodDao {
	@Resource SessionFactory factory;
	
	 /*���Food��Ϣ*/
    public void AddFood(Food food) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(food);
    }
    
    /*ɾ��Food��Ϣ*/
    public void DeleteFood (Integer foodId) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object food = s.load(Food.class, foodId);
        s.delete(food);
    }
    
    /*����Food��Ϣ*/
    public void UpdateFood(Food food) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(food);
    }
    
    /*��ѯ����Food��Ϣ*/
    public ArrayList<Food> QueryAllFood() {
        Session s = factory.getCurrentSession();
        String hql = "From Food";
        Query q = s.createQuery(hql);
        List foodList = q.list();
        return (ArrayList<Food>) foodList;
    }

    /*����������ȡ����*/
    public Food GetFoodById(Integer foodid) {
        Session s = factory.getCurrentSession();
        Food food = (Food)s.get(Food.class, foodid);
        return food;
    }
    
    /*���ݲ�ѯ������ѯ*/
    public ArrayList<Food> QueryFoodInfo(String foodname) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Food food where 1=1";
    	if(!foodname.equals("")) hql = hql + " and food.foodname like '%" + foodname + "%'";
    	Query q = s.createQuery(hql);
    	List foodList = q.list();
    	return (ArrayList<Food>) foodList;
    }

}
