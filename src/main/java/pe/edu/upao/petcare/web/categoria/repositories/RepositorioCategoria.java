package pe.edu.upao.petcare.web.categoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombreCategoria(String nombreCategoria);


    // Método para encontrar tareas por categoría




}

