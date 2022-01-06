package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


var etName: EditText? = null
var etEmailId: EditText? = null
var btnAdd: Button? = null
var rvItemsList: RecyclerView? = null
var tvNoRecordsAvailable: TextView? = null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)

        btnAdd?.setOnClickListener { view ->
            addRecord()

            Log.i(
                "MainApp",
                "name: ${etName?.text} pass:${etEmailId?.text}"
            )
        }

        setupListofDataIntoRecyclerView()

    }

    private fun setupListofDataIntoRecyclerView() {

        rvItemsList = findViewById(R.id.rvItemsList)

        if (getItemsList().size > 0) {

            rvItemsList!!.visibility = View.VISIBLE
            tvNoRecordsAvailable!!.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList!!.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList!!.adapter = itemAdapter
        } else {

            rvItemsList!!.visibility = View.GONE
            tvNoRecordsAvailable!!.visibility = View.VISIBLE
        }
    }


    //Method for saving the employee records in database
    private fun addRecord() {
        etName = findViewById(R.id.etName)
        etEmailId = findViewById(R.id.etEmailId)

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

    fun getItemsList(): ArrayList<EmpModelClass> {
        // create the instance of Database Handler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        // calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = databaseHandler.viewEmployee()

        return empList
    }
}