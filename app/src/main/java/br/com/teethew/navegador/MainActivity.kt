package br.com.teethew.navegador

import android.content.ClipData
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var url : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        // Referenciando o id do componente
        val wbvMain : WebView = findViewById(R.id.wbvMain)

        // Habilitando execução de Javascript
        wbvMain.settings.javaScriptEnabled = true

        // Carregando uma página web
        wbvMain.loadUrl("http://www.google.com")

        // Definindo o webview como client web padrão
        wbvMain.webViewClient = WebViewClient()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.searchMain -> {
            var edtMainURL: EditText = findViewById(R.id.edtMainURL)
            val wbvMain : WebView = findViewById(R.id.wbvMain)
            val urlPrefix = "http://"
            if(edtMainURL.text.toString().contains("//"))
                wbvMain.loadUrl(edtMainURL.text.toString())
            else
                wbvMain.loadUrl(urlPrefix + edtMainURL.text.toString())
            edtMainURL.setText(wbvMain.originalUrl)
            true
        }

        R.id.refreshMain -> {
            // Referenciando o id do componente
            val wbvMain : WebView = findViewById(R.id.wbvMain)

            wbvMain.reload()
            edtMainURL.setText(wbvMain.originalUrl)
            true
        }

        R.id.backMain -> {
            val wbvMain : WebView = findViewById(R.id.wbvMain)

            wbvMain.goBack()
            edtMainURL.setText(wbvMain.originalUrl)
            true
        }

        R.id.forwardMain -> {
            val wbvMain : WebView = findViewById(R.id.wbvMain)

            wbvMain.goForward()
            edtMainURL.setText(wbvMain.originalUrl)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}