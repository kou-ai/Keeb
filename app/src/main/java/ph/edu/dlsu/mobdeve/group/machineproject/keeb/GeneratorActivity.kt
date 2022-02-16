package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentGeneratorBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAO
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAOArrayList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches

// Responsible for the randomizer of the keyboards with corresponding data
class GeneratorActivity : Fragment() {

    // Initialize the necessary variables
    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()
    var KeebDAO: KeebDAO = KeebDAOArrayList()
    private var _binding: FragmentGeneratorBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storageRef: StorageReference
    // Create imagelist for calling the values
    private val imageList = intArrayOf(R.drawable.keeb0, R.drawable.keeb1, R.drawable.keeb2, R.drawable.keeb3, R.drawable.keeb4, R.drawable.keeb5, R.drawable.keeb6, R.drawable.keeb7)
    private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            firebaseAuth = FirebaseAuth.getInstance() // First instance of authentication
            storageRef = Firebase.storage.reference // References the storage to be used

            _binding = FragmentGeneratorBinding.inflate(inflater, container, false)

            populateList() // Adds the values to arraylist
            val btn_generate: Button = binding.btnGenerate // initializes the elements to be used
            val tv_switches: TextView = binding.tvSwitches
            val tv_layout: TextView = binding.tvLayout
            val tv_kprofile: TextView = binding.tvKprofile
            val img_gen: ImageView = binding.generatedImage

            btn_generate.setOnClickListener { // OnClick to randomize the lists within the database
                val rnds = (0..1).random() // randomizes the index
                val rnds2 = (0..1).random()
                val rnds3 =  (0..1).random()
                val final = switchList[rnds]!!.switches
                val final2 = layoutList[rnds2]!!.layout
                val final3 = keyList[rnds3]!!.keycap

                // String literal to be used as condition for permutations
                val combinations: String = rnds.toString().plus(rnds2.toString()).plus(rnds3.toString())


                tv_switches.text = final
                tv_layout.text = final2
                tv_kprofile.text= final3

                // Condition to use specific image with the randomized items
                when (combinations){
                    "000" -> img_gen.setImageResource(imageList[0])
                    "111" -> img_gen.setImageResource(imageList[1])
                    "001" -> img_gen.setImageResource(imageList[2])
                    "010" -> img_gen.setImageResource(imageList[3])
                    "100" -> img_gen.setImageResource(imageList[4])
                    "110" -> img_gen.setImageResource(imageList[5])
                    "011" -> img_gen.setImageResource(imageList[6])
                    "101" -> img_gen.setImageResource(imageList[7])
                }
            }
            return binding.root
        }


    // Passes called data to local model 
    private fun populateList(){
        switchList = KeebDAO.getSwitches()
        layoutList = KeebDAO.getLayout()
        keyList = KeebDAO.getKeycaps()
    }
}




       