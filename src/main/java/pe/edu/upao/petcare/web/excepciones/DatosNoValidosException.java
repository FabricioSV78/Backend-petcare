package pe.edu.upao.petcare.web.excepciones;

public class DatosNoValidosException extends RuntimeException {
    public DatosNoValidosException(String mensaje) {
        super(mensaje);
    }
}


