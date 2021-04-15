package br.com.zup.pix

import br.com.zup.Client
import br.com.zup.ModelFactory
import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveRequest
import br.com.zup.pix.remove.RemoveResponse
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class RemoveTest(
    private val client: Client,
    private val model: ModelFactory
) {

    @Test
    fun `should remove a registered pix`() {
        client.post<RegistryRequest, RegistryResponse>("/api/pix", model.makeRegistry())

        client.delete<RemoveRequest, RemoveResponse>("/api/pix", model.makeRemove())
            .let { response ->
                assertNotNull(response)
                assertEquals("12345678911", response.pix)
            }
    }
}