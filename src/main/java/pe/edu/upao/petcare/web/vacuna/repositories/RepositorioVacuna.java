package pe.edu.upao.petcare.web.vacuna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upao.petcare.web.vacuna.models.Vacuna;

public interface RepositorioVacuna extends JpaRepository<Vacuna, Long> {
    Vacuna findByNombreVacuna(String nombreVacuna);
}
