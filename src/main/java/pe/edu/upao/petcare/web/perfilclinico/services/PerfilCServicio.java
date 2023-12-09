package pe.edu.upao.petcare.web.perfilclinico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.alergia.models.Alergia;
import pe.edu.upao.petcare.web.alergia.repositories.RepositorioAlergia;
import pe.edu.upao.petcare.web.enfermedad.models.Enfermedad;
import pe.edu.upao.petcare.web.enfermedad.repositories.RepositorioEnfermedad;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.mascota.services.MascotaServicio;
import pe.edu.upao.petcare.web.perfilclinico.models.PerfilClinico;
import pe.edu.upao.petcare.web.perfilclinico.repositories.RepositorioPerfilC;
import pe.edu.upao.petcare.web.vacuna.models.Vacuna;
import pe.edu.upao.petcare.web.vacuna.repositories.RepositorioVacuna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilCServicio {
    @Autowired
    private RepositorioPerfilC repositorioPerfilC;
    @Autowired
    private RepositorioMascota repositorioMascota;
    @Autowired
    private MascotaServicio mascotaServicio;
    @Autowired
    private RepositorioVacuna repositorioVacuna;
    @Autowired
    private RepositorioEnfermedad repositorioEnfermedad;
    @Autowired
    private RepositorioAlergia repositorioAlergia;


    public PerfilClinico crearPerfilClinico(PerfilCDTO perfilCDTO) {
        if (perfilCDTO == null) {
            throw new IllegalArgumentException("Los datos del historial médico no pueden ser nulos.");
        }

        Mascota mascotaAsociada = mascotaServicio.obtenerMascotaPorNombre(perfilCDTO.getNombreMascota());
        if (mascotaAsociada == null) {
            throw new IllegalArgumentException("La mascota asociada no existe.");
        }

        PerfilClinico perfilClinico = new PerfilClinico();
        perfilClinico.setMascota(mascotaAsociada);
        perfilClinico.setFecha_UltimaDesparacitacion(perfilCDTO.getFechaUltimaDesparasitacion());
        perfilClinico.setFecha_UltimaVacuna(perfilCDTO.getFechaUltimaVacuna());
        perfilClinico.setFecha_VisitaVeterinario(perfilCDTO.getFechaVisitaVeterinario());


        List<Vacuna> vacunas = obtenerVacunasDesdePerfilCDTO(perfilCDTO);
        List<Enfermedad> enfermedades = obtenerEnfermedadesDesdePerfilCDTO(perfilCDTO);
        List<Alergia> alergias = obtenerAlergiasDesdePerfilCDTO(perfilCDTO);

        perfilClinico.setVacunas(vacunas);
        perfilClinico.setEnfermedades(enfermedades);
        perfilClinico.setAlergias(alergias);

        // Guardar el historial médico
        return repositorioPerfilC.save(perfilClinico);
    }

    public PerfilCDTO obtenerPerfilClinico(Long idPerfilC) {
        // Usar el ID del PerfilClinico como identificador
        Optional<PerfilClinico> perfilClinicoOpt = repositorioPerfilC.findById(idPerfilC);

        if (!perfilClinicoOpt.isPresent()) {
            throw new IllegalArgumentException("El perfil clínico no existe para el identificador proporcionado.");
        }

        PerfilClinico perfilClinico = perfilClinicoOpt.get();

        // Crear una instancia de PerfilCDTO y mapear los datos desde PerfilClinico
        PerfilCDTO perfilCDTO = new PerfilCDTO();
        perfilCDTO.setNombreMascota(perfilClinico.getMascota().getNombreMascota()); // Suponiendo que Mascota tiene un campo nombre
        perfilCDTO.setFechaUltimaDesparasitacion(perfilClinico.getFecha_UltimaDesparacitacion());
        perfilCDTO.setFechaUltimaVacuna(perfilClinico.getFecha_UltimaVacuna());
        perfilCDTO.setFechaVisitaVeterinario(perfilClinico.getFecha_VisitaVeterinario());
        // Agregar mapeos adicionales para otros campos relevantes

        return perfilCDTO;
    }




    private List<Vacuna> obtenerVacunasDesdePerfilCDTO(PerfilCDTO perfilCDTO) {
        List<Vacuna> vacunas = new ArrayList<>();
        for (String nombreVacuna : perfilCDTO.getNombresVacunas()) {
            Vacuna vacuna = repositorioVacuna.findByNombreVacuna(nombreVacuna);
            if (vacuna == null) {
                // Si no existe la vacuna, puedes crear una nueva instancia aquí
                // y configurar más atributos si es necesario
                vacuna = new Vacuna();
                vacuna.setNombreVacuna(nombreVacuna);

            }
            vacunas.add(vacuna);
        }
        return vacunas;
    }

    private List<Enfermedad> obtenerEnfermedadesDesdePerfilCDTO(PerfilCDTO perfilCDTO) {
        List<Enfermedad> enfermedades = new ArrayList<>();
        for (String nombreEnfermedad : perfilCDTO.getNombresEnfermedades()) {
            Enfermedad enfermedad = repositorioEnfermedad.findByNombreEnfermedad(nombreEnfermedad);
            if (enfermedad == null) {
                // Si no existe la vacuna, puedes crear una nueva instancia aquí
                // y configurar más atributos si es necesario
                enfermedad = new Enfermedad();
                enfermedad.setNombreEnfermedad(nombreEnfermedad);

            }
            enfermedades.add(enfermedad);
        }
        return enfermedades;
    }

    private List<Alergia> obtenerAlergiasDesdePerfilCDTO(PerfilCDTO perfilCDTO) {
        List<Alergia> alergias = new ArrayList<>();
        for (String nombreAlergia : perfilCDTO.getNombresAlergias()) {
            Alergia alergia = repositorioAlergia.findByNombreAlergia(nombreAlergia);
            if (alergia == null) {
                // Si no existe la vacuna, puedes crear una nueva instancia aquí
                // y configurar más atributos si es necesario
                alergia = new Alergia();
                alergia.setNombreAlergia(nombreAlergia);

            }
            alergias.add(alergia);
        }
        return alergias;
    }







}




