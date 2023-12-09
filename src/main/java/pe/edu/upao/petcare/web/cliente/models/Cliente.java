package pe.edu.upao.petcare.web.cliente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import pe.edu.upao.petcare.web.mascota.models.Mascota;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombreCliente;
    private String apellidos;
    private String telefono;
    private Date fechaNacimiento;
    private String sexo;
    private String correo;
    private String clave;

    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas;


    public Cliente(String nombreCliente, String apellidos, String telefono, Date fechaNacimiento, String sexo, String correo, String clave) {

    }

}



