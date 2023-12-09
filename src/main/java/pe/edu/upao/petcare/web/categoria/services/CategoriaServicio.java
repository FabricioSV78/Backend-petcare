package pe.edu.upao.petcare.web.categoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.categoria.repositories.RepositorioCategoria;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;
import pe.edu.upao.petcare.web.tarea.services.TareaConProgresoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServicio {

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Autowired
    private RepositorioLogro repositorioLogro;

    @Autowired
    private RepositorioTarea repositorioTarea;

    public List<CategoriaDTO> listarCategorias() {
        return repositorioCategoria.findAll()
                .stream()
                .map(categoria -> new CategoriaDTO(categoria.getNombreCategoria()))
                .collect(Collectors.toList());
    }




}
