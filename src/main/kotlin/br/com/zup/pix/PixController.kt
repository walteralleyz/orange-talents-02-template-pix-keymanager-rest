package br.com.zup.pix

import br.com.zup.*
import br.com.zup.exception.makeException
import br.com.zup.pix.all.AllRequest
import br.com.zup.pix.all.AllResponse
import br.com.zup.pix.read.ReadRequest
import br.com.zup.pix.read.ReadResponse
import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.registry.RegistryResponse
import br.com.zup.pix.remove.RemoveRequest
import br.com.zup.pix.remove.RemoveResponse
import br.com.zup.utils.toAPIResponse
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/api/pix")
class PixController(
    private val grpcRead: KeymgrInternReadServiceGrpc.KeymgrInternReadServiceBlockingStub,
    private val grpcRegistry: KeymgrRegistryServiceGrpc.KeymgrRegistryServiceBlockingStub,
    private val grpcRemove: KeymgrRemoveServiceGrpc.KeymgrRemoveServiceBlockingStub,
    private val grpcReadAll: KeymgrReadAllServiceGrpc.KeymgrReadAllServiceBlockingStub
) {

    @Post
    fun registry(@Valid @Body req: RegistryRequest): HttpResponse<RegistryResponse> {
        return (grpcGenericRequest { grpcRegistry.registry(req.toGrpcRequest()) } as KeymgrRegistryResponse)
            .let { HttpResponse.ok(it.toAPIResponse()) }
    }

    @Get("/{id}/{clientId}")
    fun read(@PathVariable id: Int, @PathVariable clientId: String): HttpResponse<ReadResponse> {
        return (grpcGenericRequest { grpcRead.read(ReadRequest(id, clientId).toGrpcRequest()) } as KeymgrReadResponse)
            .let { HttpResponse.ok(it.toAPIResponse()) }
    }

    @Get("/{clientId}")
    fun readAll(@PathVariable clientId: String): HttpResponse<AllResponse> {
        return (grpcGenericRequest { grpcReadAll.readAll(AllRequest(clientId).toGrpcRequest()) } as KeymgrReadAllResponse)
            .let { HttpResponse.ok(it.toAPIResponse()) }
    }

    @Delete
    fun remove(@Valid @Body req: RemoveRequest): HttpResponse<RemoveResponse> {
        return (grpcGenericRequest { grpcRemove.remove(req.toGrpcRequest()) } as KeymgrExcludeResponse)
            .let { HttpResponse.ok(it.toAPIResponse()) }
    }

    fun grpcGenericRequest(f: () -> Any): Any {
        return try { f() } catch (e: StatusRuntimeException) { throw makeException(e) }
    }
}