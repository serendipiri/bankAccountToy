package com.dkb.codefactory.bankAccountToy.account

import com.dkb.codefactory.bankAccountToy.enums.AccountType
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iban", nullable = false)
    var iban: Long? = null,

    @Enumerated(EnumType.STRING)
    val type: AccountType = AccountType.CHECKING,

    val title: String = "",

    var balance: BigDecimal = BigDecimal(0),

    var blocked: Boolean? = false,

    var checkingIbanForSavings : Long?
)




