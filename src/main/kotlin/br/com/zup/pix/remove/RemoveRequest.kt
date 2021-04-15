package br.com.zup.pix.remove

import br.com.zup.KeymgrExcludeRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class RemoveRequest(
    @field:NotBlank @field:Size(max = 77) val pix: String?,
    @field:NotBlank val clientId: String?
) {
    fun toGrpcRequest(): KeymgrExcludeRequest = KeymgrExcludeRequest.newBuilder()
        .setPix(pix)
        .setClientId(clientId)
        .build()
}