package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityCreatePostBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

class CreatePostActivity : AppCompatActivity() {

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
    var postNum: Int = 0
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
        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        val emailUser = firebaseAuth.currentUser?.email
        Log.i("uidofuser", uid.toString())

        // Log.i("dbRef", dbRef.toString())


        uploadbtn!!.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                chooseImageGallery()
            }
        }

        publishbtn!!.setOnClickListener {
            val postCap = caption?.text.toString()
            val postTitle = title?.text.toString()
            postNum += 1
            addPost( postCap, postTitle, uid!!, emailUser.toString(), postNum)
        }

    }

    companion object {
        private val PERMISSION_CODE = 1001;
        private val IMAGE_CODE = 1001;
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        Log.i("intent value", intent.toString())
        startActivityForResult(intent, IMAGE_CODE)
    }

    private fun addPost(title: String, caption: String, uid: String, users: String, postNum: Int){
        dbRef = FirebaseDatabase.getInstance("https://fir-login-signup-862d3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference()
        dbRef.child("post").child(postNum.toString()).setValue(Post(title, caption, users, uid))
        uploadImage()
    }

    private fun uploadImage() {
        passedImage
        srRef = FirebaseStorage.getInstance().getReference("Posts/"+firebaseAuth.currentUser?.uid)
        Log.i("srRef", srRef.toString())
        srRef.putFile(passedImage!!).addOnSuccessListener {
            Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e->
            Toast.makeText(this, "Update failed due to ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK){
            picture!!.setImageURI(data?.data)
            passedImage = data?.data
        }
    }
}


