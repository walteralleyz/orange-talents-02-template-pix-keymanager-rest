package br.com.zup.exception

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

fun makeException(e: StatusRuntimeException): HttpStatusException {
    return when (e.status.code) {
        Status.NOT_FOUND.code -> HttpStatusException(
            HttpStatus.NOT_FOUND,
            e.status.description
        )

        Status.ALREADY_EXISTS.code -> HttpStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY,
            e.status.description
        )

        Status.INVALID_ARGUMENT.code -> HttpStatusException(
            HttpStatus.NOT_ACCEPTABLE,
            e.status.description
        )

        Status.PERMISSION_DENIED.code -> HttpStatusException(
            HttpStatus.FORBIDDEN,
            e.status.description
        )

        else -> HttpStatusException(HttpStatus.valueOf(e.status.code.value()), e.status.description)
    }
}