package com.co.tiendaonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.tiendaonline.entity.Clientes;

public interface IClientesRepo extends JpaRepository<Clientes, Long> {

}
