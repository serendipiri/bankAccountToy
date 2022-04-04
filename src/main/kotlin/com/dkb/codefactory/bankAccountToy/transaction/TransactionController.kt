package com.dkb.codefactory.bankAccountToy.transaction

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping("/{iban}")
    fun getTransactions(@PathVariable iban: Long): List<Transaction> =
        transactionService.getAllTransactionsByIban(iban)

}