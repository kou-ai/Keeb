package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


var btn_generate: Button? = null
lateinit var tv_switches: TextView
var generated_image: ImageView? = null


class GeneratorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)


        btn_generate = findViewById(R.id.btn_generate)
        tv_switches = findViewById(R.id.tv_switches)
        generated_image = findViewById(R.id.generated_image)

        btn_generate!!.setOnClickListener {
            val rnds = (0..10).random()

            tv_switches.setText(rnds.toString())

            Log.i(
                "Generator",
                "Keyboard: $rnds"
            )
        }
    }
}



       