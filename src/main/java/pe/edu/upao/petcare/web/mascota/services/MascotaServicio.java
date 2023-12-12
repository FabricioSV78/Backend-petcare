package pe.edu.upao.petcare.web.mascota.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.cliente.repositories.RepositorioCliente;
//import pe.edu.upao.petcare.web.consejo.services.ConsejoDTO;
//import pe.edu.upao.petcare.web.consejo.services.ConsejoServicio;
import pe.edu.upao.petcare.web.excepciones.*;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.services.LogroDTO;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;
import pe.edu.upao.petcare.web.tipomascota.repositories.RepositorioTipoMascota;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaServicio {

    @Autowired
    private RepositorioMascota repositorioMascota;

    @Autowired
    private RepositorioTipoMascota repositorioTipoMascota;

    @Autowired
    private RepositorioCliente repositorioCliente;



    public Mascota crearPerfilMascota(MascotaDTO mascotaDTO) {
        // Comprobar que el nombre de la mascota no sea nulo ni esté vacío
        if (mascotaDTO.getNombreMascota() == null || mascotaDTO.getNombreMascota().isEmpty()) {
            throw new DatosNoValidosException("El nombre de la mascota no puede ser nulo ni estar vacío");
        }

        // Comprobar que la fecha de nacimiento no sea nula
        if (mascotaDTO.getFechaNMascota() == null) {
            throw new DatosNoValidosException("La fecha de nacimiento de la mascota no puede ser nula");
        }

        // Comprobar que el ID del género no sea nulo antes de buscar
        if (mascotaDTO.getGenero() == null) {
            throw new DatosNoValidosException("El género no puede ser nulo");
        }

        // Validar el género (insensible a mayúsculas/minúsculas)
        String genero = mascotaDTO.getGenero().toLowerCase();
        if (!genero.equals("hembra") && !genero.equals("macho")) {
            throw new DatosNoValidosException("El género debe ser 'Hembra' o 'Macho'");
        }

        // Comprobar que el ID del tipo de mascota no sea nulo antes de buscar
        if (mascotaDTO.getIdTipoMascota() == null) {
            throw new DatosNoValidosException("El tipo de mascota no puede ser nulo");
        }

        TipoMascota tipoMascota = repositorioTipoMascota.findById(mascotaDTO.getIdTipoMascota())
                .orElseThrow(() -> new DatosNoValidosException("Tipo de mascota no encontrado"));

        // Comprobar que el ID del dueño no sea nulo antes de buscar
        if (mascotaDTO.getIdCliente() == null) {
            throw new DatosNoValidosException("El dueño no puede ser nulo");
        }

        Cliente dueno = repositorioCliente.findById(mascotaDTO.getIdCliente())
                .orElseThrow(() -> new DatosNoValidosException("Dueño no encontrado"));

        Mascota mascota = new Mascota();
        mascota.setTipoMascota(tipoMascota);
        mascota.setDueno(dueno);
        mascota.setNombreMascota(mascotaDTO.getNombreMascota());
        mascota.setGenero(genero);  // Establecer el género validado
        mascota.setFechaNMascota(mascotaDTO.getFechaNMascota());
        mascota.setEsterelizado(mascotaDTO.isEsterelizado());
        mascota.setPeso(mascotaDTO.getPeso());

        return repositorioMascota.save(mascota);
    }


    public MascotaDTO obtenerPerfilMascotaConConsejo(Long idMascota) {
        Mascota mascota = repositorioMascota.findById(idMascota)
                .orElseThrow(() -> new DatosNoValidosException("Mascota no encontrada"));

        TipoMascota tipoMascota = mascota.getTipoMascota();
        if (tipoMascota == null) {
            throw new DatosNoValidosException("El tipo de mascota no está asignado");
        }

        MascotaDTO mascotaDTO = new MascotaDTO(
                mascota.getTipoMascota().getIdTipoMascota(),
                mascota.getDueno().getIdCliente(),
                mascota.getNombreMascota(),
                mascota.getFechaNMascota(),
                mascota.getGenero(),
                mascota.isEsterelizado(),
                mascota.getPeso()
                 // Inicialmente no hay consejo, podría ser agregado más tarde
        );


        return mascotaDTO;
    }





    public Mascota modificarPerfilMascota(Long idMascota, MascotaDTO mascotaDTO) {
        if (idMascota == null) {
            throw new IllegalArgumentException("El ID de la mascota no puede ser nulo");
        }

        Mascota mascotaExistente = repositorioMascota.findById(idMascota)
                .orElseThrow(() -> new MascotaNoEncontradaException("Mascota con ID " + idMascota + " no encontrada"));

        if (mascotaDTO.getIdTipoMascota() != null) {
            TipoMascota tipoMascota = repositorioTipoMascota.findById(mascotaDTO.getIdTipoMascota())
                    .orElseThrow(() -> new TipoMascotaNoEncontradoException("Tipo de mascota con ID " + mascotaDTO.getIdTipoMascota() + " no encontrado"));
            mascotaExistente.setTipoMascota(tipoMascota);
        }

        if (mascotaDTO.getIdCliente() != null) {
            Cliente dueno = repositorioCliente.findById(mascotaDTO.getIdCliente())
                    .orElseThrow(() -> new DuenoNoEncontradoException("Dueño con ID " + mascotaDTO.getIdCliente() + " no encontrado"));
            mascotaExistente.setDueno(dueno);
        }

        if (mascotaDTO.getNombreMascota() != null && !mascotaDTO.getNombreMascota().trim().isEmpty()) {
            mascotaExistente.setNombreMascota(mascotaDTO.getNombreMascota());
        } else if (mascotaDTO.getNombreMascota() != null) {
            throw new DatosNoValidosException("El nombre de la mascota no puede estar vacío");
        }

        if (mascotaDTO.getFechaNMascota() != null) {
            mascotaExistente.setFechaNMascota(mascotaDTO.getFechaNMascota());
        }

        if (mascotaDTO.getGenero() != null) {
            mascotaExistente.setGenero(mascotaDTO.getGenero());
        }

        // Asumiendo que 'isEsterelizado' y 'getPeso' no pueden ser nulos.
        // Si pueden serlo, debes agregar comprobaciones nulas aquí también.
        mascotaExistente.setEsterelizado(mascotaDTO.isEsterelizado());
        mascotaExistente.setPeso(mascotaDTO.getPeso());

        return repositorioMascota.save(mascotaExistente);
    }



    public Mascota obtenerMascotaPorNombre(String nombreMascota) {
        Mascota mascota = repositorioMascota.findByNombreMascota(nombreMascota);
        if (mascota == null) {
            throw new MascotaNoEncontradaException("La mascota no se encontró en la base de datos.");
        }
        return mascota;
    }

    public List<LogroDTO> obtenerLogrosCompletadosDeMascota(Long idMascota) {
        Mascota mascota = repositorioMascota.findById(idMascota)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con id: " + idMascota));

        return mascota.getLogros().stream()
                .filter(Logro::isCompletado)
                .map(this::convertirALogroDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir un Logro en LogroMapper
    private LogroDTO convertirALogroDTO(Logro logro) {
        // Aquí debes mapear todos los atributos necesarios del Logro al LogroMapper
        LogroDTO logroDTO = new LogroDTO(

                logro.getNombreLogro(),
                logro.getDescripcion()

        );
        return logroDTO;
    }

    public int obtenerFelicidadMascota(Long idMascota) {
        Mascota mascota = repositorioMascota.findById(idMascota)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con id: " + idMascota));
        return mascota.getFelicidad();
    }







}









