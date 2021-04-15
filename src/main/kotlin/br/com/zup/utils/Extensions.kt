package br.com.zup.utils

import br.com.zup.*
import br.com.zup.pix.all.AllResponse
import br.com.zup.pix.all.Response
import br.com.zup.pix.read.ReadResponse
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveResponse

fun KeymgrRegistryResponse.toAPIResponse() = RegistryResponse(this.id)

fun KeymgrExcludeResponse.toAPIResponse() = RemoveResponse(this.pix, this.clientId)

fun KeymgrReadResponse.toAPIResponse() = ReadResponse(
    this.id,
    this.clientId,
    this.pixType,
    this.pix,
    this.createdAt
)

fun KeymgrReadAllResponse.toAPIResponse(): AllResponse {
    return this.responseList.map {
        Response(it.id, it.clientId, it.keyType, it.pix, it.accountType, it.createdAt)
    }.toTypedArray().let { responseList -> AllResponse(responseList) }
}