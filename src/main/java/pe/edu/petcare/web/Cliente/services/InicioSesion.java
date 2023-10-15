package pe.edu.petcare.web.Cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.petcare.web.Cliente.models.Cliente;
import pe.edu.petcare.web.Cliente.repositories.RepositorioCliente;

@Service
public class InicioSesion {

    @Autowired
    private RepositorioCliente repositorioCliente;


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
}

