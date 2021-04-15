package br.com.zup.pix.registry

import br.com.zup.AccountType
import br.com.zup.KeyType
import br.com.zup.KeymgrRegistryRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
data class RegistryRequest(
    @field:NotBlank val clientId: String?,
    @field:NotNull val keyType: KeyType?,
    @field:NotBlank @field:Size(max = 77) val pix: String?,
    @field:NotNull val accountType: AccountType?
) {

    fun toGrpcRequest(): KeymgrRegistryRequest = KeymgrRegistryRequest.newBuilder()
        .setClientId(clientId)
        .setPixType(keyType)
        .setPix(pix)
        .setAccountType(accountType)
        .build()
}