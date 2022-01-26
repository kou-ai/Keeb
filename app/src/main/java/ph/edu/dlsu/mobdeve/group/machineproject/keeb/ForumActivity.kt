package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding


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