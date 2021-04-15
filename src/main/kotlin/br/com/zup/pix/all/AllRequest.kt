package br.com.zup.pix.all

import br.com.zup.KeymgrReadAllRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class AllRequest(@field:NotBlank @field:Size(max = 77) val clientId: String) {
    fun toGrpcRequest(): KeymgrReadAllRequest = KeymgrReadAllRequest.newBuilder().setClientId(clientId).build()
}
