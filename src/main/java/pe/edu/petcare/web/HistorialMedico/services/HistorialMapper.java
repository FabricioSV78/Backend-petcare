package pe.edu.petcare.web.HistorialMedico.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;
import pe.edu.petcare.web.Mascota.models.Mascota;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@JsonSerialize
public class HistorialMapper {

    private String nombreMascota;
    private LocalDate fechaUltimaDesparasitacion;
    private LocalDate fechaUltimaVacuna;
    private LocalDate fechaVisitaVeterinario;
    private List<String> nombresVacunas;
    private List<String> nombresEnfermedades;
    private List<String> nombresAlergias;


}
