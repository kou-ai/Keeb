package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityLoginBinding


var et_username: EditText? = null
var et_password: EditText? = null
var btn_submit: Button? = null

class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null
    var btn_submit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
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

            addRecord()

            var gotoMainActivity = Intent(applicationContext,MainActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", et_username!!.text.toString())

            gotoMainActivity.putExtras(bundle)
            gotoMainActivity.putExtra("username", et_username!!.text.toString())

            startActivity(gotoMainActivity)
            finish()
        }
    }


    private fun addRecord() {
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)


        val name = et_username!!.text.toString()
        val password = et_password!!.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !password.isEmpty()) {
            val status =
                databaseHandler.addEmployee(EmpModelClass(0, name, password))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                et_username!!.text.clear()
                et_password!!.text.clear()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Name or Email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

