package com.cursoudemy.microservicios.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cursoudemy.microservicios.item.models.Item;
import com.cursoudemy.microservicios.item.models.Producto;

@Service
public class ItemServiceRestTemplate implements ItemService{

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		//El metodo del array .asList() convierte el arreglo obtenido en una lista
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		/*
		 * 1- EL metodo streem es un metodo de las LISTAS que permite convertir una lista en un flujo
		 * 2- Con el metodo MAP de la lista cambiamos cada elemento (objeto) del flujo
		 * 	En este caso se convierte cada objeto Producto en un objeto Item
		 * 3- Para esto el MAP utiliza una funcion LANDA para por cada elemento obtener el Producto
		 * 4- El metodo COLLECT(COLLECTORS.TOLIST() se utiliza para convertir el String obtenido en un tipo LIST
		 * */
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		/*
		 * 1- Para pasar el parametro "id" utilizamos el MAP en donde definidmos el nombre y valor de este
		 * 2- Pasamos como tercer argumento del metodo getForObject() el MAP recien creado
		 * 3- Por ultimo retornamos el objeto ITEM con el PRODUCTO y la cantidad
		 */
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

}
