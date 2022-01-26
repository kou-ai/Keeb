package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.colorList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.currentBrush
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.pathList

class CreatorActivity : AppCompatActivity(){

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    var path = Path()
    var paintBrush = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_creator)
        setContentView(R.layout.fragment_creator)

        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val blackBtn = findViewById<ImageButton>(R.id.blackColor)
        val yellowBtn = findViewById<ImageButton>(R.id.yellowColor)
        val eraser = findViewById<ImageButton>(R.id.blueColor)
        supportActionBar?.hide()

        redBtn.setOnClickListener {
            Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        blackBtn.setOnClickListener {
            Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLACK
        }

        yellowBtn.setOnClickListener {
            Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.YELLOW
        }

        eraser.setOnClickListener {
            Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()

        }
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }

}