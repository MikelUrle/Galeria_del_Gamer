package com.mikel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikel.model.Categoria;
import com.mikel.model.Juego;
import com.mikel.model.Logro;
import com.mikel.model.Estado;
import com.mikel.model.Foto;
import com.mikel.repository.CategoriaRepository;
import com.mikel.repository.EstadoRepository;
import com.mikel.repository.FotoRepository;
import com.mikel.repository.JuegosRepository;
import com.mikel.repository.LogroRepository;

@Controller
public class AdminController {
	
	@Autowired
	private JuegosRepository juegosRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private LogroRepository logroRepo;
	
	@Autowired
	private FotoRepository fotoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@RequestMapping("/jugando")
	public String paginaJugando(Model model) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 1);
		
		return "jugando";
	}
	
	@RequestMapping("/jugandoFiltrado")
	public String paginaJugandoFiltro(Model model, @RequestParam("categoriaId") int categoriaId) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findByCategoriaId(categoriaId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 1);
		
		return "jugando";
	}
	
	@RequestMapping("/completado")
	public String paginaCompletado(Model model) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 2);
		
		return "completado";
	}
	
	@RequestMapping("/completadoFiltrado")
	public String paginaCompletadoFiltro(Model model, @RequestParam("categoriaId") int categoriaId) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findByCategoriaId(categoriaId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 2);
		
		return "completado";
	}
	
	@RequestMapping("/porJugar")
	public String paginaPorJugar(Model model) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 3);
		
		return "porJugar";
	}
	
	
	@RequestMapping("/porJugarFiltrado")
	public String paginaPorJugarFiltro(Model model, @RequestParam("categoriaId") int categoriaId) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findByCategoriaId(categoriaId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		model.addAttribute("lugar", 3);
		
		return "porJugar";
	}
	
	
	@RequestMapping("/infoJuego")
	public String infoJuego(Model model, @RequestParam("juegoId") int juegoId) {
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		String categoriaJuego = juegin.get(0).getCategoria().getNombre();
		
		model.addAttribute("juegoIdVariable", juegoId);
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		int estadoJuego = juegin.get(0).getEstado().getId();

		model.addAttribute("lugar", estadoJuego);
		
		switch (categoriaJuego) {
		case "RPG":
			return "RPG";
		case "Diablo":
			return "Diablo";
		case "Estrategia":
			return "Estrategia";
		case "Shooter":
			return "Shooter";
		case "Souls":
			return "Souls";
		case "SoulsLike":
			return "SoulsLike";

		default:
			return "";
		}
	}
	
	@RequestMapping("/completarLogro")
	public String cambiarLogroACompletado(Model model, @RequestParam("logroId") int id, @RequestParam("juegoId") int juegoId) {
		
		logroRepo.actualizarEstado(id);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		String categoriaJuego = juegin.get(0).getCategoria().getNombre();
		
		model.addAttribute("juegoIdVariable", juegoId);
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		int estadoJuego = juegin.get(0).getEstado().getId();

		model.addAttribute("lugar", estadoJuego);
		
		switch (categoriaJuego) {
		case "RPG":
			return "RPG";
		case "Diablo":
			return "Diablo";
		case "Estrategia":
			return "Estrategia";
		case "Shooter":
			return "Shooter";
		case "Souls":
			return "Souls";
		case "SoulsLike":
			return "SoulsLike";

		default:
			return "";
		}
	}
	
	@RequestMapping("/añadirLogro")
	public String guardarOrdenador(Model model, @RequestParam ("texto") String texto, @RequestParam ("juegoId") int juegoId, @RequestParam ("estado") int estado) {
		 
		Logro logro = new Logro();

		List <Juego> juegoPalId = juegosRepo.findById(juegoId);
		
		logro.setTexto(texto);
		logro.setEstado(estado);
		logro.setJuego(juegoPalId.get(0));
		
		logroRepo.save(logro);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		String categoriaJuego = juegin.get(0).getCategoria().getNombre();
		
		model.addAttribute("juegoIdVariable", juegoId);
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		switch (categoriaJuego) {
		case "RPG":
			return "RPG";
		case "Diablo":
			return "Diablo";
		case "Estrategia":
			return "Estrategia";
		case "Shooter":
			return "Shooter";
		case "Souls":
			return "Souls";
		case "SoulsLike":
			return "SoulsLike";

		default:
			return "";
		}
	}
	
	@RequestMapping("/fotos")
	public String fotos(Model model, @RequestParam ("juegoId") int juegoId) {
		
		model.addAttribute("atr_lista_fotos", fotoRepo.findByJuegoId(juegoId));
		model.addAttribute("juegoIdVariable", juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		return "fotos";
	}
	
	@RequestMapping("/añadirJuegoJugando")
	public String añadirJuegoJugando(Model model) {
		
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		return "añadirJuegoJugando";
	}
	
	@RequestMapping("/añadirJuegoJugandoDatos")
	public String añadirJuegoJugandoDatos(Model model, @RequestParam ("nombre") String Nombre, @RequestParam ("descripcion") String Descripcion, @RequestParam ("valoracion") int Valoracion, @RequestParam ("categoria_id") int CategoriaId, @RequestParam ("estado_id") int Estado, @RequestParam ("foto") String Foto) {
		
		List<Categoria> categorias = categoriaRepo.findAll();
		List<Estado> estados = estadoRepo.findAll();
		
		Juego juego = new Juego();
		
		juego.setNombre(Nombre);
		juego.setDescripcion(Descripcion);
		juego.setValoracion(Valoracion);
		juego.setCategoria(categorias.get(CategoriaId-1));
		juego.setEstado(estados.get(Estado));
		juego.setFoto(Foto);
		
		juegosRepo.save(juego);
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		return "jugando";
	}
	
	@RequestMapping("/completarJuego")
	public String completarJuego(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("nuevoEstadoId") int nuevoEstadoId) {
		
		juegosRepo.actualizarEstado(juegoId, nuevoEstadoId);
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		return "jugando";
	}
	
	@RequestMapping("/jugandoJuego")
	public String jugandoJuego(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("nuevoEstadoId") int nuevoEstadoId) {
		
		juegosRepo.actualizarEstado(juegoId, nuevoEstadoId);
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		return "jugando";
	}
	
	@RequestMapping("/modificarJuego")
	public String modificarJuego(Model model, @RequestParam ("juegoId") int juegoId) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("juegoIdVariable", juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		return "modificarJuego";
	}
	
	@RequestMapping("/modificarJuegoDatos")
	public String modificarJuegoDatos(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("nombre") String nombre, @RequestParam ("descripcion") String descripcion, @RequestParam ("valoracion") int valoracion) {
		
		juegosRepo.actualizarJuego(juegoId, nombre, descripcion, valoracion);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		String categoriaJuego = juegin.get(0).getCategoria().getNombre();
		
		model.addAttribute("juegoIdVariable", juegoId);
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		switch (categoriaJuego) {
		case "RPG":
			return "RPG";
		case "Diablo":
			return "Diablo";
		case "Estrategia":
			return "Estrategia";
		case "Shooter":
			return "Shooter";
		case "Souls":
			return "Souls";
		case "SoulsLike":
			return "SoulsLike";

		default:
			return "";
		}
	}
	
	@RequestMapping("/modificarLogros")
	public String modificarLogros(Model model, @RequestParam ("juegoId") int juegoId) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("juegoIdVariable", juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		return "modificarLogros";
	}
	
	@RequestMapping("/modificarLogrosDatos")
	public String modificarLogrosDatos(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("logroId") int id, @RequestParam ("texto") String texto) {
		
		logroRepo.actualizarTexto(id, texto);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		String categoriaJuego = juegin.get(0).getCategoria().getNombre();
		
		model.addAttribute("juegoIdVariable", juegoId);
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		int estadoJuego = juegin.get(0).getEstado().getId();

		model.addAttribute("lugar", estadoJuego);
		
		switch (categoriaJuego) {
		case "RPG":
			return "RPG";
		case "Diablo":
			return "Diablo";
		case "Estrategia":
			return "Estrategia";
		case "Shooter":
			return "Shooter";
		case "Souls":
			return "Souls";
		case "SoulsLike":
			return "SoulsLike";

		default:
			return "";
		}
	}
	
	@RequestMapping("/borrarLogro")
	public String borrarLogro(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("logroId") int id) {
		
		logroRepo.eliminarLogroPorId(id);
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("juegoIdVariable", juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		return "modificarLogros";
	}
	
	@RequestMapping("/modificarEstadoLogros")
	public String modificarEstadoLogros(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("logroId") int id) {
		
		logroRepo.actualizarEstadoAUno(id);

		model.addAttribute("atr_lista_juegos", juegosRepo.findById(juegoId));
		model.addAttribute("atr_lista_logros", logroRepo.findByJuegoId(juegoId));
		model.addAttribute("juegoIdVariable", juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		int estadoJuego = juegin.get(0).getEstado().getId();
		
		model.addAttribute("lugar", estadoJuego);
		
		return "modificarLogros";
	}
	
	@RequestMapping("/añadirFoto")
	public String añadirFoto(Model model, @RequestParam ("juegoId") int juegoId, @RequestParam ("nombre") String Nombre, @RequestParam ("ruta") String Ruta) {
		
		System.out.println(juegoId);
		
		List<Juego> juegin = juegosRepo.findById(juegoId);
		
		Foto foto = new Foto();
		
		String carpetaFotos = "fotos";
		String RutaFinal = carpetaFotos + "/" + Ruta;
		
		foto.setRuta(RutaFinal);
		foto.setJuego(juegin.get(0));
		
		fotoRepo.save(foto);
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		return "jugando";
	}
	
	@RequestMapping("/volver")
	public String volver(Model model, @RequestParam ("lugar") int lugar) {
		
		model.addAttribute("atr_lista_juegos", juegosRepo.findAll());
		model.addAttribute("atr_lista_categorias", categoriaRepo.findAll());
		
		switch (lugar) {
		case 1: {
			return "jugando";
		}
		case 2: {
			return "completado";
		}
		case 3: {
			return "porJugar";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
	
}

