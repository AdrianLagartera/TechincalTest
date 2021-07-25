package com.prueba.zara.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prueba.zara.model.PruebaTecnicaProducto;

import java.util.List;

@Validated
public interface PruebaTecnicaControllerInterface {

	@Operation(summary = "Similar products", description = "List of similar products to a given one ordered by similarity")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PruebaTecnicaProducto.class)))) })
	@RequestMapping(value = "/product/{productId}/similar", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<PruebaTecnicaProducto>> getProductSimilar(
			@Parameter(in = ParameterIn.PATH, description = "", required = true)
			@PathVariable("productId")
			String productId);

}
