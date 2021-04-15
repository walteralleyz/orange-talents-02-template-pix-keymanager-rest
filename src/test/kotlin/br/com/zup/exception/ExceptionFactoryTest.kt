package br.com.zup.exception

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ExceptionFactoryTest {

    @Test
    fun `should return an exception status`() {
        val exception = makeException(StatusRuntimeException(Status.ALREADY_EXISTS))

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.code, exception.status.code)
    }
}