package pe.edu.upao.petcare.web.categoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.List;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nombreCategoria;

    // Agregar la relación con Tarea
    @OneToMany(mappedBy = "categoria")
    private List<Tarea> tareas;

    public Categoria(String nombreCategoria) {
        this.nombreCategoria=nombreCategoria;

    }


}
