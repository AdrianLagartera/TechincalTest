package com.prueba.zara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.zara.interfaces.PruebaTecnicaControllerInterface;
import com.prueba.zara.model.PruebaTecnicaProducto;
import com.prueba.zara.service.PruebaTecnicaService;
import com.prueba.zara.utils.PruebaTecnicaConstants;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class PruebaTecnicaControllerProducto implements PruebaTecnicaControllerInterface {

	private final HttpServletRequest request;

	@Autowired
	public PruebaTecnicaControllerProducto(ObjectMapper objectMapper, HttpServletRequest request) {
		this.request = request;
	}

	@Autowired
	private PruebaTecnicaService pruebaTecnicaService;
	
	
	public ResponseEntity<List<PruebaTecnicaProducto>> getProductSimilar(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema())
			@PathVariable("productId")
			String productId) {
		String accept = request.getHeader("Accept");
		if (accept != null) {
			List<Integer> listaIDSimilares = pruebaTecnicaService.getListaProductosID(productId);
			if(listaIDSimilares != null)
				
			return new ResponseEntity<List<PruebaTecnicaProducto>>(pruebaTecnicaService.getListaProductosSimilares(listaIDSimilares), HttpStatus.OK);

		}

		return new ResponseEntity(PruebaTecnicaConstants.ERROR_NOT_FOUND , HttpStatus.NOT_FOUND);
	}

}
