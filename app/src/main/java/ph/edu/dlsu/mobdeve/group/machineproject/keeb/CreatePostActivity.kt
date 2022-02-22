package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityCreatePostBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

// Activity responsible for creating post to be passed and rendered by the Forum Fragment

class CreatePostActivity : AppCompatActivity() {


    // Initialize variables needed to be used by Firebase, Kotlin, and XMLs
    var binding: ActivityCreatePostBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var srRef: StorageReference
    var picture: ImageView? = null
    var passedImage: Uri? = null
    var uploadbtn: Button? = null
    var publishbtn: Button? = null
    var title: EditText? = null
    var caption: EditText? = null
    private val REQUEST_CODE = 13

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        uploadbtn = findViewById(R.id.btn_uploadPhoto)
        publishbtn = findViewById(R.id.btn_uploadPost)
        title = findViewById(R.id.et_title)
        caption = findViewById(R.id.et_caption)
        picture = findViewById(R.id.img_uploadedPic)
        firebaseAuth = FirebaseAuth.getInstance() // Instance for firebase authentication
        val uid = firebaseAuth.currentUser?.uid // Catches the uid of current logged in user
        val emailUser = firebaseAuth.currentUser?.email

        uploadbtn!!.setOnClickListener { // Asks user for image within the gallery to be uploaded by URI
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE) // Checks if emulator or phone has permissions
            } else {
                chooseImageGallery() // runs the function
            }
        }

        publishbtn!!.setOnClickListener {
            val imageString = passedImage.toString()// Passes the inserted values to the local model to be added to the DB
            val postCap = caption?.text.toString()
            val postTitle = title?.text.toString()
            val Post = Post(postTitle, postCap, emailUser!!, uid!!, imageString)
            addPost(Post)
        }

    }

    companion object { // Constants for CODE validation
        private val PERMISSION_CODE = 1001;
        private val IMAGE_CODE = 1001;
    }

    private fun chooseImageGallery() { // Converts ACTION of choosing image to URI to be used
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_CODE) // Start activity with acquired result
    }

    private fun addPost(post: Post){ // Adds the inserted data to the FIREBASE realtime DB
        dbRef = FirebaseDatabase.getInstance("https://fir-login-signup-862d3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("post") // Link of project DB
        val key = dbRef.push().key
        dbRef.child(key!!).setValue(post).addOnSuccessListener {
            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
        }

        passedImage // URI from previous function
        srRef = FirebaseStorage.getInstance().getReference("Posts/"+key) // Link of project storage
        srRef.putFile(passedImage!!).addOnSuccessListener { // Places file into the storage
            Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e->
            Toast.makeText(this, "Update failed due to ${e.message}", Toast.LENGTH_SHORT).show()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult( // Checker for phone or emulator permissions
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseImageGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){ // Officially converts the image URI and sets it to the designated imageview
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK){
            picture!!.setImageURI(data?.data)
            passedImage = data?.data // Global variable for functions
        }
    }
}


