package com.example.estudiante.clients;

import com.example.estudiante.dto.CursoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "curso-service")
public interface CursoClient {

    @GetMapping("/api/cursos/{id}")
    CursoDTO buscarCursoPorId(@PathVariable Long id);
}