package pe.edu.upao.petcare.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and() // Habilitamos CORS para todas las fuentes. Asegúrate de configurar CorsConfigurationSource si es necesario.
                .csrf().disable() // Deshabilitamos CSRF ya que no es necesario con JWT o aplicaciones sin estado.
                .authorizeRequests()
                .anyRequest().permitAll() // Permitimos todas las peticiones sin autenticación.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configuramos la política de sesión como sin estado.

        return httpSecurity.build();
    }
}