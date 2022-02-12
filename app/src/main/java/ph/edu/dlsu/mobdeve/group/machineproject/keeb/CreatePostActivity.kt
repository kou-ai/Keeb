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
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    var binding: ActivityCreatePostBinding? = null
    var picture: ImageView? = null
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


        uploadbtn!!.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                chooseImageGallery()
            }
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
        }
    }
}


