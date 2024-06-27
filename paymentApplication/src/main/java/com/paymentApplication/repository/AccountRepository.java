package com.paymentApplication.repository;

import com.paymentApplication.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("""
            select a
            from Account a
            join fetch a.user u
            where u.clientId = :userClientId
            """)
    Optional<Account> getResponse(Long userClientId);

    @Modifying
    @Query("""
            update Account a
            set a.balance = :balance
            where a.id = :accountId
            """)
    void updateBalance(BigDecimal balance, Long accountId);
}
