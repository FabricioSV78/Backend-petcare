package pe.edu.petcare.web.Mascota.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonSerialize
public class MascotaMapper {

    private String tipoMascota;
    private String nombreMascota;
    private LocalDate fechaNMascota;
    private String genero;
    private String esterelizado;
    private String peso;

}


