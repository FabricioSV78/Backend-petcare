package pe.edu.upao.petcare.web.perfilclinico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upao.petcare.web.perfilclinico.models.PerfilClinico;


public interface RepositorioPerfilC extends JpaRepository<PerfilClinico, Long> {

}
