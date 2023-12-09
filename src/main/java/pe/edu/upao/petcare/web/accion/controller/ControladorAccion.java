package pe.edu.upao.petcare.web.accion.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.accion.services.AccionDTO;
import pe.edu.upao.petcare.web.accion.services.AccionServicio;

@RestController
public class ControladorAccion {

    @Autowired
    private AccionServicio accionServicio;

/*

    // Endpoint para completar una tarea y asociarla a una mascota
    @PostMapping("/completarTarea/{idTarea}/{idMascota}")
    public ResponseEntity<AccionDTO> completarTarea(@PathVariable Long idTarea, @PathVariable Long idMascota) {
        try {
            AccionDTO accion = accionServicio.completarTarea(idTarea, idMascota);
            return new ResponseEntity<>(accion, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

}
