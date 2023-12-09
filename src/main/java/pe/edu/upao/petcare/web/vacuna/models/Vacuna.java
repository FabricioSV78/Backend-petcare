package pe.edu.upao.petcare.web.vacuna.models;

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
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVacuna;

    private String nombreVacuna;

    @ManyToOne
    @JoinColumn(name = "idPerfilC")
    private PerfilClinico perfilClinico;

}

