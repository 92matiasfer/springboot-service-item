package com.cursoudemy.microservicios.item.service;

import java.util.List;

import com.cursoudemy.microservicios.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
}
