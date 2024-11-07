package com.yuliitezary.gpt

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.activity.OnBackPressedCallback
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.ViewCompat
import android.view.WindowManager
import android.graphics.Rect
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import android.widget.Toast
import com.google.firebase.perf.metrics.AddTrace
import com.google.firebase.perf.FirebasePerformance

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var adView: AdView
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private var originalHeight = 0

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            filePathCallback?.onReceiveValue(arrayOf(it))
        } ?: filePathCallback?.onReceiveValue(null)
        filePathCallback = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Скрываем статус бар
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        
        setContentView(R.layout.activity_main)

        // Настраиваем обработку клавиатуры
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, windowInsets ->
            val imeHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            webView.translationY = -imeHeight.toFloat()
            WindowInsetsCompat.CONSUMED
        }

        setupWebView()
        setupBackHandler()
        setupBannerAd()

        requestNotificationPermission()
        getFCMToken()

        // Подписываем пользователя на тему при запуске приложения
        FirebaseMessaging.getInstance().subscribeToTopic("all_users")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Успешно подписались на уведомления")
                }
            }
    }

    private fun setupWebView() {
        webView = findViewById(R.id.webView)
        webView.webViewClient = CustomWebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                this@MainActivity.filePathCallback = filePathCallback
                getContent.launch("*/*")
                return true
            }
        }

        val settings = webView.settings
        settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            loadsImagesAutomatically = true
        }

        // Инжектируем CSS и JavaScript для фиксации поля ввода
        val js = """
            function fixInputPosition() {
                // Добавляем стили
                var style = document.createElement('style');
                style.textContent = `
                    .input-box-container {
                        position: fixed !important;
                        bottom: 0 !important;
                        left: 0 !important;
                        right: 0 !important;
                        z-index: 999999 !important;
                        background: white !important;
                        padding: 10px !important;
                    }
                    #message-input {
                        position: relative !important;
                        bottom: 0 !important;
                    }
                    .chat-container {
                        margin-bottom: 60px !important;
                    }
                `;
                document.head.appendChild(style);
                
                // Находим поле ввода и его контейнер
                const messageInput = document.querySelector('textarea, input[type="text"]');
                if (messageInput) {
                    const container = messageInput.closest('div');
                    if (container) {
                        container.classList.add('input-box-container');
                    }
                    
                    // Добавляем обработчик фокуса
                    messageInput.addEventListener('focus', function() {
                        setTimeout(() => {
                            window.scrollTo(0, 0);
                            messageInput.scrollIntoView(false);
                        }, 100);
                    });
                }
            }

            // Запускаем функцию после загрузки страницы
            if (document.readyState === 'complete') {
                fixInputPosition();
            } else {
                window.addEventListener('load', fixInputPosition);
            }

            // Запускаем периодически для динамически добавляемых элементов
            setInterval(fixInputPosition, 1000);
        """.trimIndent()

        webView.evaluateJavascript(js, null)

        // Загружаем страницу
        webView.loadUrl("https://chatgptchatapp.com/")
    }

    private fun setupBackHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        
        val notGrantedPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        
        if (notGrantedPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                notGrantedPermissions.toTypedArray(),
                100
            )
        }
    }

    private fun setupBannerAd() {
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Ошибка получения FCM токена", task.exception)
                return@addOnCompleteListener
            }

            // Получаем новый FCM токен и только логируем его
            val token = task.result
            Log.d(TAG, "FCM Token: $token")
        }
    }

    // Отслеживание времени выполнения метода
    @AddTrace(name = "loadData")
    private fun loadData() {
        // Ваш код
    }

    // Пользовательская трассировка
    private fun customTrace() {
        val trace = FirebasePerformance.getInstance().newTrace("custom_trace")
        trace.start()
        
        // Ваш код для измерения
        
        trace.stop()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val PERMISSION_REQUEST_CODE = 101
    }
}
