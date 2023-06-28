package com.cursoudemy.microservicios.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cursoudemy.microservicios.item.models.Item;
import com.cursoudemy.microservicios.item.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	/*
	 * Con la anotacion @Qualifier puede utilizar el bean indicado por encimar de los demas. 
	 * En este caso sera "serviceFeign"
	 */
	@Qualifier("serviceFeign") 
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}

	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
}
