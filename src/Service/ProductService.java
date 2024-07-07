package Service;

import Entity.Product;

import java.util.List;

public class ProductService {
    public static List<Product> productList;

    public Product findById(String id){
        return productList.stream()
                .filter(p->p.getId().equals(id))
                .findFirst().orElse(null);
    }
}
