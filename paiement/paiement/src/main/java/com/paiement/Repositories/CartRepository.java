package com.paiement.Repositories;

import com.paiement.Entities.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
