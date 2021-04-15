package br.com.zup.pix.read

import br.com.zup.KeyType

data class ReadResponse(
    val id: Int,
    val clientId: String,
    val pixType: KeyType,
    val pix: String,
    val createdAt: String
)