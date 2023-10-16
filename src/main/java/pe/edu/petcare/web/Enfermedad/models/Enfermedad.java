package pe.edu.petcare.web.Enfermedad.models;

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
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_enfermedad;

    private String nombreEnfermedad;

    @ManyToOne
    @JoinColumn(name = "id_Historial")
    private HistorialMedico historialMedico;
}
