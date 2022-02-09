package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentProfileBinding

class   ProfileActivity : Fragment() {

        private var _binding: FragmentProfileBinding? = null
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var actionBar: ActionBar
        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = FragmentProfileBinding.inflate(inflater, container, false)
            val btnEnd: Button = binding.buttonLogout

            actionBar = requireActivity().actionBar!!
            actionBar.title = "Profile"

            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()

            btnEnd.setOnClickListener {
                firebaseAuth.signOut()
                checkUser()
            }

            return binding.root
        }

    private fun checkUser() {
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



