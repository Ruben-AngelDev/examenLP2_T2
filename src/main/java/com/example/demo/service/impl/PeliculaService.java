package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;
import com.example.demo.service.IPeliculaService;

@Service
public class PeliculaService implements IPeliculaService {
	
	@Autowired
	private PeliculaRepository peliRepository;

	@Override
	public List<Pelicula> getAllPelicula() {
		return this.peliRepository.findAll();
	}

	@Override
	public Pelicula createPelicula(Pelicula p) {
		return this.peliRepository.save(p);
	}

	@Override
	public void deletePelicula(Long id) {
		this.peliRepository.deleteById(id);
		
	}

	@Override
	public Pelicula getPeliculaById(Long id) {

		return this.peliRepository.findById(id).orElse(null);
	}

}
