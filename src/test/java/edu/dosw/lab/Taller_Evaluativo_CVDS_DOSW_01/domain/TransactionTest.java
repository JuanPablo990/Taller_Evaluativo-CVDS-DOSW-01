package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionCreationAndGetters() {
        String id = "t1";
        LocalDate date = LocalDate.of(2025, 1, 1);
        String description = "Compra";
        BigDecimal amount = new BigDecimal("150.50");

        Transaction transaction = new Transaction(id, date, description, amount);

        assertAll("Transaction properties",
                () -> assertEquals(id, transaction.id()),
                () -> assertEquals(date, transaction.date()),
                () -> assertEquals(description, transaction.description()),
                () -> assertEquals(amount, transaction.amount())
        );
    }

    @Test
    void testTransactionNotNullFields() {

        Transaction transaction = new Transaction("t2", LocalDate.now(), "Venta", BigDecimal.TEN);


        assertNotNull(transaction.id());
        assertNotNull(transaction.date());
        assertNotNull(transaction.description());
        assertNotNull(transaction.amount());
    }
}

