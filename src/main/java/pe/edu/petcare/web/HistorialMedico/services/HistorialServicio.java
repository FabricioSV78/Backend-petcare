package pe.edu.petcare.web.HistorialMedico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petcare.web.Alergia.models.Alergia;
import pe.edu.petcare.web.Alergia.repositories.RepositorioAlergia;
import pe.edu.petcare.web.Enfermedad.models.Enfermedad;
import pe.edu.petcare.web.Enfermedad.repositories.RepositorioEnfermedad;
import pe.edu.petcare.web.HistorialMedico.models.HistorialMedico;
import pe.edu.petcare.web.HistorialMedico.repositories.RepositorioHistorial;
import pe.edu.petcare.web.Mascota.models.Mascota;
import pe.edu.petcare.web.Mascota.repositories.RepositorioMascota;
import pe.edu.petcare.web.Mascota.services.MascotaServicio;
import pe.edu.petcare.web.Vacuna.models.Vacuna;
import pe.edu.petcare.web.Vacuna.repositories.RepositorioVacuna;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialServicio {
    @Autowired
    private RepositorioHistorial repositorioHistorial;
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


    public HistorialMedico crearHistorialMedico(HistorialMapper historialMapper) {
        if (historialMapper == null) {
            throw new IllegalArgumentException("Los datos del historial médico no pueden ser nulos.");
        }

        Mascota mascotaAsociada = mascotaServicio.obtenerMascotaPorNombre(historialMapper.getNombreMascota());
        if (mascotaAsociada == null) {
            throw new IllegalArgumentException("La mascota asociada no existe.");
        }

        HistorialMedico historialMedico = new HistorialMedico();
        historialMedico.setMascota(mascotaAsociada);
        historialMedico.setFecha_UltimaDesparacitacion(historialMapper.getFechaUltimaDesparasitacion());
        historialMedico.setFecha_UltimaVacuna(historialMapper.getFechaUltimaVacuna());
        historialMedico.setFecha_VisitaVeterinario(historialMapper.getFechaVisitaVeterinario());


        List<Vacuna> vacunas = obtenerVacunasDesdeHistorialMapper(historialMapper);
        List<Enfermedad> enfermedades = obtenerEnfermedadesDesdeHistorialMapper(historialMapper);
        List<Alergia> alergias = obtenerAlergiasDesdeHistorialMapper(historialMapper);

        historialMedico.setVacunas(vacunas);
        historialMedico.setEnfermedades(enfermedades);
        historialMedico.setAlergias(alergias);

        // Guardar el historial médico
        return repositorioHistorial.save(historialMedico);
    }

    private List<Vacuna> obtenerVacunasDesdeHistorialMapper(HistorialMapper historialMapper) {
        List<Vacuna> vacunas = new ArrayList<>();
        for (String nombreVacuna : historialMapper.getNombresVacunas()) {
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

    private List<Enfermedad> obtenerEnfermedadesDesdeHistorialMapper(HistorialMapper historialMapper) {
        List<Enfermedad> enfermedades = new ArrayList<>();
        for (String nombreEnfermedad : historialMapper.getNombresEnfermedades()) {
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

    private List<Alergia> obtenerAlergiasDesdeHistorialMapper(HistorialMapper historialMapper) {
        List<Alergia> alergias = new ArrayList<>();
        for (String nombreAlergia : historialMapper.getNombresAlergias()) {
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
