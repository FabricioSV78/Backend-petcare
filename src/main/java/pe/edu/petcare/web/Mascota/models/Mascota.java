package pe.edu.petcare.web.Mascota.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.petcare.web.HistorialMedico.models.HistorialMedico;


import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mascota;

    private String tipoMascota;
    private String nombreMascota;
    private LocalDate fechaNMascota;
    private String genero;
    private String esterelizado;
    private String peso;


    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistorialMedico historialMedico;

}


