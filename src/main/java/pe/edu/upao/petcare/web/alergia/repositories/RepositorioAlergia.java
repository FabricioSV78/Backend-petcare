package pe.edu.upao.petcare.web.alergia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upao.petcare.web.alergia.models.Alergia;

public interface RepositorioAlergia extends JpaRepository<Alergia, Long> {
    Alergia findByNombreAlergia(String nombreAlergia);
}
