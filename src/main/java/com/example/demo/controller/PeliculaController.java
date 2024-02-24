package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.service.impl.GeneroService;
import com.example.demo.service.impl.PeliculaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/getAllPeliculas")
	public String getAllPeliculas(Model model) {
		List<Pelicula> listPeli = peliService.getAllPelicula();
		model.addAttribute("peliculas", listPeli);
		return "peliList";
	}
    
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGenero());
		return "peliRegister";
	}
	
	@PostMapping("/register")
	public String createPelicula(@RequestParam("nombre") String nombre,
	        @RequestParam("director") String director, @RequestParam("fecha") Date fecha,
	        @RequestParam("idGenero") Long idGenero, Model model) {

	    Genero genero = generoService.getGeneroById(idGenero);
	    Pelicula pelicula = new Pelicula();
	    pelicula.setNomPelicula(nombre);
	    pelicula.setDirector(director);
	    pelicula.setFecha(fecha);

	    pelicula.setIdGenero(genero);
	    peliService.createPelicula(pelicula);
	    List<Pelicula> listPelis = peliService.getAllPelicula();
	    model.addAttribute("peliculas", listPelis);

	    return "peliList";
	}
	
	@GetMapping("/edit/{id}")
	public String getPeliculaByID(@PathVariable Long id, Model model){
		
		Pelicula peli = peliService.getPeliculaById(id);
		model.addAttribute("generos", generoService.getAllGenero());
		model.addAttribute("pelicula",peli);
		return "peliEdit";
		
	}
	@PostMapping("/edit")
	public String editPelicula(@RequestParam("id") Long id, @RequestParam("nombre") String nombre,
			@RequestParam("director") String director, @RequestParam("fecha") Date fecha,@RequestParam("idGenero") Long idGenero,Model model){
		Genero genero = generoService.getGeneroById(idGenero);
		Pelicula peli= peliService.getPeliculaById(id);
		peli.setIdGenero(genero);
		peliService.createPelicula(peli);
		List<Pelicula> listPelis = peliService.getAllPelicula();
		model.addAttribute("peliculas", listPelis);
		return "redirect:/peliculas/getAllPeliculas";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePelicula(@PathVariable Long id, Model model) {
		peliService.deletePelicula(id);
		List<Pelicula> listPelis = peliService.getAllPelicula();
		model.addAttribute("peliculas", listPelis);
		return "redirect:/peliculas/getAllPeliculas";
	}
	
	
	
	}
