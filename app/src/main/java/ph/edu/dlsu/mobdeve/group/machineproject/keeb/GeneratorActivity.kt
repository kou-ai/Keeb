package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityGeneratorBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentForumBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentGeneratorBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAO
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAOArrayList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches


class GeneratorActivity : Fragment() {


    var generated_image: ImageView? = null
    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()
    var KeebDAO: KeebDAO = KeebDAOArrayList()
    private var _binding: FragmentGeneratorBinding? = null


        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            _binding = FragmentGeneratorBinding.inflate(inflater, container, false)


            populateList()
            val btn_generate: Button = binding.btnGenerate
            val tv_switches: TextView = binding.tvSwitches
            val tv_layout: TextView = binding.tvLayout
            val tv_kprofile: TextView = binding.tvKprofile

            btn_generate!!.setOnClickListener {
                val rnds = (0..2).random()
                val final = switchList[rnds]!!.switches
                val final2 = layoutList[rnds]!!.layout
                val final3 = keyList[rnds]!!.keycap

                Log.i("random", "$rnds")
                Log.i("final random", final)

                tv_switches.text = final
                tv_layout.text = final2
                tv_kprofile.text= final3

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




       