package com.example.rally.data


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Account(
    val name:String,
    val number: Int,
    val balance:Float,
    val color:Color
)

@Immutable
data class Bill(
    val name:String,
    val due: String,
    val amount:Float,
    val color:Color
)

object UserData {
    val accounts:List<Account> = listOf(
        Account(
            "Checking",
            1234,
            2215.13f,
            Color(0xFF004948)
        ),
        Account(
            "Home Savings",
            5678,
            8676.88f,
            Color(0xFF04B97F)
        ),
        Account(
            "Vacation",
            3456,
            253f,
            Color(0xFF37EFBA)
        )
    )
    val bills: List<Bill> = listOf(
        Bill(
            "夏帆",
            "jan19",
            45.36f,
            Color(0xFFFFDC78)
        ),
        Bill(
            "Rent",
            "Fed 9",
            1200f,
            Color(0xFFFF6951)
        )
    )

    fun getAccount(accountName:String?):Account{
        return accounts.first{it.name == accountName}
    }
}