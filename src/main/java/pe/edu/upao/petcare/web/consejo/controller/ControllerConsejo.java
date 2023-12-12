package pe.edu.upao.petcare.web.consejo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.consejo.services.ConsejoServicio;

@RestController
public class ControllerConsejo {


    @Autowired
    private ConsejoServicio consejoService;

    @GetMapping("/consejo/{id}")
    public ResponseEntity<Consejo> mostrarConsejoPorId(@PathVariable Long id) {
        return consejoService.obtenerConsejoPorId(id)
                .map(consejo -> ResponseEntity.ok(consejo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
