package pe.edu.upao.petcare.web.pruebas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.cliente.repositories.RepositorioCliente;

import pe.edu.upao.petcare.web.excepciones.DatosNoValidosException;
import pe.edu.upao.petcare.web.mascota.models.Mascota;
import pe.edu.upao.petcare.web.mascota.repositories.RepositorioMascota;
import pe.edu.upao.petcare.web.mascota.services.MascotaDTO;
import pe.edu.upao.petcare.web.mascota.services.MascotaServicio;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;
import pe.edu.upao.petcare.web.tipomascota.repositories.RepositorioTipoMascota;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MascotaTest {

    @Mock
    private RepositorioMascota repositorioMascota;
    @Mock
    private RepositorioCliente repositorioCliente;
    @Mock
    private RepositorioTipoMascota repositorioTipoMascota;
    @InjectMocks
    private MascotaServicio mascotaServicio;


    // Escenario de éxito
    @Test
    void cuandoLosDatosSonValidosEntoncesCreaPerfilMascota() {
        // Arrange
        MascotaDTO mascotaDTO = new MascotaDTO(1L, 1L, "Firulais", "20/12/2023", "Hembra", true, 5.0f);
        Mascota mascotaEsperada = new Mascota();

        when(repositorioTipoMascota.findById(anyLong())).thenReturn(Optional.of(new TipoMascota()));
        when(repositorioCliente.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        when(repositorioMascota.save(any(Mascota.class))).thenReturn(mascotaEsperada);

        // Act
        Mascota resultado = mascotaServicio.crearPerfilMascota(mascotaDTO);

        // Assert
        assertNotNull(resultado);
    }

    // Nombre de la mascota nulo o vacío
    @Test
    void cuandoElNombreDeLaMascotaEsNuloOVacioEntoncesLanzaExcepcion() {
        // Arrange
        MascotaDTO mascotaDTO = new MascotaDTO(1L, 1L, "", "9/12/2023", "Macho", true, 5.0f);

        // Act & Assert
        assertThrows(DatosNoValidosException.class, () -> mascotaServicio.crearPerfilMascota(mascotaDTO));
    }

    // genero invalido
    @Test
    void cuandoElGeneroEsInvalidoEntoncesLanzaDatosNoValidosException() {
        // Arrange
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setNombreMascota("Rocky");
        mascotaDTO.setFechaNMascota("20/12/2023");
        mascotaDTO.setIdTipoMascota(1L);
        mascotaDTO.setIdCliente(1L);
        mascotaDTO.setGenero("Indefinido");  // Un género inválido para esta prueba

        // Act & Assert
        DatosNoValidosException exception = assertThrows(
                DatosNoValidosException.class,
                () -> mascotaServicio.crearPerfilMascota(mascotaDTO),
                "Se esperaba que lanzara una DatosNoValidosException debido a un género inválido"
        );

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("El género debe ser 'Hembra' o 'Macho'", exception.getMessage());
    }




    // Escenario donde la mascota no se encuentra
    @Test
    void cuandoLaMascotaNoExisteEntoncesLanzaExcepcion() {
        // Arrange
        Long idMascota = 1L;
        when(repositorioMascota.findById(idMascota)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DatosNoValidosException.class, () -> mascotaServicio.obtenerPerfilMascotaConConsejo(idMascota));
    }


    // Escenario de éxito
    @Test
    void cuandoLosDatosParaModificarSonValidosEntoncesActualizaPerfilMascota() {
        // Arrange
        Long idMascota = 1L;
        Mascota mascotaExistente = new Mascota();
        MascotaDTO mascotaDTO = new MascotaDTO(1L, 1L, "Firulais", "10/12/2023", "Macho", true, 5.0f);

        when(repositorioMascota.findById(idMascota)).thenReturn(Optional.of(mascotaExistente));
        when(repositorioTipoMascota.findById(anyLong())).thenReturn(Optional.of(new TipoMascota()));
        when(repositorioCliente.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        when(repositorioMascota.save(any(Mascota.class))).thenReturn(mascotaExistente);

        // Act
        Mascota resultado = mascotaServicio.modificarPerfilMascota(idMascota, mascotaDTO);

        // Assert
        assertNotNull(resultado);
    }

    // Escenario donde la mascota no se encuentra
    @Test
    void cuandoLaMascotaNoExisteEntoncesLanzaMascotaNoEncontradaException() {
        // Arrange
        Long idMascota = 1L;

        // Simular que el repositorio devuelve un Optional vacío para simular que la mascota no existe
        when(repositorioMascota.findById(idMascota)).thenReturn(Optional.empty());

        // Act & Assert
        // Usar assertThrows para verificar que se lanza la excepción cuando la mascota no se encuentra
        DatosNoValidosException exception = assertThrows(
                DatosNoValidosException.class,
                () -> mascotaServicio.obtenerPerfilMascotaConConsejo(idMascota),
                "Se esperaba que lanzara una DatosNoValidosException"
        );

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("Mascota no encontrada", exception.getMessage());
    }


}
