package com.example.estudiante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteResponseDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private String email;
    private Long cursoId;
}