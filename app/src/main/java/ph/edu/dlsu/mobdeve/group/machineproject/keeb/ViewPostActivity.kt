package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityViewPostBinding

class ViewPostActivity : AppCompatActivity() {

    var binding: ActivityViewPostBinding? = null
    var title: EditText? = null
    var caption: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}