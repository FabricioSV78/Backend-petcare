package pe.edu.petcare.web.Vacuna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.petcare.web.Cliente.models.Cliente;
import pe.edu.petcare.web.HistorialMedico.models.HistorialMedico;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_vacuna;

    private String nombreVacuna;

    @ManyToOne
    @JoinColumn(name = "id_Historial")
    private HistorialMedico historialMedico;

}

