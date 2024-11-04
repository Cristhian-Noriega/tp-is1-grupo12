package is1.order_app.config;

import is1.order_app.utils.AttributeParser;
import is1.order_app.utils.TypeMappingLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public TypeMappingLoader typeMappingLoader() {
        return new TypeMappingLoader();
    }

    @Bean
    public AttributeParser attributeParser(TypeMappingLoader typeMappingLoader) {
        return new AttributeParser(typeMappingLoader);
    }
}
