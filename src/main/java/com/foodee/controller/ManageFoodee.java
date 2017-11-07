package com.foodee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodee.dto.FoodItems;
import com.foodee.repository.FoodRepository;


@Controller
@RequestMapping("/manage")
public class ManageFoodee {
	

	
	@Autowired
	FoodRepository foodrepository;

	@ResponseBody
	@RequestMapping(value="/foodItems",method = RequestMethod.GET)
	public List<FoodItems> getFoodItems(){
		
		return foodrepository.findAll();
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/product/{id}/activation",method = RequestMethod.POST)
	public String handleFoodActivation(@PathVariable("id") int id){
		
		FoodItems items=foodrepository.findById(id);
		boolean active=items.isEnabled();
		items.setEnabled(!items.isEnabled());
		foodrepository.save(items);
	
		return (active)? "You have Successfully Deactivate the FoodItems with Id"+items.getId():
						 "You have Succesfully Activate the FoodItems with ID"  +items.getId();
	}

}
