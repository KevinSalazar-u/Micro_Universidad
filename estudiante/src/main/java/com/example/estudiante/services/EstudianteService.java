package com.example.estudiante.services;

import com.example.estudiante.dto.*;

import java.util.List;

public interface EstudianteService {

    List<EstudianteResponseDTO> listar();

    EstudianteResponseDTO buscarPorId(Long id);

    EstudianteConCursoDTO buscarConCurso(Long id);

    EstudianteResponseDTO guardar(EstudianteRequestDTO estudianteRequestDTO);

    EstudianteResponseDTO actualizar(Long id, EstudianteRequestDTO estudianteRequestDTO);

    void eliminar(Long id);
}