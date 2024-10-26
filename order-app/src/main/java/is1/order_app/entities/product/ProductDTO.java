package is1.order_app.entities.product;

record ProductDTO(
        long id,
        String name
) {
    public ProductDTO(Product product) {
        this(product.getId(), product.getName());
    }
}
