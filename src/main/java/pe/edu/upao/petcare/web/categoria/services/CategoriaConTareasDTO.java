package pe.edu.upao.petcare.web.categoria.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.tarea.services.TareaDTO;

import java.util.List;

@Data

@JsonSerialize
@NoArgsConstructor
public class CategoriaConTareasDTO {

    private String nombreCategoria;
    private List<String> nombresTareas;

    public CategoriaConTareasDTO(String nombreCategoria, List<String> nombresTareas) {
        this.nombreCategoria = nombreCategoria;
        this.nombresTareas = nombresTareas;
    }
}
