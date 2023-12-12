package pe.edu.upao.petcare.web.consejo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.consejo.repositories.RepositorioConsejo;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ConsejoServicio {

    @Autowired
    private RepositorioConsejo consejoRepository;

    public Optional<Consejo> obtenerConsejoPorId(Long id) {
        return consejoRepository.findById(id);
    }



}
