package br.com.zup.pix.read

import br.com.zup.KeymgrInternReadRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class ReadRequest(
    @field:NotBlank val id: Int?,
    @field:NotBlank @field:Size(max = 77) val clientId: String?
) {
    fun toGrpcRequest(): KeymgrInternReadRequest = KeymgrInternReadRequest.newBuilder()
        .setId(id!!)
        .setClientId(clientId)
        .build()
}