package pe.edu.petcare.web.Cliente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    private String nombre_cliente;
    private String apellidos;
    private String telefono;
    private Date fecha_nacimiento;
    private String sexo;
    private String correo;
    private String clave;

}



