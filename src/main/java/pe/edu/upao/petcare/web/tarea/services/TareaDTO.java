package pe.edu.upao.petcare.web.tarea.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class TareaDTO {

    private String nombreTarea;
    private int puntajeTarea;
    @JsonIgnore
    private Long idLogro;




    public TareaDTO(String nombreTarea, int puntajeTarea) {
        this.nombreTarea=nombreTarea;
        this.puntajeTarea=puntajeTarea;

    }

    public TareaDTO(String nombreTarea) {
        this.nombreTarea=nombreTarea;
    }
}
