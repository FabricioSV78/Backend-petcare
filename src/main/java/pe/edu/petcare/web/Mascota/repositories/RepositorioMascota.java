package pe.edu.petcare.web.Mascota.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.petcare.web.Mascota.models.Mascota;

@Repository
public interface RepositorioMascota extends JpaRepository<Mascota, Long> {
    Mascota findByNombreMascota(String nombreMascota);

}

