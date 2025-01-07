package com.mikel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="juegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="valoracion")
	private int valoracion;
	
	@ManyToOne
	private Estado estado;
	
	@ManyToOne
	private Categoria categoria;
	
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="descripcion")
	private String descripcion;

	public Juego(int id, String nombre, int valoracion, Estado estado, Categoria categoria, String foto,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.valoracion = valoracion;
		this.estado = estado;
		this.categoria = categoria;
		this.foto = foto;
		this.descripcion = descripcion;
	}

	public Juego() {
		super();
		this.id = 0;
		this.nombre = "";
		this.valoracion = 0;
		this.estado = new Estado();
		this.categoria = new Categoria();
		this.foto = "";
		this.descripcion = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", valoracion=" + valoracion + ", estado=" + estado
				+ ", categoria=" + categoria + ", foto=" + foto + ", descripcion=" + descripcion + "]";
	}
	
}

