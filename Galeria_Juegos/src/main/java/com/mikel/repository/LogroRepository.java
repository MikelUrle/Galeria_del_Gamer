package com.mikel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mikel.model.Logro;

import jakarta.transaction.Transactional;

@Repository
public interface LogroRepository extends JpaRepository<Logro, Integer> {
	
	List<Logro> findByJuegoId(int juegoId);
	
    @Modifying
    @Transactional
    @Query("UPDATE Logro l SET l.estado = 2 WHERE l.id = :id")
    int actualizarEstado(@Param("id") int id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Logro l SET l.texto = :texto WHERE l.id = :id")
    int actualizarTexto(@Param("id") int id, @Param("texto") String texto);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Logro l WHERE l.id = :id")
    int eliminarLogroPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Logro l SET l.estado = 1 WHERE l.id = :id")
    int actualizarEstadoAUno(@Param("id") int id);

	
}
