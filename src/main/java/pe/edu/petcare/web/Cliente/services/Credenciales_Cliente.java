package pe.edu.petcare.web.Cliente.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class Credenciales_Cliente {
        private String correo;
        private String clave;

}



