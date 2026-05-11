package com.example.estudiante.controllers;

import com.example.estudiante.dto.*;
import com.example.estudiante.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public List<EstudianteResponseDTO> listar() {
        return estudianteService.listar();
    }

    @GetMapping("/{id}")
    public EstudianteResponseDTO buscarPorId(@PathVariable Long id) {
        return estudianteService.buscarPorId(id);
    }

    @GetMapping("/{id}/curso")
    public EstudianteConCursoDTO buscarConCurso(@PathVariable Long id) {
        return estudianteService.buscarConCurso(id);
    }

    @PostMapping
    public EstudianteResponseDTO guardar(@RequestBody EstudianteRequestDTO estudianteRequestDTO) {
        return estudianteService.guardar(estudianteRequestDTO);
    }

    @PutMapping("/{id}")
    public EstudianteResponseDTO actualizar(@PathVariable Long id, @RequestBody EstudianteRequestDTO estudianteRequestDTO) {
        return estudianteService.actualizar(id, estudianteRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "Estudiante eliminado correctamente";
    }
}