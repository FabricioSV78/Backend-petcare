package pe.edu.upao.petcare.web.perfilclinico.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class PerfilCDTO {

    private String nombreMascota;
    private LocalDate fechaUltimaDesparasitacion;
    private LocalDate fechaUltimaVacuna;
    private LocalDate fechaVisitaVeterinario;
    //private List<String> nombresVacunas;
    //private List<String> nombresEnfermedades;
    //private List<String> nombresAlergias;
    private List<String> nombresVacunas = new ArrayList<>();
    private List<String> nombresEnfermedades = new ArrayList<>();
    private List<String> nombresAlergias = new ArrayList<>();


}
