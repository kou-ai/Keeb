package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {

    var binding: ActivityCreatePostBinding? = null
    var picture: ImageView? = null
    var uploadbtn: Button? = null
    var title: EditText? = null
    var caption: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        uploadbtn = findViewById(R.id.btn_uploadPhoto)
        title = findViewById(R.id.et_title)
        caption = findViewById(R.id.et_caption)
        picture = findViewById(R.id.img_uploadedPic)
    }
}