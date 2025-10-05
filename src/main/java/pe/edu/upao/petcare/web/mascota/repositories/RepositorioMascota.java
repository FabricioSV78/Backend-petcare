package pe.edu.upao.petcare.web.mascota.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.mascota.models.Mascota;

import java.util.List;

@Repository
public interface RepositorioMascota extends JpaRepository<Mascota, Long> {
    Mascota findByNombreMascota(String nombreMascota);

    Mascota findByIdMascota(Long idMascota);

    @Query("SELECT l FROM Logro l WHERE l IN (SELECT t.logro FROM Tarea t WHERE t IN (SELECT a.tarea FROM Accion a WHERE a.mascota.idMascota = :idMascota))")
    List<Logro> findLogrosByMascotaId(@Param("idMascota") Long idMascota);



}

