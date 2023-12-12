package pe.edu.upao.petcare.web;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upao.petcare.web.accion.models.Accion;
import pe.edu.upao.petcare.web.accion.repositories.RepositorioAccion;
import pe.edu.upao.petcare.web.categoria.models.Categoria;
import pe.edu.upao.petcare.web.categoria.repositories.RepositorioCategoria;
import pe.edu.upao.petcare.web.consejo.models.Consejo;
import pe.edu.upao.petcare.web.consejo.repositories.RepositorioConsejo;
import pe.edu.upao.petcare.web.logro.models.Logro;
import pe.edu.upao.petcare.web.logro.repositories.RepositorioLogro;
import pe.edu.upao.petcare.web.tarea.models.Tarea;
import pe.edu.upao.petcare.web.tarea.repositories.RepositorioTarea;
import pe.edu.upao.petcare.web.tipomascota.models.TipoMascota;
import pe.edu.upao.petcare.web.tipomascota.repositories.RepositorioTipoMascota;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatosIniciales {

    @Autowired
    private RepositorioCategoria categoriaRepository;

    @Autowired
    private RepositorioLogro logroRepository;

    @Autowired
    private RepositorioTarea tareaRepository;

    @Autowired
    private RepositorioAccion accionRepository;

    @Autowired
    private RepositorioConsejo repositorioConsejo;

    @Autowired
    private RepositorioTipoMascota repositorioTipoMascota;

    @PostConstruct
    public void crearDatosIniciales() {
        // Categorías
        Categoria alimentacion = new Categoria("Alimentación");
        Categoria salud = new Categoria("Salud");
        Categoria ejercicio = new Categoria("Ejercicio y actividad física");
        Categoria entrenamiento = new Categoria("Entrenamiento y socialización");
        Categoria higiene = new Categoria("Higiene y cuidado personal");

        // Guardar categorías
        categoriaRepository.save(alimentacion);
        categoriaRepository.save(salud);
        categoriaRepository.save(ejercicio);
        categoriaRepository.save(entrenamiento);


        // Logros para "Alimentación"
        List<Logro> logrosAlimentacion = List.of(
                new Logro("Chef Canino", "Logro otorgado por proporcionar una dieta casera equilibrada y saludable para tu mascota aprobada por un veterinario.", 100, 0, 0.0, false, alimentacion),
                new Logro("Dieta Especializada", "Otorgado a aquellos que manejan con éxito dietas especiales, como alimentación medicinal o restricciones alimenticias específicas.", 100, 0, 0.0, false, alimentacion),
                new Logro("Maestro de las Porciones", "Controla las porciones como un profesional. Aprende a darle a tu mascota la cantidad justa para mantener su bienestar.", 100, 0, 0.0, false, alimentacion)

                // ... otros logros de la categoría "Alimentación"
        );
        logroRepository.saveAll(logrosAlimentacion);

        // Logros para "Salud"
        List<Logro> logrosSalud = List.of(
                new Logro("Energía Felina", "Mantén a tu mascota llena de vitalidad y alegría. para conseguir el logro Juega con tu mascota usando un juguete interactivo, por lo menos 5 veces.", 100, 0, 0.0, false, salud),
                new Logro("Salud Dental", "Cuida de la salud bucal de tu mascota. para obtener este logro.", 100, 0, 0.0, false, salud),
                new Logro("Salto Alegre", "Fomenta como todo un crack la actividad física y la alegría en tu mascota.", 100, 0, 0.0, false, salud),
                new Logro("Bienestar Mental", "Promueve la felicidad y el bienestar mental de tu mascota", 100, 0, 0.0, false, salud)
                // ... otros logros de la categoría "Salud"
        );
        logroRepository.saveAll(logrosSalud);

        // Logros para "Ejercicio y actividad física"
        List<Logro> logrosEjercicio = List.of(
                new Logro("Atleta Canino", "Transforma a tu mascota en un atleta de élite, desarrollando fuerza, resistencia y agilidad a través de un entrenamiento diario.", 100, 0, 0.0, false, ejercicio),
                new Logro("Ruta de la Naturaleza", "Sumérgete en la belleza de diferentes entornos naturales mientras ejercitas a tu mascota, creando una experiencia de ejercicio única.", 100, 0, 0.0, false, ejercicio),
                new Logro("Desafío de Salto", "Pon a prueba la destreza y la capacidad de salto de tu mascota al superar obstáculos virtuales en un emocionante desafío.", 100, 0, 0.0, false, ejercicio),
                new Logro("Estiramiento Canino", "Promueve la flexibilidad y el bienestar general de tu mascota a través de sesiones regulares de estiramiento.", 100, 0, 0.0, false, ejercicio)

                // ... otros logros de la categoría "Ejercicio y actividad física"
        );
        logroRepository.saveAll(logrosEjercicio);

        // Logros para "Entrenamiento y socialización"
        List<Logro> logrosEntrenamiento = List.of(
                new Logro("Maestro de la Socialización", "Desarrolla las habilidades sociales de tu mascota para que sea un experto en interactuar y hacer amigos. ", 100, 0, 0.0, false, entrenamiento),
                new Logro("Rey de las Fiestas", "Convierte a tu mascota en el alma de las fiestas, destacando en eventos sociales y disfrutando de la compañía de otros.", 100, 0, 0.0, false, entrenamiento),
                new Logro("Diplomacia Peluda", "Fomenta relaciones armoniosas entre tu mascota y otras mascotas virtuales, promoviendo la paz y la cooperación.", 100, 0, 0.0, false, entrenamiento),
                new Logro("Guardián Social", "Desarrolla la capacidad de tu mascota para cuidar y apoyar a otros en situaciones sociales.", 100, 0, 0.0, false, entrenamiento)
                // ... otros logros de la categoría "Entrenamiento y socialización"
        );
        logroRepository.saveAll(logrosEntrenamiento);

        Logro logroChefCanino = logroRepository.findByNombreLogro("Chef Canino").orElse(null);
        Logro logroDietaEspecializada = logroRepository.findByNombreLogro("Dieta Especializada").orElse(null);
        Logro logroMaestroPorciones = logroRepository.findByNombreLogro("Maestro de las Porciones").orElse(null);


        Logro logroEnergíaFelina = logroRepository.findByNombreLogro("Energía Felina").orElse(null);
        Logro logroSaludDental = logroRepository.findByNombreLogro("Salud Dental").orElse(null);
        Logro logroSaltoAlegre = logroRepository.findByNombreLogro("Salto Alegre").orElse(null);
        Logro logroBienestarMental = logroRepository.findByNombreLogro("Bienestar Mental").orElse(null);

        Logro logroAtletaCanino = logroRepository.findByNombreLogro("Atleta Canino").orElse(null);
        Logro logroRutaNaturaleza = logroRepository.findByNombreLogro("Ruta de la Naturaleza").orElse(null);
        Logro logroDesafíoSalto = logroRepository.findByNombreLogro("Desafío de Salto").orElse(null);
        Logro logroEstiramientoCanino = logroRepository.findByNombreLogro("Estiramiento Canino").orElse(null);

        Logro logroMaestroSocialización = logroRepository.findByNombreLogro("Maestro de la Socialización").orElse(null);
        Logro logroReyFiestas = logroRepository.findByNombreLogro("Rey de las Fiestas").orElse(null);
        Logro logroDiplomaciaPeluda = logroRepository.findByNombreLogro("Diplomacia Peluda").orElse(null);
        Logro logroGuardiánSocial = logroRepository.findByNombreLogro("Guardián Social").orElse(null);


        // Crear tareas
        List<Tarea> tareas = List.of(
                new Tarea("Establece horarios de alimentación regulares para mantener la salud y la rutina de tu mascota. Sigue el horario de alimentación establecido", 50, logroChefCanino, alimentacion),
                new Tarea("Crear un plan de comidas equilibrado.", 50, logroChefCanino, alimentacion),
                //---//
                new Tarea("Introduce nuevos alimentos en la dieta de tu mascota.", 100, logroDietaEspecializada, alimentacion),
                //---//
                new Tarea("Mide con precisión las porciones de comida al menos 5 veces.", 100, logroMaestroPorciones, alimentacion),
                //---//
                new Tarea("Juega con tu mascota usando un juguete interactivo, por lo menos 5 veces.", 100, logroEnergíaFelina, salud),
                //---//
                new Tarea("Cepilla los dientes de tu mascota. 5 veces", 100, logroSaludDental, salud),
                //---//
                new Tarea("Lleva a tu mascota a dar un paseo", 100, logroSaltoAlegre, salud),
                //---//
                new Tarea("Proporciónale juguetes de estimulación mental, por lo menos 5 veces.", 100, logroBienestarMental, salud),
                //---//
                new Tarea("Realiza una rutina diaria de ejercicios que incluya al menos 20 minutos de saltos y carreras, al menos 5 veces.", 100, logroAtletaCanino, ejercicio),
                //---//
                new Tarea("LLeva a tu mascota al menos 3 veces a un parque y realiza ejercicios simples (puedes usar una pelota)", 100, logroRutaNaturaleza, ejercicio),
                //---//
                new Tarea("Saltos sobre rocas y sesiones de entrenamiento en un parque. al menos 5 veces", 100, logroDesafíoSalto, ejercicio),
                //---//
                new Tarea("Mantén cada estiramiento durante al menos 15 segundos para mejorar la flexibilidad de tu mascota. al menos 3 veces.", 100, logroEstiramientoCanino, ejercicio),
                //---//
                new Tarea("Anima a tu mascota a interactuar amigablemente, compartiendo juguetes virtuales y participando en actividades sociales.", 100, logroMaestroSocialización, entrenamiento),
                //---//
                new Tarea("Organiza juegos y actividades interactivas para que todos participen. Asegúrate de que tu mascota socialice con entusiasmo y sea el centro de atención", 100, logroReyFiestas, entrenamiento),
                //---//
                new Tarea("Facilita una reconciliación exitosa a través de interacciones amigables. Logra que ambas mascotas convivan pacíficamente después del conflicto.",100,logroDiplomaciaPeluda, entrenamiento),
                //---//
                new Tarea("Proporciónele consuelo, juega con ella y asegúrate de que se sienta apoyada.",100,logroGuardiánSocial, entrenamiento)
        );


        // Guardar tareas en la base de datos
        tareaRepository.saveAll(tareas);




        Tarea tarea1 = tareaRepository.findByNombreTarea("Establece horarios de alimentación regulares para mantener la salud y la rutina de tu mascota. Sigue el horario de alimentación establecido").orElse(null);
        Tarea tarea2 = tareaRepository.findByNombreTarea("Crear un plan de comidas equilibrado.").orElse(null);
        Tarea tarea3 = tareaRepository.findByNombreTarea("Introduce nuevos alimentos en la dieta de tu mascota.").orElse(null);
        Tarea tarea4 = tareaRepository.findByNombreTarea("Mide con precisión las porciones de comida al menos 5 veces.").orElse(null);
        Tarea tarea5 = tareaRepository.findByNombreTarea("Juega con tu mascota usando un juguete interactivo, por lo menos 5 veces.").orElse(null);
        Tarea tarea6 = tareaRepository.findByNombreTarea("Cepilla los dientes de tu mascota. 5 veces").orElse(null);
        Tarea tarea7 = tareaRepository.findByNombreTarea("Lleva a tu mascota a dar un paseo").orElse(null);
        Tarea tarea8 = tareaRepository.findByNombreTarea("Proporciónale juguetes de estimulación mental, por lo menos 5 veces.").orElse(null);
        Tarea tarea9 = tareaRepository.findByNombreTarea("Realiza una rutina diaria de ejercicios que incluya al menos 20 minutos de saltos y carreras, al menos 5 veces.").orElse(null);
        Tarea tarea10 = tareaRepository.findByNombreTarea("LLeva a tu mascota al menos 3 veces a un parque y realiza ejercicios simples (puedes usar una pelota)").orElse(null);
        Tarea tarea11 = tareaRepository.findByNombreTarea("Saltos sobre rocas y sesiones de entrenamiento en un parque. al menos 5 veces").orElse(null);
        Tarea tarea12 = tareaRepository.findByNombreTarea("Mantén cada estiramiento durante al menos 15 segundos para mejorar la flexibilidad de tu mascota. al menos 3 veces.").orElse(null);
        Tarea tarea13 = tareaRepository.findByNombreTarea("Anima a tu mascota a interactuar amigablemente, compartiendo juguetes virtuales y participando en actividades sociales.").orElse(null);
        Tarea tarea14 = tareaRepository.findByNombreTarea("Organiza juegos y actividades interactivas para que todos participen. Asegúrate de que tu mascota socialice con entusiasmo y sea el centro de atención").orElse(null);
        Tarea tarea15 = tareaRepository.findByNombreTarea("Facilita una reconciliación exitosa a través de interacciones amigables. Logra que ambas mascotas convivan pacíficamente después del conflicto.").orElse(null);
        Tarea tarea16 = tareaRepository.findByNombreTarea("Proporciónele consuelo, juega con ella y asegúrate de que se sienta apoyada.").orElse(null);

        List<Accion> requisitos = List.of(
                new Accion(logroChefCanino, tarea1, 1, 0),
                new Accion(logroChefCanino, tarea2, 1, 0),
                new Accion(logroDietaEspecializada, tarea3, 1, 0),
                new Accion(logroMaestroPorciones, tarea4, 5, 0),

                new Accion(logroEnergíaFelina, tarea5, 5, 0),
                new Accion(logroSaludDental, tarea6, 5, 0),
                new Accion(logroSaltoAlegre, tarea7, 1, 0),
                new Accion(logroBienestarMental, tarea8, 5, 0),

                new Accion(logroAtletaCanino, tarea9, 5, 0),
                new Accion(logroRutaNaturaleza, tarea10, 3, 0),
                new Accion(logroDesafíoSalto, tarea11, 5, 0),
                new Accion(logroEstiramientoCanino, tarea12, 3, 0),

                new Accion(logroMaestroSocialización, tarea13, 1, 0),
                new Accion(logroReyFiestas, tarea14, 1, 0),
                new Accion(logroDiplomaciaPeluda, tarea15, 1, 0),
                new Accion(logroGuardiánSocial, tarea16, 1, 0)

        );


        // Guardar tareas en la base de datos
        accionRepository.saveAll(requisitos);


        // Lista de nombres de tipos de mascotas
        List<String> nombresTipos = List.of("Perro", "Gato");

        // Convertir los nombres de tipos en objetos TipoMascota
        List<TipoMascota> tiposMascota = nombresTipos.stream()
                .map(TipoMascota::new)
                .collect(Collectors.toList());

        // Guardar los objetos TipoMascota en la base de datos
        repositorioTipoMascota.saveAll(tiposMascota);


        List<Consejo> consejos = List.of(

                new Consejo("Tener una mascota de manera responsable comienza con visitas regulares al veterinario.")
        );

        repositorioConsejo.saveAll(consejos);




    }
}