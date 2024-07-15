package ag.vario.snickers.service;

import ag.vario.snickers.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    ProductDTO getProductById(Long productId);
    void deleteProductById(Long productId);
    List<ProductDTO> getAllProducts();
}
