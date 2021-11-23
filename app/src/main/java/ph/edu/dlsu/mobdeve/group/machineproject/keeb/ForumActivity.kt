package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.CookieSyncManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.Uri;
import android.view.Window;


class ForumActivity : Activity() {
    private var webView: WebView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Adds Progress Bar Support
        this.window.requestFeature(Window.FEATURE_PROGRESS)
        // Makes Progress Bar Visible
        window.setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON)

        // Use forum.xml as webview layout
        setContentView(R.layout.forum)
        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true

        // Adds Zoom Control (You may not need this)
        webView!!.settings.setSupportZoom(true)

        // Enables Multi-Touch. if supported by ROM
        webView!!.settings.builtInZoomControls = true

        // Change to your own forum url
        webView!!.loadUrl("http://smf-media.com/community/")
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // Loads only your frum domain and no others!
                if (url.contains("smf-media.com") == true) {
                    view.loadUrl(url)
                    // Adds Progress Bar Support
                    super.onPageStarted(view, url, null)
                    findViewById<View>(R.id.progressbar).visibility = View.VISIBLE
                    // If they are not your domain, use browser instead
                } else {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)
                }
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Removes Progress Bar
                findViewById<View>(R.id.progressbar).visibility = View.GONE
                // Adds Cookies. Yummy!
                CookieSyncManager.getInstance().sync()
            }
        }
    }

    override fun onBackPressed() {
        // Enables going back history
        if (webView!!.copyBackForwardList().currentIndex > 0) {
            webView!!.goBack()
        } else {
            // Your exit alert code, or alternatively line below to finish
            // Finishes forum activity
            super.onBackPressed()
        }
    }
}