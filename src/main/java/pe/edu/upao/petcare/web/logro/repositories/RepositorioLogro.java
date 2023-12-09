package pe.edu.upao.petcare.web.logro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.logro.models.Logro;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioLogro extends JpaRepository<Logro, Long> {




    List<Logro> findByCategoriaIdCategoria(Long idCategoria);

    List<Logro> findByCategoria(Categoria categoria);


    Optional<Logro> findByNombreLogro(String nombreLogro);
}

