package pe.edu.petcare.web.Evento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petcare.web.Evento.models.Evento;
import pe.edu.petcare.web.Evento.repositories.RepositorioEvento;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoServicio {
    @Autowired
    private RepositorioEvento repositorioEvento;

    public Evento crearEvento(Evento evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }

        return repositorioEvento.save(evento);

    }

    public List<EventoMapper> obtenerTodosLosEventos() {
        List<Evento> eventos = repositorioEvento.findAll();
        List<EventoMapper> eventoMappers = new ArrayList<>();

        for (Evento evento : eventos) {
            EventoMapper eventoMapper = new EventoMapper(evento.getTipoEvento(), evento.getFecha());
            eventoMappers.add(eventoMapper);
        }

        return eventoMappers;
    }
}
