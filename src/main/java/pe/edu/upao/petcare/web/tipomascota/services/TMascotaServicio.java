package pe.edu.upao.petcare.web.tipomascota.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;
import pe.edu.upao.petcare.web.tipomascota.repositories.RepositorioTipoMascota;

@Service
public class TMascotaServicio {


    @Autowired
    private RepositorioTipoMascota repositorioTipoMascota;



    public TipoMascota crearTipoMascota(TMascotaDTO tMascotaDTO) {


        TipoMascota tipoMascota = new TipoMascota();

        tipoMascota.setNombreTipo(tMascotaDTO.getNombreTipo());

        // Luego, guardamos la mascota en la base de datos
        return repositorioTipoMascota.save(tipoMascota);
    }




}









