package pe.edu.upao.petcare.web.alergia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.perfilclinico.models.PerfilClinico;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAlergia;

    private String nombreAlergia;

    @ManyToOne
    @JoinColumn(name = "idPerfilC")
    private PerfilClinico perfilClinico;

}
