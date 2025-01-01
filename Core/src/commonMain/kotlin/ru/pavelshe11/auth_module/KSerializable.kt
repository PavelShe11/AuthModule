package ru.pavelshe11.auth_module

import kotlinx.serialization.KSerializer

abstract class KSerializable<T> {
    abstract val serializer: KSerializer<T>
}