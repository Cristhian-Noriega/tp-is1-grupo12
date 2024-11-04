package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;
import is1.order_app.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidadorPedidoTest {
    private ValidadorPedido validador;

    @BeforeEach
    public void setup() throws Exception {
        // Inicializar el validador con el path al archivo de reglas de prueba
        validador = new ValidadorPedido("src/test/resources/testrules.json");
    }

    @Test
    public void testValidar_ConReglasQueFallan() {
        // Configura algunos productos para que provoquen errores en las reglas
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "electronica");
        extraAttributes.put("precio", 200);
        producto1.setProductExtraAtributtes(extraAttributes);

        Product producto2 = new Product();
        producto2.setName("Celular");
        HashMap<String,Object> extraAttributes2 = new HashMap<>();
        extraAttributes2.put("categoria", "electronica");
        extraAttributes2.put("precio", 300);
        producto2.setProductExtraAtributtes(extraAttributes2);

        OrderItem item1 = new OrderItem(producto1, 3);
        OrderItem item2 = new OrderItem(producto2, 3);
        List<OrderItem> productos = Arrays.asList(item1, item2);

        List<String> errores = validador.validar(productos);

        assertEquals(1, errores.size());
        assertEquals("No puede haber más de 5 productos de la categoría Electrónica", errores.get(0));
    }

    @Test
    public void testValidar_ConReglasQueNoFallen() {
        // Configura productos que cumplan con las reglas
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "electronica");
        extraAttributes.put("precio", 200);
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 1);
        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(0, errores.size());
    }
}
