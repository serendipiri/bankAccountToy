package com.dkb.codefactory.bankAccountToy.transaction

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.enums.OperationType
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    val amount: BigDecimal,
    val sourceBalance: BigDecimal?,
    val date: LocalDate,

    @Enumerated(EnumType.STRING)
    val operationType: OperationType,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "source_iban")
    val source: Account?,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "target_iban")
    val target: Account?

) {

}
