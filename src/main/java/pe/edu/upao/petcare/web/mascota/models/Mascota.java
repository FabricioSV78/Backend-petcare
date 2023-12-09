package pe.edu.upao.petcare.web.mascota.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
//import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    @ManyToOne
    @JoinColumn(name = "idTipoMascota")
    private TipoMascota tipoMascota;

    private String nombreMascota;
    private LocalDate fechaNMascota;

    private String genero;

    private boolean esterelizado;
    private Float peso;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente dueno;


    @OneToMany(mappedBy = "mascota")
    private List<Logro> logros;


    private int felicidad; // Valor entre 0 y 100

    // ... constructores, getters y setters ...

    public void aumentarFelicidad(int valor) {
        this.felicidad = Math.min(this.felicidad + valor, 100);
    }

    public void disminuirFelicidad(int valor) {
        this.felicidad = Math.max(this.felicidad - valor, 0);
    }


}


