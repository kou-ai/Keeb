package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Random


var btn_generate: Button? = null
lateinit var tv_switches: TextView
var generated_image: ImageView? = null

class Generator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)


    btn_generate = findViewById(R.id.btn_generate)
    tv_switches = findViewById(R.id.tv_switches)
    generated_image = findViewById(R.id.generated_image)

    btn_submit!!.setOnClickListener {
        val rnds = (0..10).random()

        tv_switches.setText(rnds.toString())

        Log.i(
            "Generator",
            "Keyboard: $rnds"
        )

        var gotoGenerator = Intent(applicationContext, Generator::class.java)

        var bundle = Bundle()
        bundle.putString("keyboard", tv_switches.toString())

        gotoGenerator.putExtras(bundle)
        gotoGenerator.putExtra("keyboard", tv_switches.toString())




        startActivity(gotoGenerator)

        finish()
        }
    }
}