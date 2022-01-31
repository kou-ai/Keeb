package ph.edu.dlsu.mobdeve.group.machineproject.keeb



import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.colorList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.currentBrush
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.PaintView.Companion.pathList
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.FragmentCreatorBinding


class CreatorActivity : Fragment(){

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

        private var _binding: FragmentCreatorBinding? = null
        // This property is only valid between onCreateView and
        // onDestroyView.
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
            /*activity.getSupportActionBar().hide()*/

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

            eraser.setOnClickListener {
                Toast.makeText(requireActivity(),"Clicked", Toast.LENGTH_SHORT).show()
                pathList.clear()
                colorList.clear()
                path.reset()
            }

            return binding.root
        }



    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}