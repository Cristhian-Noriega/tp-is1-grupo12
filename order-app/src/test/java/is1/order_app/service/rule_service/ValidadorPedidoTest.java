package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;
import is1.order_app.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidadorPedidoTest {
    private ValidadorPedido validador;

    @BeforeEach
    public void setup() throws Exception {
        validador = new ValidadorPedido("src/main/resources/testrules.json");
    }

    @Test
    public void testValidar_ConReglasQueFallan() {
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
        OrderItem item2 = new OrderItem(producto2, 2);
        List<OrderItem> productos = Arrays.asList(item1, item2);

        List<String> errores = validador.validar(productos);

        assertEquals(1, errores.size());
        assertEquals("El total no puede exceder los 1000", errores.get(0));
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

    @Test
    public void testValidar_orNoFalla() {
        Product producto1 = new Product();
        producto1.setName("Lapiz Labial");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "cosmetica");
        extraAttributes.put("color", "rojo");
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 2);
        // falla que hay menos de 4 productos, pero pasa que el producto no es verde
        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(0, errores.size());
    }

    @Test
    public void testValidar_orFalla() {
        Product producto1 = new Product();
        producto1.setName("Lapiz Labial");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "cosmetica");
        extraAttributes.put("color", "verde");
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 2);

        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(1, errores.size());
    }

    @Test
    public void testValidar_andNoFalla() {
        Product producto1 = new Product();
        producto1.setName("Lapiz Labial");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "cosmetica");
        extraAttributes.put("color", "rojo");
        producto1.setProductExtraAtributtes(extraAttributes);

        Product producto2 = new Product();
        producto2.setName("rimel");
        HashMap<String,Object> extraAttributes2 = new HashMap<>();
        extraAttributes2.put("categoria", "cosmetica");
        extraAttributes2.put("color", "negro");
        producto2.setProductExtraAtributtes(extraAttributes2);

        OrderItem item1 = new OrderItem(producto1, 4);
        OrderItem item2 = new OrderItem(producto2, 2);

        List<OrderItem> productos = Arrays.asList(item1, item2);

        List<String> errores = validador.validar(productos);

        assertEquals(0, errores.size());
    }

    @Test
    public void testValidar_andFalla() {
        Product producto1 = new Product();
        producto1.setName("Lapiz Labial");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("categoria", "cosmetica");
        extraAttributes.put("color", "rojo");
        producto1.setProductExtraAtributtes(extraAttributes);

        Product producto2 = new Product();
        producto2.setName("rimel");
        HashMap<String,Object> extraAttributes2 = new HashMap<>();
        extraAttributes2.put("categoria", "cosmetica");
        extraAttributes2.put("color", "azul");
        producto2.setProductExtraAtributtes(extraAttributes2);

        OrderItem item1 = new OrderItem(producto1, 4);
        OrderItem item2 = new OrderItem(producto2, 2);

        List<OrderItem> productos = Arrays.asList(item1, item2);

        List<String> errores = validador.validar(productos);

        //no se cumple el no mix de rojo / azul
        assertEquals(1, errores.size());
    }
}
