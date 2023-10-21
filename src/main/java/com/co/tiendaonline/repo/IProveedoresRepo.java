package com.co.tiendaonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.tiendaonline.entity.Proveedores;

public interface IProveedoresRepo extends JpaRepository<Proveedores, Long> {

}
