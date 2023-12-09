package pe.edu.upao.petcare.web.pruebas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.petcare.web.accion.services.AccionServicio;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.services.MascotaServicio;
import pe.edu.upao.petcare.web.perfilclinico.models.PerfilClinico;
import pe.edu.upao.petcare.web.perfilclinico.repositories.RepositorioPerfilC;
import pe.edu.upao.petcare.web.perfilclinico.services.PerfilCDTO;
import pe.edu.upao.petcare.web.perfilclinico.services.PerfilCServicio;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerfilClinicoTest {

    @InjectMocks
    private PerfilCServicio perfilCServicio;

    @Mock
    private RepositorioPerfilC repositorioPerfilC;

    @Mock
    private MascotaServicio mascotaServicio;


   //Escenario exitoso

    @Test
    public void testCrearPerfilClinicoConDatosValidos() {
        // Configura el DTO y los mocks de dependencias
        PerfilCDTO perfilCDTO = new PerfilCDTO();
        perfilCDTO.setNombreMascota("Firulais");
        // Configura el resto de datos necesarios en perfilCDTO...

        Mascota mascotaMock = new Mascota();
        when(mascotaServicio.obtenerMascotaPorNombre(anyString())).thenReturn(mascotaMock);

        PerfilClinico perfilClinicoMock = new PerfilClinico();
        when(repositorioPerfilC.save(any(PerfilClinico.class))).thenReturn(perfilClinicoMock);

        // Ejecuta el método a probar
        PerfilClinico resultado = perfilCServicio.crearPerfilClinico(perfilCDTO);

        // Verifica los resultados y las interacciones
        assertNotNull(resultado);
        verify(mascotaServicio).obtenerMascotaPorNombre("Firulais");
        verify(repositorioPerfilC).save(any(PerfilClinico.class));

    }

   //Escenario fallido
    @Test
    void cuandoMascotaNoExisteEntoncesLanzaExcepcion() {
        PerfilCDTO perfilCDTO = new PerfilCDTO();
        perfilCDTO.setNombreMascota("MascotaInexistente");

        when(mascotaServicio.obtenerMascotaPorNombre("MascotaInexistente")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            perfilCServicio.crearPerfilClinico(perfilCDTO);
        });
    }

    //Escenario exitoso
    @Test
    void cuandoPerfilClinicoExistePorIdEntoncesDevuelvePerfilCDTO() {
        Long idPerfilC = 1L;

        // Simular un PerfilClinico existente
        PerfilClinico perfilClinicoExistente = new PerfilClinico();
        perfilClinicoExistente.setMascota(new Mascota());
        perfilClinicoExistente.setFecha_UltimaDesparacitacion(LocalDate.now());
        perfilClinicoExistente.setFecha_UltimaVacuna(LocalDate.now());
        perfilClinicoExistente.setFecha_VisitaVeterinario(LocalDate.now());

        when(repositorioPerfilC.findById(idPerfilC)).thenReturn(Optional.of(perfilClinicoExistente));

        PerfilCDTO resultado = perfilCServicio.obtenerPerfilClinico(idPerfilC);

        assertNotNull(resultado);
        // Realiza más aserciones para verificar los valores de los campos en el PerfilCDTO devuelto
    }


    //Escenario fallido
    @Test
    void cuandoPerfilClinicoNoExistePorIdEntoncesLanzaExcepcion() {
        Long idPerfilC = 1L;

        when(repositorioPerfilC.findById(idPerfilC)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            perfilCServicio.obtenerPerfilClinico(idPerfilC);
        });
    }


}
