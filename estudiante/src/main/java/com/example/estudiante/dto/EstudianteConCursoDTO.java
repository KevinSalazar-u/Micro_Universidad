package com.example.estudiante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteConCursoDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private String email;
    private CursoDTO curso;
}