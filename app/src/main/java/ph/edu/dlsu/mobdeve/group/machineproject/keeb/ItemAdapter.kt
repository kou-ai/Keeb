package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

class ItemAdapter(private var postList: ArrayList<Post>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Calls the context from the parent view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(itemView)
    }

    // Gets the number of items in the list
    override fun getItemCount() = postList.size

    // Passes the acquired value to be displayed to the corresponding elements
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = postList[position]

        holder.email.text = context.postUser
        holder.caption.text = context.caption
        holder.title.text = context.title

        if (context.imgString == null){
            holder.img.setImageResource(R.mipmap.ic_launcher)
        } else holder.img.setImageURI(context.imgString!!.toUri())

    }


    // Initializes the elements from corresponding recycler view to be given data
    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.tv_email)
        val caption: TextView = itemView.findViewById(R.id.postCaption)
        val title: TextView = itemView.findViewById(R.id.postTitle)
        val img: ImageView = itemView.findViewById(R.id.postImg)
    }
}
