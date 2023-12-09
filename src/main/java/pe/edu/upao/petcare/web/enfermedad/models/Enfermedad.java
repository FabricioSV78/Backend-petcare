package pe.edu.upao.petcare.web.enfermedad.models;

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
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEnfermedad;

    private String nombreEnfermedad;

    @ManyToOne
    @JoinColumn(name = "idPerfilC")
    private PerfilClinico perfilClinico;
}
