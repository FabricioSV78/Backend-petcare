package pe.edu.upao.petcare.web.mascota.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class MascotaDTO {

    private Long idTipoMascota;
    private Long idCliente;
    private String nombreMascota;
    private String fechaNMascota;
    private String genero;
    private boolean esterelizado;
    private Float peso;



}


