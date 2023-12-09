package pe.edu.upao.petcare.web.excepciones;

public class TipoMascotaNoEncontradoException extends RuntimeException{

    public TipoMascotaNoEncontradoException(String mensaje) {

        super(mensaje);
    }
}
