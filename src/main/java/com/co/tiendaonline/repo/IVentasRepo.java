package com.co.tiendaonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.tiendaonline.entity.Ventas;

public interface IVentasRepo extends JpaRepository<Ventas, Long> {

}
