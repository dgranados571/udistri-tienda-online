package com.co.tiendaonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.tiendaonline.entity.Categorias;

public interface ICategoriasRepo extends JpaRepository<Categorias, Long> {

}
