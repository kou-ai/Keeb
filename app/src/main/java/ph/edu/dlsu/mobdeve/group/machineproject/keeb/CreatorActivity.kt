package ph.edu.dlsu.mobdeve.group.machineproject.keeb


import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.colorList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.currentBrush
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.pathList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentCreatorBinding

// Activity responsible for creating figures using the CANVAS api

class CreatorActivity : Fragment(){

    companion object{ // Create init of paintbrush and path
        var path = Path()
        var paintBrush = Paint()
    }

        private var _binding: FragmentCreatorBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = FragmentCreatorBinding.inflate(inflater, container, false)

            val redBtn:  ImageButton = binding.redColor
            val blackBtn: ImageButton = binding.blackColor
            val yellowBtn: ImageButton = binding.yellowColor
            val eraser: ImageButton = binding.eraser


            // Set of onClickListeners to change the color of the brush
            redBtn.setOnClickListener {
                Toast.makeText(requireActivity(),"Clicked", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.RED
                currentColor(paintBrush.color)
            }

            blackBtn.setOnClickListener {
                Toast.makeText(requireActivity(),"Clicked", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.BLACK
                currentColor(paintBrush.color)
            }

            yellowBtn.setOnClickListener {
                Toast.makeText(requireActivity(),"Clicked", Toast.LENGTH_SHORT).show()
                paintBrush.color = Color.YELLOW
                currentColor(paintBrush.color)
            }

            // Eraser to remove everything within the view
            eraser.setOnClickListener {
                Toast.makeText(requireActivity(),"Clicked", Toast.LENGTH_SHORT).show()
                pathList.clear()
                colorList.clear()
                path.reset()
            }

            return binding.root
        }


    // Is called to generate and update the current color
    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}