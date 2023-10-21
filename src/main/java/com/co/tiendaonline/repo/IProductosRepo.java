package com.co.tiendaonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.tiendaonline.entity.Productos;

public interface IProductosRepo extends JpaRepository<Productos, Long> {

}
