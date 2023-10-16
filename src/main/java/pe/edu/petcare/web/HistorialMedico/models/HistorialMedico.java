package pe.edu.petcare.web.HistorialMedico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.petcare.web.Alergia.models.Alergia;
import pe.edu.petcare.web.Enfermedad.models.Enfermedad;
import pe.edu.petcare.web.Mascota.models.Mascota;
import pe.edu.petcare.web.Vacuna.models.Vacuna;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Historial;

    @OneToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_UltimaDesparacitacion;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_UltimaVacuna;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_VisitaVeterinario;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL)
    private List<Vacuna> vacunas;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL)
    private List<Enfermedad> enfermedades;


}


