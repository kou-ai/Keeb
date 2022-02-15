package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post


class ForumActivity : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var dataSnapshot: DataSnapshot
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
        dbRef = FirebaseDatabase.getInstance("https://fir-login-signup-862d3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("post")
        val size = dataSnapshot.getChildrenCount()
        Log.i("children count", size.toString())


        if (dbRef != null) {
            readPosts(dbRef)
        } else {
            Toast.makeText(context, "Account successfully created, please log in.", Toast.LENGTH_SHORT).show()
        }

        itemAdapter = ItemAdapter(requireActivity().applicationContext, postList)
        _binding!!.forumPosts.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
        _binding!!.forumPosts.adapter = itemAdapter

        btn_createPost.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, CreatePostActivity::class.java))
        }

        return binding.root
    }

    private fun readPosts (dbRef: DatabaseReference){
        val postId = dbRef.database.getReference("postId")
        dbRef.child(postId.toString()).get().addOnSuccessListener {
            if (it.exists()){
                val email = it.child("postUser")
                val title = it.child("title")
                val uid = it.child("uid")
                val caption = it.child("caption")
                Toast.makeText(context, "Data read", Toast.LENGTH_SHORT).show()

                Post(email.toString(), title.toString(), uid.toString(), caption.toString())




            } else {
                Toast.makeText(context, "Data not read.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}