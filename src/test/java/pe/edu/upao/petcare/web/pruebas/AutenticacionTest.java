package pe.edu.upao.petcare.web.pruebas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.cliente.repositories.RepositorioCliente;
import pe.edu.upao.petcare.web.cliente.services.Autenticacion;
import pe.edu.upao.petcare.web.cliente.services.RegistroMapper;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacionTest {

    @Mock
    private RepositorioCliente repositorioCliente;

    @InjectMocks
    private Autenticacion autenticacion;


    //REGISTRO exitoso
    @Test
    void cuandoLosDatosSonValidosEntoncesRegistraCliente() {
        // Datos de prueba válidos
        RegistroMapper registroMapper = new RegistroMapper("Juan", "Perez", "123456789", new Date(), "Masculino", "juan.perez@example.com", "password123");
        Cliente clienteEsperado = new Cliente("Juan", "Perez", "123456789", new Date(), "Masculino", "juan.perez@example.com", "password123");

        when(repositorioCliente.save(any(Cliente.class))).thenReturn(clienteEsperado);

        // Llama al método bajo prueba
        Cliente clienteResultante = autenticacion.registrarCliente(registroMapper);

        // Verifica los resultados
        assertNotNull(clienteResultante);
        assertEquals(clienteEsperado, clienteResultante);
    }

    @Test
    void cuandoElTelefonoNoTieneNueveDigitosEntoncesLanzaExcepcion() {
        // Datos de prueba con teléfono inválido
        RegistroMapper registroMapper = new RegistroMapper("Juan", "Perez", "12345", new Date(), "Masculino", "juan.perez@example.com", "password123");

        // Verifica que se lanza la excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            autenticacion.registrarCliente(registroMapper);
        });

        assertTrue(exception.getMessage().contains("El número de teléfono debe tener 9 dígitos"));
    }

    @Test
    void cuandoElCorreoNoTieneArrobaEntoncesLanzaExcepcion() {
        // Datos de prueba con correo inválido
        RegistroMapper registroMapper = new RegistroMapper("Juan", "Perez", "123456789", new Date(), "Masculino", "juan.perezexample.com", "password123");

        // Verifica que se lanza la excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            autenticacion.registrarCliente(registroMapper);
        });

        assertTrue(exception.getMessage().contains("El correo electrónico es inválido"));
    }


    //INICIO SESION
    @Test
    void cuandoCredencialesCorrectasEntoncesRetornaCliente() {
        // Datos de prueba
        String correo = "test@example.com";
        String clave = "claveCorrecta";
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setClave(clave);

        // Configura el comportamiento del mock
        when(repositorioCliente.findByCorreo(correo)).thenReturn(clienteEsperado);

        // Llama al método bajo prueba
        Cliente clienteResultante = autenticacion.iniciarSesion(correo, clave);

        // Verifica los resultados
        assertNotNull(clienteResultante);
        assertEquals(clienteEsperado, clienteResultante);
    }

    @Test
    void cuandoCorreoIncorrectoOInexistenteEntoncesRetornaNull() {
        // Datos de prueba
        String correoIncorrecto = "noexiste@example.com";

        // Configura el comportamiento del mock
        when(repositorioCliente.findByCorreo(correoIncorrecto)).thenReturn(null);

        // Llama al método bajo prueba
        Cliente clienteResultante = autenticacion.iniciarSesion(correoIncorrecto, "algunaClave");

        // Verifica los resultados
        assertNull(clienteResultante);
    }

    @Test
    void cuandoClaveIncorrectaEntoncesRetornaNull() {
        // Datos de prueba
        String correo = "test@example.com";
        String claveCorrecta = "claveCorrecta";
        String claveIncorrecta = "claveIncorrecta";
        Cliente clienteConClaveCorrecta = new Cliente();
        clienteConClaveCorrecta.setClave(claveCorrecta);

        // Configura el comportamiento del mock
        when(repositorioCliente.findByCorreo(correo)).thenReturn(clienteConClaveCorrecta);

        // Llama al método bajo prueba
        Cliente clienteResultante = autenticacion.iniciarSesion(correo, claveIncorrecta);

        // Verifica los resultados
        assertNull(clienteResultante);
    }

}
