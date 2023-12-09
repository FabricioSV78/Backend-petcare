package pe.edu.upao.petcare.web.tarea.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.logro.models.Logro;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class TareaConProgresoDTO {

    private String nombreTarea;
    private int puntajeTarea;
    private int vecesCompletada; // Cuántas veces se ha completado la tarea
    private int vecesRequeridas; // Cuántas veces se requiere completar la tarea para lograr el logro
    private boolean completada;

}
