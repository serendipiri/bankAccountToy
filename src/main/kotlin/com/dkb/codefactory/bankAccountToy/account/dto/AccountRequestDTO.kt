package com.dkb.codefactory.bankAccountToy.account.dto

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.enums.AccountType
import java.io.Serializable

data class AccountRequestDTO(
    val title: String,
    val type: AccountType,
    var checkingAccountIban: Long?
) : Serializable {

    fun toAccount(): Account =
        Account(
            title = this.title,
            type = this.type,
            checkingIbanForSavings = this.checkingAccountIban
        )

}