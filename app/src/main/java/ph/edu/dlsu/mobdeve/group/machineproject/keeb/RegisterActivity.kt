package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityRegisterBinding


// Responsible for adding new users to the firebase authentication
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    var btn_reg: Button? = null
    var btn_login: Button? = null
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        btn_login = findViewById(R.id.btn_login)
        btn_reg = findViewById(R.id.btn_register2)

        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Registering account")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        btn_reg!!.setOnClickListener {
            validateData() // same behavior as login validator
        }

        btn_login!!.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java)) // redirects to login page
        }
    }

    private fun validateData() { // Checks the appropriate error handling within the register module
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding!!.etEmail.error = "Invalid Email"
        }
        else if (TextUtils.isEmpty(password)){
            binding!!.etPassword.error = "Please enter password"
        }
        else if (password.length <6){
            binding!!.etPassword.error = "Password must be atleast 6 characters long"
        }
        else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() { // Function responsible for adding the user to the authentication log of firebase
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account successfully created, please log in.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "sign up failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}