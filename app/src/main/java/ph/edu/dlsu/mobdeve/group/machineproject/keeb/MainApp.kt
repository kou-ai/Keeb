package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

var et_username: EditText? = null
var et_password: EditText? = null
var btn_submit: Button? = null

class MainApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        btn_submit = findViewById(R.id.btn_submit)

        btn_submit!!.setOnClickListener {
            Log.i(
                "MainApp",
                "name: ${et_username?.text} pass:${et_password?.text}"
            )

            Toast.makeText(
                applicationContext,
                "name: ${et_username?.text} pass:${et_password?.text}",
                Toast.LENGTH_SHORT
            ).show()

            var gotoForumActivity = Intent(applicationContext, LandingActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", et_username!!.text.toString())

            gotoForumActivity.putExtras(bundle)
            gotoForumActivity.putExtra("username", et_username!!.text.toString())

            startActivity(gotoForumActivity)
            finish()
        }
    }
}