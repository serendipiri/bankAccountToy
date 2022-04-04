package com.dkb.codefactory.bankAccountToy.account

import com.dkb.codefactory.bankAccountToy.account.dto.AccountRequestDTO
import com.dkb.codefactory.bankAccountToy.account.dto.OperationRequestDTO
import com.dkb.codefactory.bankAccountToy.enums.AccountType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/balance/{iban}")
    fun showBalance(@PathVariable iban: Long): BigDecimal = accountService.getBalance(iban)

    @GetMapping
    fun getAccounts(@RequestBody accountTypeList: Set<AccountType>): List<Account> =
        accountService.getAccounts(accountTypeList)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody accountRequest: AccountRequestDTO): Account? =
        accountService.saveAccount(accountRequest.toAccount())

    @PatchMapping("/block/{iban}")
    fun blockAccount(@PathVariable iban: Long): Account =
        accountService.blockAccount(iban)

    @PostMapping("/deposit")
    fun deposit(@RequestBody operation: OperationRequestDTO): Account =
        accountService.deposit(operation)

    @PostMapping("/transfer")
    fun transfer(@RequestBody operation: OperationRequestDTO): Account =
        accountService.transfer(operation)

}