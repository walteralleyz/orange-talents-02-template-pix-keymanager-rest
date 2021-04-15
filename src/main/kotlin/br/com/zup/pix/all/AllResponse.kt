package br.com.zup.pix.all

import br.com.zup.AccountType
import br.com.zup.KeyType

data class Response(
    val id: Int,
    val clientId: String,
    val keyType: KeyType,
    val pix: String,
    val accountType: AccountType,
    val createdAt: String
)

data class AllResponse(val response: Array<Response>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AllResponse

        if (!response.contentEquals(other.response)) return false

        return true
    }

    override fun hashCode(): Int {
        return response.contentHashCode()
    }
}
