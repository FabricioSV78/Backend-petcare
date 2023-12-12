package pe.edu.upao.petcare.web.cliente.services;

//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.petcare.web.cliente.models.Cliente;
import pe.edu.upao.petcare.web.cliente.repositories.RepositorioCliente;
//import io.jsonwebtoken.Jwts;


import java.util.Optional;

@Service
public class Autenticacion {
    @Autowired
    private RepositorioCliente repositorioCliente;

    private final String secretKey = "secret";

    public Cliente registrarCliente(RegistroMapper clienteMapper) {
        // Verificar que el número de teléfono tiene 9 dígitos
        if (clienteMapper.getTelefono() == null || clienteMapper.getTelefono().length() != 9) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 dígitos.");
        }

        // Verificar que el correo electrónico contiene arroba
        if (clienteMapper.getCorreo() == null || !clienteMapper.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo electrónico es inválido.");
        }

        // crea una nueva instancia de Usuario
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombreCliente(clienteMapper.getNombreCliente());
        nuevoCliente.setApellidos(clienteMapper.getApellidos());
        nuevoCliente.setTelefono(clienteMapper.getTelefono());
        //nuevoCliente.setFechaNacimiento(clienteMapper.getFechaNacimiento());
        nuevoCliente.setSexo(clienteMapper.getSexo());
        nuevoCliente.setCorreo(clienteMapper.getCorreo());
        nuevoCliente.setClave(clienteMapper.getClave());

        // Guarda el nuevo usuario en el repositorio
        return repositorioCliente.save(nuevoCliente);
    }

    public Cliente iniciarSesion(String correo, String clave){
        Cliente cliente = repositorioCliente.findByCorreo(correo);

        if(cliente!=null && contraValida(clave, cliente.getClave())){
            return cliente;
        }else{
            return null;
        }
    }

    private boolean contraValida(String contraIngresada, String contraAlmacenada){
        return contraIngresada.equals(contraAlmacenada);
    }


    public Cliente mostrarPerfilCliente(Long idCliente) {
        // Buscar el cliente en el repositorio por ID
        Optional<Cliente> cliente = repositorioCliente.findById(idCliente);

        // Verificar si el cliente existe
        if (!cliente.isPresent()) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }

        // Devolver los detalles del cliente encontrado
        return cliente.get();
    }












}



