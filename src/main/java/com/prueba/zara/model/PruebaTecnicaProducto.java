package com.prueba.zara.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;


@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PruebaTecnicaProducto {
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("price")
	private BigDecimal price;
	
	@JsonProperty("availability")
	private Boolean availability;


	@Size(min = 1)
	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PruebaTecnicaProducto pruebaTecnicaProducto = (PruebaTecnicaProducto) o;
		return Objects.equals(this.id, pruebaTecnicaProducto.id) && Objects.equals(this.name, pruebaTecnicaProducto.name)
				&& Objects.equals(this.price, pruebaTecnicaProducto.price)
				&& Objects.equals(this.availability, pruebaTecnicaProducto.availability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, availability);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse200 {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    availability: ").append(toIndentedString(availability)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
