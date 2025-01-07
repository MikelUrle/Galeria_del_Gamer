package com.mikel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="logro")
public class Logro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="texto")
	private String texto;
	
	@Column(name="estado")
	private int estado;

	@ManyToOne
	private Juego juego;

	public Logro(int id, String texto, int estado, Juego juego) {
		super();
		this.id = id;
		this.texto = texto;
		this.estado = estado;
		this.juego = juego;
	}

	public Logro() {
		super();
		this.id = 0;
		this.texto = "";
		this.estado = 0;
		this.juego = new Juego();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "Logro [id=" + id + ", texto=" + texto + ", estado=" + estado + ", juego=" + juego + "]";
	}
	
}



