package pe.edu.petcare.web.Mascota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.petcare.web.Mascota.models.Mascota;
import pe.edu.petcare.web.Mascota.services.MascotaMapper;
import pe.edu.petcare.web.Mascota.services.MascotaNoEncontradaException;
import pe.edu.petcare.web.Mascota.services.MascotaServicio;

@RestController
public class ControladorMascota {
    @Autowired
    private MascotaServicio mascotaServicio;

    @PostMapping("/crearPerfil")
    public ResponseEntity<String> crearPerfilMascota(@RequestBody MascotaMapper mascotaMapper) {
        try {
            Mascota mascotaCreada = mascotaServicio.crearPerfilMascota(mascotaMapper);
            if (mascotaCreada != null) {
                return new ResponseEntity<>("Perfil de mascota creado", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("No se pudo crear el perfil de mascota", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificarPerfil/{id_mascota}")
    public ResponseEntity<String> modificarPerfilMascota(
            @PathVariable Long id_mascota,
            @RequestBody MascotaMapper mascotaMapper) {

        try {
            Mascota mascotaModificada = mascotaServicio.modificarPerfilMascota(id_mascota, mascotaMapper);
            if (mascotaModificada != null) {
                return new ResponseEntity<>("Perfil de mascota modificado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se pudo modificar el perfil de mascota", HttpStatus.BAD_REQUEST);
            }
        } catch (MascotaNoEncontradaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}




