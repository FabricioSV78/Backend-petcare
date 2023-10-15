package pe.edu.petcare.web.Evento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.petcare.web.Evento.models.Evento;
import pe.edu.petcare.web.Evento.services.EventoMapper;
import pe.edu.petcare.web.Evento.services.EventoServicio;

import java.util.List;

@RestController
public class ControladorEvento {
    @Autowired
    private EventoServicio eventoServicio;

    @PostMapping("/crear_evento")
    public ResponseEntity<String> crearEvento(@RequestBody EventoMapper eventoMapper) {

        Evento nuevo_evento = new Evento();
        nuevo_evento.setTipoEvento(eventoMapper.getTipoEvento());
        nuevo_evento.setFecha(eventoMapper.getFecha());

        // Llama al servicio para crear el evento
        Evento eventoCreado = eventoServicio.crearEvento(nuevo_evento);

        if (eventoCreado != null) {
            return new ResponseEntity<>("Evento creado", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No se pudo crear el evento", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrar_eventos")
    public List<EventoMapper> obtenerTodosLosEventos() {
        return eventoServicio.obtenerTodosLosEventos();
    }
}



