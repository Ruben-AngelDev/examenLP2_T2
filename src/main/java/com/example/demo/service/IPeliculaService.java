package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Pelicula;

public interface IPeliculaService {
	
	public List<Pelicula> getAllPelicula();
	public Pelicula createPelicula(Pelicula p);
	public void deletePelicula(Long id);
	public Pelicula getPeliculaById(Long id);
}
