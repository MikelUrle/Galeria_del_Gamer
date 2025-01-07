package com.mikel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="foto")
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ruta")
	private String ruta;

	@ManyToOne
	private Juego juego;

	public Foto(int id, String ruta, Juego juego) {
		super();
		this.id = id;
		this.ruta = ruta;
		this.juego = juego;
	}

	public Foto() {
		super();
		this.id = 0;
		this.ruta = "";
		this.juego = new Juego();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "Foto [id=" + id + ", ruta=" + ruta + ", juego=" + juego + "]";
	}
	
}




