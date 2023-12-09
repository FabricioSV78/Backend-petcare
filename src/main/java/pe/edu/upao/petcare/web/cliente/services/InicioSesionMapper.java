package pe.edu.upao.petcare.web.cliente.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class InicioSesionMapper {
        private String correo;
        private String clave;

}



