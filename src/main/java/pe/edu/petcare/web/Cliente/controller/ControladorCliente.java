package pe.edu.petcare.web.Cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.petcare.web.Cliente.models.Cliente;
import pe.edu.petcare.web.Cliente.services.ClienteMapper;
import pe.edu.petcare.web.Cliente.services.Credenciales_Cliente;
import pe.edu.petcare.web.Cliente.services.InicioSesion;
import pe.edu.petcare.web.Cliente.services.Registro;


@RestController
public class ControladorCliente {

    @Autowired
    private Registro registrarse;

    @Autowired
    private InicioSesion login;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCliente(@RequestBody ClienteMapper clienteMapper) {

        Cliente clienteRegistrado = registrarse.registrarCliente(clienteMapper);
        if (clienteRegistrado != null) {
            // El registro es exitoso
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } else {
            // El registro falla
            return new ResponseEntity<>("Registro fallido", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesions(@RequestBody Credenciales_Cliente credenciales){
        Cliente cliente = login.iniciarSesion(credenciales.getCorreo(), credenciales.getClave());

        if(cliente!=null) {
            return new ResponseEntity<>("Inicio de sesion exitoso", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Inicio de sesion fallido", HttpStatus.UNAUTHORIZED);
        }
    }

}
