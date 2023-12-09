package pe.edu.upao.petcare.web.accion.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class AccionDTO {

    private Long idAccion;
    private int puntajeObtenido;
    private Date fechaAccion;
    private Long idTarea;
    private Long idMascota;

    public AccionDTO(Long idAccion, int puntajeObtenido, Date fechaAccion){
        this.idAccion=idAccion;
        this.puntajeObtenido=puntajeObtenido;
        this.fechaAccion=fechaAccion;
    }

}
