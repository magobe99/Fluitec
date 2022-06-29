package com.fluytec.fluytec.dao;

import com.fluytec.fluytec.domain.Productos;
import org.springframework.data.repository.CrudRepository;


public interface ProductosDao extends CrudRepository<Productos, Integer>{
    
}
