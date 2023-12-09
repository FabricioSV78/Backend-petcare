package pe.edu.upao.petcare.web.tipomascota.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class TMascotaDTO {

    private String nombreTipo;
}
