package pe.edu.upao.petcare.web.consejo.services;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.consejo.repositories.RepositorioConsejo;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;

import java.util.List;
import java.util.Random;

@Service
public class ConsejoServicio {

    @Autowired
    private RepositorioConsejo repositorioConsejo;

    public ConsejoDTO obtenerConsejoAleatorioPorTipo(TipoMascota tipoMascota) {
        List<Consejo> consejosPorTipo = repositorioConsejo.findByTipoMascota(tipoMascota);

        // Si no hay consejos, devuelve un ConsejoMapper con valores predeterminados o vacíos.
        if (consejosPorTipo.isEmpty()) {
            return new ConsejoDTO(0L, "No hay consejos disponibles para este tipo de mascota.");
        }

        Consejo consejoAleatorio = consejosPorTipo.get(new Random().nextInt(consejosPorTipo.size()));
        return new ConsejoDTO(consejoAleatorio.getIdConsejo(), consejoAleatorio.getDescripcionC());
    }


}
*/