package pe.edu.upao.petcare.web.accion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accion {

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

    public Accion(Logro logro, Tarea tarea, int vecesNecesarias, int vecesCompletadas) {
        this.logro=logro;
        this.tarea=tarea;
        this.vecesNecesarias=vecesNecesarias;
        this.vecesCompletadas=vecesCompletadas;
    }

    public boolean isCompleto() {
        return vecesCompletadas >= vecesNecesarias;
    }

    public void incrementarVecesCompletadas() {
        this.vecesCompletadas++;
    }




    // Este método devuelve un objeto que contiene las veces necesarias y las veces completadas.
    public AccionEstado getEstado() {
        return new AccionEstado(this.vecesNecesarias, this.vecesCompletadas);
    }

    // Clase interna que se utiliza para devolver el estado de la acción.
    @Getter
    public static class AccionEstado {
        // Getters
        private final int vecesNecesarias;
        private final int vecesCompletadas;

        public AccionEstado(int vecesNecesarias, int vecesCompletadas) {
            this.vecesNecesarias = vecesNecesarias;
            this.vecesCompletadas = vecesCompletadas;
        }

    }
}
