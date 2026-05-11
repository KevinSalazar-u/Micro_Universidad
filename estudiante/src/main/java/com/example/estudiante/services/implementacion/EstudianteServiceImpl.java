package com.example.estudiante.services.implementacion;

import com.example.estudiante.clients.CursoClient;
import com.example.estudiante.dto.*;
import com.example.estudiante.entities.Estudiante;
import com.example.estudiante.exceptions.RecursoNoEncontradoException;
import com.example.estudiante.repositories.EstudianteRepository;
import com.example.estudiante.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoClient cursoClient;

    @Override
    public List<EstudianteResponseDTO> listar() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Override
    public EstudianteResponseDTO buscarPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con id: " + id));

        return convertirAResponseDTO(estudiante);
    }

    @Override
    public EstudianteConCursoDTO buscarConCurso(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con id: " + id));

        CursoDTO curso = cursoClient.buscarCursoPorId(estudiante.getCursoId());

        return EstudianteConCursoDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .cedula(estudiante.getCedula())
                .email(estudiante.getEmail())
                .curso(curso)
                .build();
    }

    @Override
    public EstudianteResponseDTO guardar(EstudianteRequestDTO estudianteRequestDTO) {
        cursoClient.buscarCursoPorId(estudianteRequestDTO.getCursoId());

        Estudiante estudiante = Estudiante.builder()
                .nombre(estudianteRequestDTO.getNombre())
                .cedula(estudianteRequestDTO.getCedula())
                .email(estudianteRequestDTO.getEmail())
                .cursoId(estudianteRequestDTO.getCursoId())
                .build();

        return convertirAResponseDTO(estudianteRepository.save(estudiante));
    }

    @Override
    public EstudianteResponseDTO actualizar(Long id, EstudianteRequestDTO estudianteRequestDTO) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con id: " + id));

        cursoClient.buscarCursoPorId(estudianteRequestDTO.getCursoId());

        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setCedula(estudianteRequestDTO.getCedula());
        estudiante.setEmail(estudianteRequestDTO.getEmail());
        estudiante.setCursoId(estudianteRequestDTO.getCursoId());

        return convertirAResponseDTO(estudianteRepository.save(estudiante));
    }

    @Override
    public void eliminar(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con id: " + id));

        estudianteRepository.delete(estudiante);
    }

    private EstudianteResponseDTO convertirAResponseDTO(Estudiante estudiante) {
        return EstudianteResponseDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .cedula(estudiante.getCedula())
                .email(estudiante.getEmail())
                .cursoId(estudiante.getCursoId())
                .build();
    }
}