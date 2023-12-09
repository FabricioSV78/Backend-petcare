package pe.edu.upao.petcare.web.categoria.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.tarea.services.TareaConProgresoDTO;

import java.util.List;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class CategoriaConProgresoDTO {

    private String nombreCategoria;
    private List<TareaConProgresoDTO> tareasConProgreso;

}
