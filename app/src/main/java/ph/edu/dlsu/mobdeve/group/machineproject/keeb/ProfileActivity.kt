package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentProfileBinding

class   ProfileActivity : Fragment() {

        private var _binding: FragmentProfileBinding? = null
        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            _binding = FragmentProfileBinding.inflate(inflater, container, false)
            val btnEnd: Button = binding.buttonLogout

            btnEnd.setOnClickListener {
                val intent = Intent(activity?.applicationContext, LoginActivity::class.java)
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                activity?.finish()
            }

            return binding.root
        }
    }



