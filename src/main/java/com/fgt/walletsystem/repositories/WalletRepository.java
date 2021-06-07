package com.fgt.walletsystem.repositories;

import com.fgt.walletsystem.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findWalletById (long id);
    Optional<Wallet> findById(long id);
}
