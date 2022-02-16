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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

// Responsible for generating all post data from the DB

class ForumActivity : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private var _binding: FragmentForumBinding? = null
    var itemAdapter: ItemAdapter? = null
    var postList = ArrayList<Post>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForumBinding.inflate(inflater, container, true)

        val btn_createPost: Button = binding.btnCreatePost
        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance("https://fir-login-signup-862d3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("post")

        if (dbRef != null) { // Checks whether or not the current DB is existing or not
            readPosts(dbRef) // function to read data from Firebase back to local application
        } else {
            Toast.makeText(context, "Account successfully created, please log in.", Toast.LENGTH_SHORT).show()
        }

        // Series of code to call the Adapter file for manipulating the recycler view
        itemAdapter = ItemAdapter(requireActivity().applicationContext, postList)
        binding.forumPosts.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.forumPosts.adapter = itemAdapter

        // Create own post to be submitted to the database
        btn_createPost.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, CreatePostActivity::class.java))
        }

        return binding.root
    }

    private fun readPosts (dbRef: DatabaseReference){ // Reads the data of a specific post
        dbRef.child("1").get().addOnSuccessListener {
            if (it.exists()){ // If snapshot has data, get the values
                val email = it.child("postUser").value.toString()
                val title = it.child("title").value.toString()
                val uid = it.child("uid").value.toString()
                val caption = it.child("caption").value.toString()

                Toast.makeText(context, "Data read", Toast.LENGTH_SHORT).show()
                postList.add(Post(title, caption, email, uid)) // add data from Firebase to local model
            } else {
                Toast.makeText(context, "Data not read.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


