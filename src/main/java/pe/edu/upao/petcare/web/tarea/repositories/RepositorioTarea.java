package pe.edu.upao.petcare.web.tarea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioTarea extends JpaRepository<Tarea, Long> {

    // Este método asume que tienes una relación establecida entre Acciones y Tareas que te permita hacer esta suma.
    @Query("SELECT SUM(t.puntajeTarea) FROM Tarea t WHERE t.logro.idLogro = :idLogro")
    int sumaPuntajePorLogro(@Param("idLogro") Long idLogro);


    Optional<Tarea> findByNombreTarea(String nombreTarea);

    List<Tarea> findByCategoria_IdCategoria(Long idCategoria);


}

