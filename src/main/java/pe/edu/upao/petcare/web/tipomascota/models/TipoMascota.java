package pe.edu.upao.petcare.web.tipomascota.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoMascota")
    private Long idTipoMascota;

    private String nombreTipo;

    public TipoMascota(String nombreTipo) {
        this.nombreTipo=nombreTipo;
    }
}
