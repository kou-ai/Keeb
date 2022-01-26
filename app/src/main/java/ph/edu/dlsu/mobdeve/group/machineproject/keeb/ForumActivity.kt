package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.net.Uri;
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window;
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentHomeBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.ui.home.HomeViewModel


class ForumActivity : Fragment() {

    private var _binding: FragmentForumBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForumBinding.inflate(inflater, container, false)

        return binding.root
    }
}