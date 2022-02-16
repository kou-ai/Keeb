package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ItemRowBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

class ItemAdapter(private var context: Context, private var postList: ArrayList<Post>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Calls the context from the parent view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    // Gets the number of items in the list
    override fun getItemCount() = postList.size

    // Passes the acquired value to be displayed to the corresponding elements
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = postList[position]

        holder.email.text = context.postUser
        holder.caption.text = context.caption
        holder.title.text = context.title

    }



    // Initializes the elements from corresponding recycler view to be given data
    class ViewHolder(private val itemBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var email = itemBinding.tvEmail
        var caption = itemBinding.postCaption
        var title = itemBinding.postTitle

    }
}
