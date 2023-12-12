package pe.edu.upao.petcare.web.mascota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.excepciones.DatosNoValidosException;
import pe.edu.upao.petcare.web.logro.services.LogroDTO;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.services.MascotaDTO;
import pe.edu.upao.petcare.web.excepciones.MascotaNoEncontradaException;
import pe.edu.upao.petcare.web.mascota.services.MascotaServicio;
import pe.edu.upao.petcare.web.tarea.services.TareaServicio;

import java.util.List;

@RestController
public class ControladorMascota {
    @Autowired
    private MascotaServicio mascotaServicio;
    @Autowired
    private TareaServicio tareaServicio;
    @Autowired
    private LogroServicio logroServicio;



    @PostMapping("/mascotas")
    public ResponseEntity<?> crearPerfilMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota mascotaCreada = mascotaServicio.crearPerfilMascota(mascotaDTO);
            return new ResponseEntity<>(mascotaCreada, HttpStatus.CREATED);
        } catch (DatosNoValidosException e) {
            // Aquí puedes manejar excepciones específicas y enviar un mensaje adecuado
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Captura cualquier otra excepción no manejada
            return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idMascota}/obtenerPerfil")
    public ResponseEntity<?> obtenerPerfilMascotaConConsejo(@PathVariable Long idMascota) {
        try {
            MascotaDTO mascotaDTO = mascotaServicio.obtenerPerfilMascotaConConsejo(idMascota);
            return ResponseEntity.ok(mascotaDTO);
        } catch (DatosNoValidosException e) {
            // Aquí capturas la excepción personalizada y envías un mensaje de error adecuado
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otra excepción no manejada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado.");
        }
    }

    @PutMapping("/mascotas/{idMascota}") // Asegúrate de que el nombre de la variable coincida aquí.
    public ResponseEntity<?> modificarPerfilMascota(
            @PathVariable("idMascota") Long idMascota, // Añade el nombre de la variable en la anotación si es diferente al nombre del parámetro.
            @RequestBody MascotaDTO mascotaDTO) {

        try {
            Mascota mascotaModificada = mascotaServicio.modificarPerfilMascota(idMascota, mascotaDTO);
            return new ResponseEntity<>(mascotaModificada, HttpStatus.OK);
        } catch (MascotaNoEncontradaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // Es una buena práctica enviar el mensaje de la excepción al cliente.
        } catch (Exception e) {
            // Es recomendable manejar todas las excepciones potenciales para evitar que detalles del sistema se filtren.
            return new ResponseEntity<>("Ocurrió un error inesperado al modificar el perfil de la mascota.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idMascota}/logros/completados")
    public ResponseEntity<List<LogroDTO>> obtenerLogrosCompletados(@PathVariable Long idMascota) {
        List<LogroDTO> logrosCompletados = mascotaServicio.obtenerLogrosCompletadosDeMascota(idMascota);
        return ResponseEntity.ok(logrosCompletados);
    }

    @GetMapping("/{idMascota}/felicidad")
    public ResponseEntity<Integer> obtenerFelicidadMascota(@PathVariable Long idMascota) {
        int felicidad = mascotaServicio.obtenerFelicidadMascota(idMascota);
        return ResponseEntity.ok(felicidad);
    }


}




