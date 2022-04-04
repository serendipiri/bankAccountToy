package com.dkb.codefactory.bankAccountToy.transaction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TransactionRepository : JpaRepository<Transaction, Long> {

    @Query("select t.id," +
            " t.amount," +
            " t.source_balance," +
            " t.date," +
            " t.operation_type," +
            " t.target_iban," +
            " t.source_iban " +
            " from transaction t" +
            " where t.target_iban = :iban " +
            "   and t.amount > 0 " +
            "union all " +
            "select t2.id," +
            " t2.amount," +
            " t2.source_balance," +
            " t2.date," +
            " t2.operation_type," +
            " t2.target_iban," +
            " t2.source_iban" +
            " from transaction t2" +
            " where t2.source_iban = :iban " +
            "   and t2.amount < 0 ",
        nativeQuery = true)
    fun findTransactionsByIban(iban: Long): List<Transaction>

}