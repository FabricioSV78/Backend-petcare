package pe.edu.upao.petcare.web.cliente.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class RegistroMapper {

    private String nombreCliente;
    private String apellidos;
    private String telefono;
    //private Date fechaNacimiento;
    private String sexo;
    private String correo;
    private String clave;

}
