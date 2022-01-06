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
    var tv_switches: TextView? = null
    var tv_layout: TextView? = null
    var tv_kprofile: TextView? = null
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
        btn_generate = findViewById(R.id.btn_generate)
        tv_switches = findViewById(R.id.tv_switches)
        tv_layout = findViewById(R.id.tv_layout)
        tv_kprofile = findViewById(R.id.tv_kprofile)

        btn_generate!!.setOnClickListener {
            val rnds = (0..2).random()
            val final = switchList[rnds]!!.switches
            val final2 = layoutList[rnds]!!.layout
            val final3 = keyList[rnds]!!.keycap

            Log.i("random", "$rnds")
            Log.i("final random", "$final")

            tv_switches!!.text = final
            tv_layout!!.text = final2
            tv_kprofile!!.text= final3

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




       