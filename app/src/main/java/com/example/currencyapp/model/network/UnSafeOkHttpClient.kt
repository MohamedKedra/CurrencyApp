package com.example.currencyapp.model.network

import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import javax.net.ssl.*

class UnsafeOkHttpClient {
    companion object {
        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            try {
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
//                        return java.security.cert.X509Certificate[]{}
                    }
                })

                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { p0, p1 -> true }

                return builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}