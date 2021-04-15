package br.com.zup.grpc

import br.com.zup.*
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GrpcTest(
    private val grpcRegistry: KeymgrRegistryServiceGrpc.KeymgrRegistryServiceBlockingStub,
    private val grpcRemove: KeymgrRemoveServiceGrpc.KeymgrRemoveServiceBlockingStub,
    private val grpcRead: KeymgrInternReadServiceGrpc.KeymgrInternReadServiceBlockingStub,
    private val model: ModelFactory
) {

    @Test
    fun `should registry at grpc server`() {
        grpcRegistry.registry((model.makeRegistry()).toGrpcRequest())
            .let { response: KeymgrRegistryResponse ->
                assertNotNull(response)
                assertTrue(response.id > 0)
            }

        grpcRemove.remove((model.makeRemove()).toGrpcRequest())
    }

    @Test
    fun `should retrieve a registered pix`() {
        val response = grpcRegistry.registry((model.makeRegistry()).toGrpcRequest())

        grpcRead.read(KeymgrInternReadRequest.newBuilder()
            .setId(response.id)
            .setClientId("c56dfef4-7901-44fb-84e2-a2cefb157890")
            .build()
        ).let {
            assertNotNull(it)
            assertEquals("12345678911", it.pix)
        }

        grpcRemove.remove((model.makeRemove()).toGrpcRequest())
    }
}