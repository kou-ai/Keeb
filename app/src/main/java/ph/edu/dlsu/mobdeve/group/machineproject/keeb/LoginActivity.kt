package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityLoginBinding

// Responsible for verifying user state and login
class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null
    var btn_submit: Button? = null
    var btn_reg: Button? = null
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        actionBar = supportActionBar!!
        actionBar.title = "Login"
        btn_submit = findViewById(R.id.btn_submit)
        btn_reg = findViewById(R.id.btn_register)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance() // First instance of authentication
        checkUser()

        btn_reg!!.setOnClickListener{ // Switches to register file
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_submit!!.setOnClickListener{
            validateData() // Checks if the data submitted is correct
        }
    }

    private fun validateData() { // Checks for wrong formatting, validation
        email = binding!!.etEmail.text.toString().trim()
        password = binding!!.etPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding!!.etEmail.error = "Invalid Email"
        }
        else if (TextUtils.isEmpty(password)){
            binding!!.etPassword.error = "Please enter password"
        }
        else
            firebaseLogin() // Officially logs in the credentials
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password) // Predefined function to add to the authentication of Firebase
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser // Passes constant values
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){ // Checks state of the application if one is logged to redirect to page
        val firebaseuser = firebaseAuth.currentUser
        if (firebaseuser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

