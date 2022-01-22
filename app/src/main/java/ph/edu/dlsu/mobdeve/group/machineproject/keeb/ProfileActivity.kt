package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class   ProfileActivity : AppCompatActivity() {

    var btnEnd: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnEnd = findViewById(R.id.buttonLogout)
        btnEnd!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish()
        }
    }
}