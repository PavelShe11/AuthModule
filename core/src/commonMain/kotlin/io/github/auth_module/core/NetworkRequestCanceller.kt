package io.github.auth_module.core

/**
 * Предназначен для отмены всех запросов, которые были отправлены на сервер
 *
 * Вызывается например при разавторизации
 */
interface NetworkRequestCanceller {
    fun cancel()
}
