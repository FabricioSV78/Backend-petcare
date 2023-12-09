package pe.edu.upao.petcare.web.logro.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.tarea.services.TareaDTO;

import java.util.List;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class LogroDTO {

    private String nombreLogro;
    private String descripcion;
    private int puntajeTotal;
    private int puntajeActual = 0;
    private double progreso;
    private boolean completado;
    private String nombreCategoria;

    public LogroDTO(String nombreLogro) {
        this.nombreLogro=nombreLogro;
    }


    public LogroDTO(String nombreLogro, String descripcion, boolean completado) {
        this.nombreLogro=nombreLogro;
        this.descripcion=descripcion;
        this.completado=completado;
    }
}
