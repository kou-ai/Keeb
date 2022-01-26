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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding


class ForumActivity : AppCompatActivity() {

    var binding: FragmentForumBinding? = null


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentForumBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }
}