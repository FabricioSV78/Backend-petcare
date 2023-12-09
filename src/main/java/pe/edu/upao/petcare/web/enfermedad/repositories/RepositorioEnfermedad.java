package pe.edu.upao.petcare.web.enfermedad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upao.petcare.web.enfermedad.models.Enfermedad;

public interface RepositorioEnfermedad extends JpaRepository<Enfermedad, Long> {
    Enfermedad findByNombreEnfermedad(String nombreEnfermedad);
}
