package is1.order_app.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    private Product product;
    private Map<String, Object> extraAttributes;

    @BeforeEach
    void setUp() {
        extraAttributes = new HashMap<>();
        extraAttributes.put("color", "rojo");
        extraAttributes.put("size", 32);
        extraAttributes.put("peso", 150.4);

        product = new Product();
        product.setName("Remera Gap mediana");
        product.setDescription("Remera marca Gap tamanio mediano muy comoda");
        product.setBrand("Gap");
        product.setProductExtraAtributtes(extraAttributes);
        product.setStock(100);
    }

    @Test
    void testAtributoBasicoString() {
        assertEquals("Gap", product.getBrand());
    }

    @Test
    void testAtributoBasicoInteger() {
        assertEquals(100, product.getStock());
    }

    @Test
    void testAtributoComplejoString() {
        assertEquals("rojo", product.get("color"));
    }

    @Test
    void testAtributoComplejoInteger() {
        assertEquals(32, Double.parseDouble((String) product.get("size")));
    }

    @Test
    void testAtributoComplejoDouble() {
        assertEquals(150.4, Double.parseDouble((String) product.get("peso")));
    }

    @Test
    void testAtributoNoExiste() {
        assertNull(extraAttributes.get("material"));
    }

    @Test
    void testAgregarAtributos() {
        extraAttributes.put("material", "algodon");
        product.setProductExtraAtributtes(extraAttributes);
        assertEquals("algodon", extraAttributes.get("material"));
    }
}
