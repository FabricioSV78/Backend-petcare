package pe.edu.upao.petcare.web.consejo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consejo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsejo;


    private String descripcionC;


    public Consejo(String descripcionC) {
        this.descripcionC=descripcionC;
    }
}
