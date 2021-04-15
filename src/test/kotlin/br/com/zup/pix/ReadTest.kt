package br.com.zup.pix

import br.com.zup.Client
import br.com.zup.ModelFactory
import br.com.zup.pix.read.ReadRequest
import br.com.zup.pix.read.ReadResponse
import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveRequest
import br.com.zup.pix.remove.RemoveResponse
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class ReadTest(
    private val client: Client,
    private val model: ModelFactory
) {

    @Test
    fun `should retrieve a registered pix`() {
        val response = client.post<RegistryRequest, RegistryResponse>("/api/pix", model.makeRegistry())

        client.get<ReadRequest, ReadResponse>("/api/pix/${response.id}/c56dfef4-7901-44fb-84e2-a2cefb157890")
            .run {
                assertNotNull(this)
                assertNotNull(this.pix)
            }

        client.delete<RemoveRequest, RemoveResponse>("/api/pix", model.makeRemove())
    }
}