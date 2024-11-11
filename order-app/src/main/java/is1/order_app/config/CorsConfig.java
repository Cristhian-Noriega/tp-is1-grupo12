package is1.order_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las rutas
                .allowedOrigins("http://172.19.0.5:5173", "http://localhost:5173") // Permite este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permite estos métodos HTTP
                .allowedHeaders("*"); // Permite todos los encabezados
    }
}
