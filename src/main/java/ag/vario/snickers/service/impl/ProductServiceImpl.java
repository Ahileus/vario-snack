package ag.vario.snickers.service.impl;

import ag.vario.snickers.dto.ProductDTO;
import ag.vario.snickers.mapper.ProductMapper;
import ag.vario.snickers.model.Money;
import ag.vario.snickers.model.Product;
import ag.vario.snickers.repository.ProductRepository;
import ag.vario.snickers.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.mapToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product foundProduct = this.findProductById(productId);
        Product product = ProductMapper.merge(productDTO, foundProduct);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToProductDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = this.findProductById(productId);
        return ProductMapper.mapToProductDTO(product);

    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = this.findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapToProductDTO).toList();
    }

    private Product findProductById(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
