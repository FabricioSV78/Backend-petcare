package pe.edu.upao.petcare.web.accion.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.accion.services.AccionDTO;
import pe.edu.upao.petcare.web.accion.services.AccionServicio;

import java.util.List;

@RestController
public class ControladorAccion {

    @Autowired
    private AccionServicio accionServicio;

    @GetMapping("/{idTarea}/acciones/estado")
    public ResponseEntity<?> obtenerEstadoPorIdTarea(@PathVariable Long idTarea) {
        List<Accion.AccionEstado> estados = accionServicio.obtenerEstadoPorIdTarea(idTarea);
        if (estados.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(estados);
        }
    }

}
