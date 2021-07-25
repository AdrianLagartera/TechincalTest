package com.prueba.zara.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prueba.zara.interfaces.PruebaTecnicaServiceInterface;
import com.prueba.zara.model.PruebaTecnicaProducto;
import com.prueba.zara.utils.PruebaTecnicaConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PruebaTecnicaService implements PruebaTecnicaServiceInterface {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Integer> getListaProductosID(String id) {
		String url = new StringBuilder(PruebaTecnicaConstants.URL_PETICION_PRODUCTOS).append("/").append(id)
				.append(PruebaTecnicaConstants.PATH_SIMILARIDS).toString();
		ResponseEntity<List<Integer>> resp = new ResponseEntity<List<Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			resp = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Integer>>() {
			});
		}
		catch (Exception e) {
			log.error(new StringBuilder("ID No encontrada: ").append(e.getMessage()).toString());
		}
		if (resp.getStatusCode().is2xxSuccessful()) {
			return (List<Integer>) resp.getBody();
		}
		return null;

	}

	@Override
	public List<PruebaTecnicaProducto> getListaProductosSimilares(List<Integer> listaProductosId) {
		String url = "";
		List<PruebaTecnicaProducto> listaProductos = new ArrayList<>();
		for (Integer id : listaProductosId) {
			url = new StringBuilder(PruebaTecnicaConstants.URL_PETICION_PRODUCTOS).append("/").append(id).toString();
			ResponseEntity<PruebaTecnicaProducto> resp = null;
			try {
				resp = restTemplate.exchange(url, HttpMethod.GET, null, PruebaTecnicaProducto.class);

			}
			catch (Exception e) {
				log.error(e.getMessage());
			}
			if (resp != null && resp.getStatusCode().is2xxSuccessful()) {
				listaProductos.add((PruebaTecnicaProducto) resp.getBody());
			}
			else {
				log.warn(new StringBuilder("El producto con la id: ").append(id).append(" no ha sido encontrado.")
						.toString());
			}
		}
		return listaProductos;
	}

}
