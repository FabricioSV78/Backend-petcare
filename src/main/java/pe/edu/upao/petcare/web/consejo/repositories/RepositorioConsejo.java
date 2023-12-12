package pe.edu.upao.petcare.web.consejo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioConsejo extends JpaRepository<Consejo, Long> {



}

