package pe.edu.petcare.web.HistorialMedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.petcare.web.HistorialMedico.models.HistorialMedico;
import pe.edu.petcare.web.HistorialMedico.services.HistorialMapper;
import pe.edu.petcare.web.HistorialMedico.services.HistorialServicio;
import pe.edu.petcare.web.Mascota.services.MascotaServicio;

import java.util.Optional;

@RestController
public class ControladorHistorial {

    @Autowired
    private HistorialServicio historialServicio;


    @PostMapping("/crear_historial")
    public ResponseEntity<String> crearHistorialMedico(@RequestBody HistorialMapper historialMapper) {
        try {
            HistorialMedico historialMedico = historialServicio.crearHistorialMedico(historialMapper);
            return new ResponseEntity<>("Historial médico creado exitosamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /*@GetMapping("/mostrarHistorial/{id_mascota}")
    public ResponseEntity<HistorialMedico> mostrarHistorial(@PathVariable Long id_mascota) {
        HistorialMedico historialMedico = historialServicio.mostrarHistorial(id_mascota);

        if (historialMedico != null) {
            return new ResponseEntity<>(historialMedico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/


}
