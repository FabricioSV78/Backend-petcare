package pe.edu.upao.petcare.web.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import pe.edu.upao.petcare.web.cliente.models.Cliente;

import pe.edu.upao.petcare.web.cliente.repositories.RepositorioCliente;
import pe.edu.upao.petcare.web.cliente.services.RegistroMapper;
import pe.edu.upao.petcare.web.cliente.services.InicioSesionMapper;
import pe.edu.upao.petcare.web.cliente.services.Autenticacion;


import java.util.HashMap;
import java.util.Optional;


@RestController
public class ControladorCliente {

    @Autowired
    private Autenticacion autenticacion;


    @Autowired
    RepositorioCliente repositorioCliente;


    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody RegistroMapper clienteMapper) {

        Cliente clienteRegistrado = autenticacion.registrarCliente(clienteMapper);

        if (clienteRegistrado != null) {
            // El registro es exitoso
            return new ResponseEntity<>(clienteRegistrado, HttpStatus.OK);
        } else {
            // El registro falla
            return new ResponseEntity<>("Registro fallido", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody InicioSesionMapper credenciales) {
        Cliente cliente = autenticacion.iniciarSesion(credenciales.getCorreo(), credenciales.getClave());
        if (cliente != null) {
            return ResponseEntity.ok(cliente.getIdCliente());

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inicio de sesión fallido");
        }
    }



    @GetMapping("/perfilC/{idCliente}")
    public ResponseEntity<Cliente> mostrarPerfil(@PathVariable Long idCliente) {
        Cliente cliente = autenticacion.mostrarPerfilCliente(idCliente);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }










}
