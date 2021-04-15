package br.com.zup

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import javax.inject.Singleton

@Singleton
class Client {
    private val application: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    private val client: HttpClient = HttpClient.create(application.url)

    fun httpClient() = client

    inline fun <T, reified R> post(url: String, obj: T): R {
        return httpClient().toBlocking().retrieve(HttpRequest.POST(url, obj), R::class.java)
    }

    inline fun <T, reified R> delete(url: String, obj: T): R {
        return httpClient().toBlocking().retrieve(HttpRequest.DELETE(url, obj), R::class.java)
    }

    inline fun <reified T, reified R> get(url: String): R {
        return httpClient().toBlocking().retrieve<T, R>(HttpRequest.GET(url), R::class.java)
    }
}