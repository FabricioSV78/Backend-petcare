package pe.edu.upao.petcare.web.accion.models;
/*
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoLogro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLogro")
    private Logro logro;

    @ManyToOne
    @JoinColumn(name = "idTarea")
    private Tarea tarea;

    private int vecesNecesarias;
    private int vecesCompletadas;

    public boolean isCompleto() {
        return vecesCompletadas >= vecesNecesarias;
    }

}
*/