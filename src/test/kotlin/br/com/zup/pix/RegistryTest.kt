package br.com.zup.pix

import br.com.zup.Client
import br.com.zup.ModelFactory
import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveRequest
import br.com.zup.pix.remove.RemoveResponse
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class RegistryTest(
    private val client: Client,
    private val model: ModelFactory
) {

    @Test
    fun `should register a new pix`() {
        client.post<RegistryRequest, RegistryResponse>("/api/pix", model.makeRegistry())
            .let { response ->
                assertNotNull(response)
                assertTrue(response.id > 0)
            }

        client.delete<RemoveRequest, RemoveResponse>("/api/pix", model.makeRemove())
    }
}