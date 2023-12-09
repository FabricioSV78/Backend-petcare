package pe.edu.upao.petcare.web.perfilclinico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upao.petcare.web.alergia.models.Alergia;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.enfermedad.models.Enfermedad;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.vacuna.models.Vacuna;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPerfilC;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente creador;

    @OneToOne
    @JoinColumn(name = "idMascota")
    private Mascota mascota;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_UltimaDesparacitacion;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_UltimaVacuna;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha_VisitaVeterinario;

    @OneToMany(mappedBy = "perfilClinico", cascade = CascadeType.ALL)
    private List<Vacuna> vacunas;

    @OneToMany(mappedBy = "perfilClinico", cascade = CascadeType.ALL)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "perfilClinico", cascade = CascadeType.ALL)
    private List<Enfermedad> enfermedades;


}


