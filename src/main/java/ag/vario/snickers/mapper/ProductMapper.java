package ag.vario.snickers.mapper;

import ag.vario.snickers.dto.ProductDTO;
import ag.vario.snickers.model.Product;

public class ProductMapper {

    public static ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCount(product.getCount());
        return productDTO;
    }

    public static Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();
        //product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCount(productDTO.getCount());
        return product;
    }

    public static Product merge(ProductDTO source, Product target) {
        target.setName(source.getName());
        target.setCount(source.getCount());
        return target;
    }
}
