package pe.edu.upao.petcare.web.excepciones;

public class DuenoNoEncontradoException extends RuntimeException{

    public DuenoNoEncontradoException(String mensaje) {

        super(mensaje  );
    }
}
