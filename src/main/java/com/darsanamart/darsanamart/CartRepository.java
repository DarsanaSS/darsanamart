package com.darsanamart.darsanamart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUsernameAndProductName(
            String username,
            String productName
    );

    List<Cart> findByUsername(String username);

    Optional<Cart> findByIdAndUsername(Long id, String username);
}