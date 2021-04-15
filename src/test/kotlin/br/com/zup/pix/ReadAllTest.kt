package br.com.zup.pix

import br.com.zup.Client
import br.com.zup.ModelFactory
import br.com.zup.pix.all.AllRequest
import br.com.zup.pix.all.AllResponse
import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveRequest
import br.com.zup.pix.remove.RemoveResponse
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@MicronautTest
class ReadAllTest(
    private val client: Client,
    private val model: ModelFactory
) {

    @Test
    fun `should retrieve all registries from client`() {
        val mock = model.makeRegistry()

        client.post<RegistryRequest, RegistryResponse>("/api/pix", mock)

        with(mock) {
            client.post<RegistryRequest, RegistryResponse>("/api/pix", RegistryRequest(
                clientId,
                keyType,
                "11122233344",
                accountType
            ))
        }

        client.get<AllRequest, AllResponse>("/api/pix/${model.makeRegistry().clientId}").let {
            assertTrue(it.response.size == 2)
            assertEquals(model.makeRegistry().clientId, it.response[0].clientId)
        }

        client.delete<RemoveRequest, RemoveResponse>("/api/pix", RemoveRequest(mock.pix, mock.clientId))
        client.delete<RemoveRequest, RemoveResponse>("/api/pix", RemoveRequest("11122233344", mock.clientId))
    }
}