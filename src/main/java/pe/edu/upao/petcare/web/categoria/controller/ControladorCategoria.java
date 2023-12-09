package pe.edu.upao.petcare.web.categoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.petcare.web.categoria.services.CategoriaConProgresoDTO;
import pe.edu.upao.petcare.web.categoria.services.CategoriaServicio;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.List;

@RestController
public class ControladorCategoria {

    @Autowired
    private CategoriaServicio categoriaServicio;
/*
    @GetMapping("/progreso")
    public ResponseEntity<List<CategoriaConProgresoDTO>> obtenerProgresoTareasPorCategoria() {
        List<CategoriaConProgresoDTO> progresoCategorias = categoriaServicio.obtenerProgresoTareasPorCategoria();
        if (progresoCategorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(progresoCategorias, HttpStatus.OK);
    }

*/

}
