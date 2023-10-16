package pe.edu.petcare.web.Vacuna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.petcare.web.Vacuna.models.Vacuna;

public interface RepositorioVacuna extends JpaRepository<Vacuna, Long> {
    Vacuna findByNombreVacuna(String nombreVacuna);
}
