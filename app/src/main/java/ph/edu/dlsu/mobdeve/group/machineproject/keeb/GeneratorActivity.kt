package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentGeneratorBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAO
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAOArrayList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches


class GeneratorActivity : Fragment() {


    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()
    var KeebDAO: KeebDAO = KeebDAOArrayList()
    private var _binding: FragmentGeneratorBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storageRef: StorageReference
    private val imageList = intArrayOf()




        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            firebaseAuth = FirebaseAuth.getInstance()
            storageRef = Firebase.storage.reference

            _binding = FragmentGeneratorBinding.inflate(inflater, container, false)

            populateList()
            val btn_generate: Button = binding.btnGenerate
            val tv_switches: TextView = binding.tvSwitches
            val tv_layout: TextView = binding.tvLayout
            val tv_kprofile: TextView = binding.tvKprofile
            val img_gen: ImageView = binding.generatedImage

            btn_generate.setOnClickListener {
                val rnds = (0..1).random()
                val rnds2 = (0..1).random()
                val rnds3 =  (0..1).random()
                val final = switchList[rnds]!!.switches
                val final2 = layoutList[rnds2]!!.layout
                val final3 = keyList[rnds3]!!.keycap
                val combinations: String = rnds.toString().plus(rnds2.toString()).plus(rnds3.toString())


                Log.i("random", "$rnds")
                Log.i("final random", final)

                tv_switches.text = final
                tv_layout.text = final2
                tv_kprofile.text= final3

                when (combinations){
                    "000" -> tv_switches.text = final
                    "000" -> tv_switches.text = final
                    "000" -> tv_switches.text = final

                    "111" -> tv_switches.text = final

                    "001" -> tv_switches.text = final

                    "010" -> tv_switches.text = final

                    "100" -> tv_switches.text = final

                    "110" -> tv_switches.text = final

                    "011" -> tv_switches.text = final

                    "101" -> tv_switches.text = final

                }

                Log.i(
                    "Generator",
                    "Keyboard: $rnds"
                )
            }
            return binding.root
        }



    private fun populateList(){
        switchList = KeebDAO.getSwitches()
        layoutList = KeebDAO.getLayout()
        keyList = KeebDAO.getKeycaps()
    }
}




       