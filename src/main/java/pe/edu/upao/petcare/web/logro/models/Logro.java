package pe.edu.upao.petcare.web.logro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.accion.models.Accion;

import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.tarea.models.Tarea;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogro;

    private String nombreLogro;

    private String descripcion;

    private int puntajeTotal;

    private int puntajeActual = 0;

    private double progreso;

    private boolean completado = false;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idMascota")
    @JsonIgnore
    private Mascota mascota;

    @OneToMany(mappedBy = "logro")
    private List<Accion> accions;


    public boolean isCompleto() {
        return this.accions.stream()
                .allMatch(requisito -> requisito.isCompleto());
    }

    public Logro(String nombreLogro, String descripcion, int puntajeTotal, Categoria categoria) {
        this.nombreLogro=nombreLogro;
        this.descripcion=descripcion;
        this.puntajeTotal=puntajeTotal;
        this.categoria=categoria;

    }

    public Logro(String nombreLogro, String descripcion, int puntajeTotal,
                 int puntajeActual, double progreso, boolean completado,
                 Categoria categoria) {
        this.nombreLogro = nombreLogro;
        this.descripcion = descripcion;
        this.puntajeTotal = puntajeTotal;
        this.puntajeActual = puntajeActual;
        this.progreso = progreso;
        this.completado = completado;
        this.categoria = categoria;
        // La mascota se puede asignar más tarde si es necesario
        this.mascota = null;
    }

}
