package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

// Responsible for generating all post data from the DB

class ForumActivity : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var sRef: StorageReference
    private var _binding: FragmentForumBinding? = null
    var itemAdapter: ItemAdapter? = null
    var postList = ArrayList<Post>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, true)

        val btnCreatePost: Button = binding.btnCreatePost
        firebaseAuth = FirebaseAuth.getInstance()

        readPosts() // function to read data from Firebase back to local application

        // Series of code to call the Adapter file for manipulating the recycler view
        // Create own post to be submitted to the database
        btnCreatePost.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, CreatePostActivity::class.java))
        }

        return binding.root
    }

    private fun readPosts() { // Reads the data of a specific post
        dbRef = FirebaseDatabase.getInstance("https://fir-login-signup-862d3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("post")

        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (postSnapshot in snapshot.children){
                        val post = postSnapshot.getValue(Post::class.java)
                        postList.add(post!!)
                    }
                    itemAdapter = ItemAdapter(postList)
                    binding.forumPosts.layoutManager = LinearLayoutManager(
                        requireActivity().applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    binding.forumPosts.adapter = itemAdapter
                    }
                }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}


