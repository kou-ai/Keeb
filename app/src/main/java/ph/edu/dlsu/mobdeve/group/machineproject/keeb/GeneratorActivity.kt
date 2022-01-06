package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ActivityGeneratorBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAO
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.keebDAO.KeebDAOArrayList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Keycaps
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Layout
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Switches


class GeneratorActivity : AppCompatActivity() {

    var btn_generate: Button? = null
    var binding: ActivityGeneratorBinding? = null
    lateinit var tv_switches: TextView
    lateinit var tv_layout: TextView
    lateinit var tv_kprofile: TextView
    var generated_image: ImageView? = null
    var switchList = ArrayList<Switches?>()
    var layoutList = ArrayList<Layout?>()
    var keyList = ArrayList<Keycaps?>()
    var KeebDAO: KeebDAO = KeebDAOArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratorBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras

        populateList()


        btn_generate!!.setOnClickListener {
            val rnds = (0..2).random()
            val final = switchList[rnds]
            val final2 = layoutList[rnds]
            val final3 = keyList[rnds]

            tv_switches.setText(final.toString())
            tv_layout.setText(final2.toString())
            tv_kprofile.setText(final3.toString())

            Log.i(
                "Generator",
                "Keyboard: $rnds"
            )
        }
    }


    private fun populateList(){
        switchList = KeebDAO.getSwitches()
        layoutList = KeebDAO.getLayout()
        keyList = KeebDAO.getKeycaps()
    }
}




       