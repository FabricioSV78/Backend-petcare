package pe.edu.upao.petcare.web.tipomascota.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

@Repository
public interface RepositorioTipoMascota extends JpaRepository<TipoMascota, Long> {


}

