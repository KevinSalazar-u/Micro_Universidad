package com.example.estudiante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {

    private Long id;
    private String nombre;
    private String modalidad;
    private Integer duracionMeses;
}