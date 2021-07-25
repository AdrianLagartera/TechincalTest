package com.prueba.zara.interfaces;

import java.util.List;

import com.prueba.zara.model.PruebaTecnicaProducto;

public interface PruebaTecnicaServiceInterface {
	
	List<Integer> getListaProductosID(String id);
	
	List<PruebaTecnicaProducto> getListaProductosSimilares(List<Integer> listaProductosId);
}
