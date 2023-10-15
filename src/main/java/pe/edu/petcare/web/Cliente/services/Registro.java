package pe.edu.petcare.web.Cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petcare.web.Cliente.models.Cliente;
import pe.edu.petcare.web.Cliente.repositories.RepositorioCliente;

@Service
public class Registro {
    @Autowired
    private RepositorioCliente repositorioCliente;

    public Cliente registrarCliente(ClienteMapper clienteMapper) {

        // crea una nueva instancia de Usuario
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre_cliente(clienteMapper.getNombre_cliente());
        nuevoCliente.setApellidos(clienteMapper.getApellidos());
        nuevoCliente.setTelefono(clienteMapper.getTelefono());
        nuevoCliente.setFecha_nacimiento(clienteMapper.getFecha_nacimiento());
        nuevoCliente.setSexo(clienteMapper.getSexo());
        nuevoCliente.setCorreo(clienteMapper.getCorreo());
        nuevoCliente.setClave(clienteMapper.getClave());
        // Guarda el nuevo usuario en el repositorio
        return repositorioCliente.save(nuevoCliente);
    }


}



