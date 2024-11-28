package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;
import is1.order_app.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidadorPresentacionTest {
    private ValidadorPedido validador;

    @BeforeEach
    public void setup() throws Exception {
        validador = new ValidadorPedido("src/main/resources/presentacion_rules.json");
    }

    @Test
    public void TestRegla1Pasa() {
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("peso", 8);
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 1);
        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(0, errores.size());
    }

    @Test
    public void TestRegla1NoPasa() {
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("peso", 8);
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 2);
        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(1, errores.size());
        assertEquals("El peso total de los productos debe ser menos de 15 kg.", errores.get(0));
    }

    @Test
    public void TestRegla2Pasa() {
        Product producto1 = new Product();
        producto1.setName("Tanque de Oxigeno");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("tipo", "gaseoso");
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 1);
        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);

        assertEquals(0, errores.size());
    }

    @Test
    public void TestRegla2NoPasa() {
        Product producto1 = new Product();
        producto1.setName("Tanque de Oxigeno");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("tipo", "gaseoso");
        producto1.setProductExtraAtributtes(extraAttributes);

        Product producto2 = new Product();
        producto2.setName("Agua");
        HashMap<String, Object> extraAttributes2 = new HashMap<>();
        extraAttributes2.put("tipo", "liquido");
        producto2.setProductExtraAtributtes(extraAttributes2);

        OrderItem item1 = new OrderItem(producto1, 2);
        OrderItem item2 = new OrderItem(producto2, 4);

        List<OrderItem> productos = Arrays.asList(item1, item2);

        List<String> errores = validador.validar(productos);
        assertEquals(1, errores.size());
    }

    @Test
    public void TestRegla3NoPasa() {
        Product producto1 = new Product();
        producto1.setName("Celular Motorola e34");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("sector", "electrodomesticos");
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 3);

        List<OrderItem> productos = Arrays.asList(item1);

        List<String> errores = validador.validar(productos);
        assertEquals(1, errores.size());
    }

    @Test
    public void TestRegla4Pasa() {
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("promocion", "true");
        extraAttributes.put("precio", 8000);
        producto1.setProductExtraAtributtes(extraAttributes);

        Product producto2 = new Product();
        producto2.setName("Celular Ultimo modelo");
        HashMap<String,Object> extraAttributes2 = new HashMap<>();
        extraAttributes2.put("promocion", "false");
        extraAttributes2.put("precio", 15000);
        producto2.setProductExtraAtributtes(extraAttributes2);

        OrderItem item1 = new OrderItem(producto1, 1);
        OrderItem item2 = new OrderItem(producto2, 1);

        List<OrderItem> productos = Arrays.asList(item1, item2);
        List<String> errores = validador.validar(productos);
        assertEquals(0, errores.size());
    }

    @Test
    public void TestRegla4NoPasa() {
        Product producto1 = new Product();
        producto1.setName("Laptop");
        HashMap<String,Object> extraAttributes = new HashMap<>();
        extraAttributes.put("promocion", "true");
        extraAttributes.put("precio", 8000);
        producto1.setProductExtraAtributtes(extraAttributes);

        OrderItem item1 = new OrderItem(producto1, 1);

        List<OrderItem> productos = Arrays.asList(item1);
        List<String> errores = validador.validar(productos);
        assertEquals(1, errores.size());
    }
}
