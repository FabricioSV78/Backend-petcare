package pe.edu.upao.petcare.web.tarea.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.mascota.models.Mascota;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTarea;

    private String nombreTarea;

    private int puntajeTarea;


    // Frecuencia de la tarea en días
    //private int frecuenciaEnDias;


    @ManyToOne
    @JoinColumn(name = "idLogro")
    private Logro logro;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;


    public Tarea(String nombreTarea, int puntajeTarea, Logro logro, Categoria categoria) {
        this.nombreTarea=nombreTarea;
        this.puntajeTarea=puntajeTarea;
        this.logro=logro;
        this.categoria=categoria;
    }

}

