package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentProfileBinding

// Responsible for storing the user data
class   ProfileActivity : Fragment() {

        private var _binding: FragmentProfileBinding? = null
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var actionBar: ActionBar
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = FragmentProfileBinding.inflate(inflater, container, false)
            firebaseAuth = FirebaseAuth.getInstance()
            val btnEnd: Button = binding.buttonLogout

            firebaseAuth = FirebaseAuth.getInstance() // first instance of authentication
            checkUser() // checks if user is signed in and which user is signed in

            btnEnd.setOnClickListener { // Logout button to end session
                firebaseAuth.signOut()
                checkUser()
            }

            return binding.root
        }

    private fun checkUser() { // Checks for the state of the app, if logged in, use the details, if not, redirect
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            binding.profileEmail.text = email
        }
        else {
            startActivity(Intent(activity?.applicationContext, LoginActivity::class.java))
            activity?.finish()
        }
    }
}



