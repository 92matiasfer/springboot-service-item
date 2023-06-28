package com.cursoudemy.microservicios.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cursoudemy.microservicios.item.clients.ProductoClienteRest;
import com.cursoudemy.microservicios.item.models.Item;

@Service("serviceFeign")
/*
 * Cuando una misma interfaz esta siendo utilizada por mas de un bean, se debe indicar cual de estos sera utilizado
 * 1- La antoacion @Primary indica que esta clase debe ser utilizada por encima de las demas clases.
 * 2- Mediante la anotacion @Qualifier("serviceFeign") en el controlador que esta consumiendo el bean, indicando
 * el nombre del bean (como se ve en este caso se puede modificar el nombre del bean)
 */
//@Primary 
public class ItemServiceFeign implements ItemService {

	@Autowired
	private  ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id), cantidad);
	}

}
