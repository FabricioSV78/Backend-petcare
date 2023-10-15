package pe.edu.petcare.web.Mascota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pe.edu.petcare.web.Mascota.models.Mascota;
import pe.edu.petcare.web.Mascota.repositories.RepositorioMascota;

import java.util.Optional;

@Service
public class MascotaServicio {

    @Autowired
    private RepositorioMascota repositorioMascota;


    public Mascota crearPerfilMascota(MascotaMapper mascotaMapper) {

        Mascota mascota = new Mascota();

        mascota.setTipoMascota(mascotaMapper.getTipoMascota());
        mascota.setNombreMascota(mascotaMapper.getNombreMascota());
        mascota.setFechaNMascota(mascotaMapper.getFechaNMascota());
        mascota.setGenero(mascotaMapper.getGenero());
        mascota.setEsterelizado(mascotaMapper.getEsterelizado());
        mascota.setPeso(mascotaMapper.getPeso());

        if (mascota.getNombreMascota().isEmpty() || mascota.getFechaNMascota()==null) {
            throw new IllegalArgumentException("no puede aver valores nulos");
        }
        if (!mascota.getGenero().equals("Hembra") && !mascota.getGenero().equals("Macho")) {
            throw new IllegalArgumentException("El género de la mascota debe ser 'Hembra' o 'Macho'");
        }

        // Luego, guardamos la mascota en la base de datos
        return repositorioMascota.save(mascota);
    }

    public Mascota modificarPerfilMascota(Long id_mascota, MascotaMapper mascotaMapper) {
        Optional<Mascota> mascotaExistenteOptional = repositorioMascota.findById(id_mascota);

        if (mascotaExistenteOptional.isPresent()) {
            Mascota mascotaExistente = mascotaExistenteOptional.get();

            if (mascotaMapper.getTipoMascota() != null) {
                mascotaExistente.setTipoMascota(mascotaMapper.getTipoMascota());
            }
            if (mascotaMapper.getNombreMascota() != null) {
                mascotaExistente.setNombreMascota(mascotaMapper.getTipoMascota());
            }
            if (mascotaMapper.getFechaNMascota() != null) {
                mascotaExistente.setFechaNMascota(mascotaMapper.getFechaNMascota());
            }
            if (mascotaMapper.getGenero() != null) {
                mascotaExistente.setGenero(mascotaMapper.getGenero());
            }
            if (mascotaMapper.getEsterelizado() != null) {
                mascotaExistente.setEsterelizado(mascotaMapper.getEsterelizado());
            }
            if (mascotaMapper.getPeso() != null) {
                mascotaExistente.setPeso(mascotaMapper.getPeso());
            }

            try {
                // Guardar la mascota modificada en la base de datos
                return repositorioMascota.save(mascotaExistente);
            } catch (DataIntegrityViolationException ex) {
                // Manejar errores de base de datos, por ejemplo, campos duplicados
                throw new MascotaNoEncontradaException("La mascota ya existe en la base de datos.");
            }
        } else {
            // Manejar el caso en el que la mascota no se encuentra
            throw new MascotaNoEncontradaException("La mascota no se encontró en la base de datos.");
        }
    }


    public Mascota obtenerMascotaPorNombre(String nombreMascota) {
        return repositorioMascota.findByNombreMascota(nombreMascota);
    }


}

