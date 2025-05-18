package net.achraf.activite2.repository;

import net.achraf.activite2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface ProductRepository extends JpaRepository<Product, Long> {
}
