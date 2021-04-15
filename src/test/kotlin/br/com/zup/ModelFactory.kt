package br.com.zup

import br.com.zup.pix.registry.RegistryRequest
import br.com.zup.pix.remove.RemoveRequest
import javax.inject.Singleton

@Singleton
class ModelFactory {
    fun makeRegistry(): RegistryRequest = RegistryRequest(
        "c56dfef4-7901-44fb-84e2-a2cefb157890",
        KeyType.CPF,
        "12345678911",
        AccountType.CACC
    )

    fun makeRemove(): RemoveRequest = RemoveRequest(
        "12345678911",
        "c56dfef4-7901-44fb-84e2-a2cefb157890"
    )
}