package br.com.zup.clients

import br.com.zup.KeymgrInternReadServiceGrpc
import br.com.zup.KeymgrReadAllServiceGrpc
import br.com.zup.KeymgrRegistryServiceGrpc
import br.com.zup.KeymgrRemoveServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

private const val location = "\${grpc-host}"

@Factory
class Clients(
    @GrpcChannel(location)
    private val channel: ManagedChannel
) {
    @Singleton
    fun registryStub(): KeymgrRegistryServiceGrpc.KeymgrRegistryServiceBlockingStub {
        return KeymgrRegistryServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun removeStub(): KeymgrRemoveServiceGrpc.KeymgrRemoveServiceBlockingStub {
        return KeymgrRemoveServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun readStub(): KeymgrInternReadServiceGrpc.KeymgrInternReadServiceBlockingStub {
        return KeymgrInternReadServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun readAllStub(): KeymgrReadAllServiceGrpc.KeymgrReadAllServiceBlockingStub {
        return KeymgrReadAllServiceGrpc.newBlockingStub(channel)
    }
}