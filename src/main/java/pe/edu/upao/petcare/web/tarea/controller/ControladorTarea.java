package pe.edu.upao.petcare.web.tarea.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.petcare.web.categoria.services.CategoriaConProgresoDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaConTareasDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaServicio;
import pe.edu.upao.petcare.web.logro.services.LogroDTO;
import pe.edu.upao.petcare.web.logro.services.LogroServicio;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.services.TareaConProgresoDTO;
import pe.edu.upao.petcare.web.tarea.services.TareaDTO;
import pe.edu.upao.petcare.web.tarea.services.TareaServicio;

import java.util.List;

@RestController
public class ControladorTarea {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private LogroServicio logroServicio;

    @Autowired
    private TareaServicio tareaServicio;

    // Este método lista todas las categorías
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaServicio.listarCategorias();
        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Este método lista los logros basado en una categoría
    @GetMapping("/logros/{categoriaId}")
    public ResponseEntity<List<LogroDTO>> listarLogrosPorCategoria(@PathVariable Long categoriaId) {
        List<LogroDTO> logros = logroServicio.listarLogrosPorCategoria(categoriaId);
        if (logros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logros, HttpStatus.OK);
    }

    @PostMapping("/tareas/completar/{tareaId}/mascota/{mascotaId}")
    public ResponseEntity<?> completarTarea(@PathVariable Long tareaId, @PathVariable Long mascotaId) {
        try {
            tareaServicio.completarTarea(tareaId, mascotaId);
            return new ResponseEntity<>("Tarea completada con éxito", HttpStatus.OK);
        } catch (EntityNotFoundException enf) {
            return new ResponseEntity<>("No se encontró la entidad: " + enf.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al completar la tarea: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Endpoint para obtener tareas por ID de categoría
    @GetMapping("/{idCategoria}/tareas")
    public ResponseEntity<List<TareaDTO>> obtenerTareasPorIdCategoria(@PathVariable Long idCategoria) {
        try {
            List<TareaDTO> tareasDTO = tareaServicio.obtenerTareasPorIdCategoria(idCategoria);
            return ResponseEntity.ok(tareasDTO);
        } catch (Exception e) {
            // Manejo de la excepción, como una categoría no encontrada
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}
