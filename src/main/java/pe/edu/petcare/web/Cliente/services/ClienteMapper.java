package pe.edu.petcare.web.Cliente.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonSerialize
public class ClienteMapper {

    private String nombre_cliente;
    private String apellidos;
    private String telefono;
    private Date fecha_nacimiento;
    private String sexo;
    private String correo;
    private String clave;

}
