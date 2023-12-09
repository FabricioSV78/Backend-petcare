package pe.edu.upao.petcare.web.tipomascota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;
import pe.edu.upao.petcare.web.tipomascota.services.TMascotaServicio;
import pe.edu.upao.petcare.web.tipomascota.services.TMascotaDTO;

@RestController
public class ControladorTMascota {
    @Autowired
    private TMascotaServicio tMascotaServicio;

    @PostMapping("/tipoM")
    public ResponseEntity<?> crearTipoMascota(@RequestBody TMascotaDTO tMascotaDTO) {
        try {
            TipoMascota tipoMascota = tMascotaServicio.crearTipoMascota(tMascotaDTO);
            if (tipoMascota != null) {
                return new ResponseEntity<>(tipoMascota, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

}




