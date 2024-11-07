package com.yuliitezary.gpt

import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.http.SslError

class CustomWebViewClient : WebViewClient() {
    
    override fun onReceivedSslError(
        view: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        error?.let { sslError ->
            // Логируем информацию об ошибке
            logSslError(sslError)
            
            if (isCertificateValid(sslError)) {
                // Только для доверенных доменов
                if (isAllowedHost(view?.url)) {
                    handler?.proceed()
                    Log.d(TAG, "SSL: Продолжаем для доверенного хоста ${view?.url}")
                } else {
                    handler?.cancel()
                    Log.w(TAG, "SSL: Отменено для недоверенного хоста ${view?.url}")
                }
            } else {
                handler?.cancel()
                Log.w(TAG, "SSL: Отменено из-за невалидного сертификата")
            }
        } ?: run {
            handler?.cancel()
            Log.e(TAG, "SSL: Ошибка null, отменяем")
        }
    }
    
    private fun isCertificateValid(error: SslError): Boolean {
        return when {
            error.hasError(SslError.SSL_UNTRUSTED) -> {
                Log.w(TAG, "SSL: Ненадёжный сертификат")
                false
            }
            error.hasError(SslError.SSL_EXPIRED) -> {
                Log.w(TAG, "SSL: Истёкший сертификат")
                false
            }
            error.hasError(SslError.SSL_IDMISMATCH) -> {
                Log.w(TAG, "SSL: Несоответствие имени хоста")
                false
            }
            error.hasError(SslError.SSL_NOTYETVALID) -> {
                Log.w(TAG, "SSL: Сертификат ещё не действителен")
                false
            }
            else -> true
        }
    }
    
    private fun isAllowedHost(url: String?): Boolean {
        return url?.let {
            // Список разрешённых доменов
            val allowedHosts = listOf(
                "chatgptchatapp.com",
                "www.chatgptchatapp.com"
            )
            allowedHosts.any { host -> url.contains(host) }
        } ?: false
    }
    
    private fun logSslError(error: SslError) {
        val certificate = error.certificate
        Log.w(TAG, """
            SSL Error:
            URL: ${error.url}
            Primary error: ${getPrimaryErrorMessage(error.primaryError)}
            Certificate:
            - Issued to: ${certificate.issuedTo}
            - Issued by: ${certificate.issuedBy}
            - Valid from: ${certificate.validNotBeforeDate}
            - Valid until: ${certificate.validNotAfterDate}
        """.trimIndent())
    }
    
    private fun getPrimaryErrorMessage(error: Int): String {
        return when (error) {
            SslError.SSL_NOTYETVALID -> "CERT_NOT_YET_VALID"
            SslError.SSL_EXPIRED -> "CERT_EXPIRED"
            SslError.SSL_IDMISMATCH -> "CERT_HOSTNAME_MISMATCH"
            SslError.SSL_UNTRUSTED -> "CERT_UNTRUSTED"
            SslError.SSL_DATE_INVALID -> "CERT_DATE_INVALID"
            SslError.SSL_INVALID -> "CERT_INVALID"
            else -> "UNKNOWN ERROR($error)"
        }
    }
    
    companion object {
        private const val TAG = "CustomWebViewClient"
    }
} 