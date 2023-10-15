package pe.edu.petcare.web.Evento.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.petcare.web.Cliente.models.Cliente;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_evento;

    private String tipoEvento;
    private LocalDate fecha;

    @ManyToOne
    Cliente cliente;


}
