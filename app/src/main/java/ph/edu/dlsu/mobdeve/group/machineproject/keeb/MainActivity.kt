package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


var etName: EditText? = null
var etEmailId: EditText? = null
var btnAdd: Button? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd?.setOnClickListener { view ->
            addRecord()
        }

        etName = findViewById(R.id.etName)
        etEmailId = findViewById(R.id.etEmailId)
        btnAdd = findViewById(R.id.btnAdd)
    }

    //Method for saving the employee records in database
    private fun addRecord() {
        val name = etName!!.text.toString()
        val email = etEmailId!!.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty()) {
            val status =
                databaseHandler.addEmployee(EmpModelClass(0, name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                etName!!.text.clear()
                etEmailId!!.text.clear()
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