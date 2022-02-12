package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post


class ForumActivity : Fragment() {

    private var _binding: FragmentForumBinding? = null
    var itemAdapter: ItemAdapter? = null
    var postList = ArrayList<Post>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForumBinding.inflate(inflater, container, false)

        val btn_createPost: Button = binding.btnCreatePost

        itemAdapter = ItemAdapter(requireActivity().applicationContext, postList)
        _binding!!.forumPosts.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
        _binding!!.forumPosts.adapter = itemAdapter

        btn_createPost.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, CreatePostActivity::class.java))
        }

        return binding.root
    }
}