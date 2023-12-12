package pe.edu.upao.petcare.web.categoria.services;

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
public class CategoriaDTO {
    private String nombreCategoria;


}
