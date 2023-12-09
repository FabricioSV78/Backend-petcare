package pe.edu.upao.petcare.web.perfilclinico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.perfilclinico.models.PerfilClinico;
import pe.edu.upao.petcare.web.perfilclinico.services.PerfilCDTO;
import pe.edu.upao.petcare.web.perfilclinico.services.PerfilCServicio;

@RestController
public class ControladorPerfilClinico {

    @Autowired
    private PerfilCServicio perfilCServicio;


    @PostMapping("/historial")
    public ResponseEntity<?> crearHistorialMedico(@RequestBody PerfilCDTO perfilCDTO) {
        try {
            PerfilClinico perfilClinico = perfilCServicio.crearPerfilClinico(perfilCDTO);
            return new ResponseEntity<>(perfilClinico, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrarPerfilClinico/{id_mascota}")
    public ResponseEntity<PerfilCDTO> obtenerPerfilClinico(@PathVariable Long id) {
        try {
            PerfilCDTO perfilCDTO = perfilCServicio.obtenerPerfilClinico(id);
            return ResponseEntity.ok(perfilCDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // O manejar el error de manera más específica
        }
    }


}


