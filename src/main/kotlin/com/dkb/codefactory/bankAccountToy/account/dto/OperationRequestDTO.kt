package com.dkb.codefactory.bankAccountToy.account.dto

import com.dkb.codefactory.bankAccountToy.enums.OperationType
import java.math.BigDecimal

data class OperationRequestDTO(
    val to: Long,
    var amount: BigDecimal,
    var from: Long//,
    //val operation: OperationType
) {
}