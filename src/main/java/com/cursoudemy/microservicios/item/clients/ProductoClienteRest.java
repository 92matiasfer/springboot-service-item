package com.cursoudemy.microservicios.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cursoudemy.microservicios.item.models.Producto;

/*
 * Esta configuracion se utiliza sin implementar un BALANCEADOR DE CARGAS.
 * Por ello se debe indicar la URL lo que hace que quede todo mas acoplado (No recomendable)
 */
//@FeignClient(name="service-productos", url="localhost:8001")
/*
 * Utilizando el BALANCEADOR DE CARGAS RIBBON queda de la siguiente forma
 */
@FeignClient(name="service-productos")
public interface ProductoClienteRest {

	/*
	 * En el controlador utilizamos la anotacion @GetMapping para mapear nuestros metodos a endpoints o URL,
	 * mientras que en FeigClient indicamos la ruta para consumir el servicio en la red y obtener los datos del
	 * JSON pero convertidos a nuestros objetos, en este caso al producto 
	 * (La anotacion y la ruta puesta debe ser exactamente igual a la que nos queremos conectar)
	 */
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
}
