package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.CreatorActivity.Companion.paintBrush
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.CreatorActivity.Companion.path


// Responsible to be the container for all drawings for Canvas API
class PaintView : View{

    var params : ViewGroup.LayoutParams? = null

    companion object { // Initialize object to be used
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
    }

    constructor(context: Context) : this(context, null){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init(){ // Initialized values of the object
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 10f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean { // Function to listen for fingerstrokes within the application
        var x = event.x
        var y = event.y

        when(event.action){ // Moves to path within the finger
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE ->{ // Adds to the path
                path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) { // Adds the values or pixels to the path made by the user
        for(i in pathList.indices){
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }

}