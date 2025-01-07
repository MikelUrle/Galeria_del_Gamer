package com.mikel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mikel.model.Categoria;
import com.mikel.model.Juego;

import jakarta.transaction.Transactional;

@Repository
public interface JuegosRepository extends JpaRepository<Juego, Integer> {

	List<Juego> findByCategoriaId(int categoriaId);
	
	List<Juego> findById(int juegoId);
	
    @Transactional
    @Modifying
    @Query("UPDATE Juego j SET j.estado.id = :estadoId WHERE j.id = :juegoId")
    int actualizarEstado(@Param("juegoId") int juegoId, @Param("estadoId") int estadoId);
    
    @Transactional
    @Modifying
    @Query("UPDATE Juego j SET j.nombre = :nombre, j.descripcion = :descripcion, j.valoracion = :valoracion WHERE j.id = :juegoId")
    int actualizarJuego(@Param("juegoId") int juegoId, 
                        @Param("nombre") String nombre, 
                        @Param("descripcion") String descripcion, 
                        @Param("valoracion") int valoracion);

}


