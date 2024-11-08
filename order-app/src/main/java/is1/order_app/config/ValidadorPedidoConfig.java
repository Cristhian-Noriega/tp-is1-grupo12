package is1.order_app.config;

import is1.order_app.service.rule_service.ValidadorPedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidadorPedidoConfig {
    @Bean
    public ValidadorPedido validadorPedido(@Value("${validation.rules.path}") String path) throws  Exception {
        return new ValidadorPedido(path);
    }
}
