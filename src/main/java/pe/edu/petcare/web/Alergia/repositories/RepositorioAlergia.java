package pe.edu.petcare.web.Alergia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.petcare.web.Alergia.models.Alergia;
import pe.edu.petcare.web.Enfermedad.models.Enfermedad;

public interface RepositorioAlergia extends JpaRepository<Alergia, Long> {
    Alergia findByNombreAlergia(String nombreAlergia);
}
