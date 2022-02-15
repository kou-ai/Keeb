package ph.edu.dlsu.mobdeve.group.machineproject.keeb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.databinding.ItemRowBinding
import ph.edu.dlsu.mobdeve.group.machineproject.keeb.model.Post

var llMain: LinearLayout? = null
var tvName: TextView? = null
var tvEmail: TextView? = null
var ivEdit: ImageView? = null
var ivDelete: ImageView? = null


class ItemAdapter(private val context: Context, private var postList: ArrayList<Post>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /**
     * Inflates the item views which is designed in the XML layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPost(postList[position])

        /*holder.tvName.toString() = item.name
        holder.tvEmail.toString() = item.email */

        // Updating the background color according to the odd/even positions in list.
        /*
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple_200
                )
            )
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }

         */


    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return postList.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */

    class ViewHolder(private val itemBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        // Holds the TextView that will add each item to
        // need i-link to firebase users

        fun bindPost(post: Post) {
            itemBinding.tvEmail.text = post.postUser
            itemBinding.postCaption.text = post.caption
            itemBinding.postTitle.text = post.title
        }

        //val llMain = view // di ko gets para san 'to
        // var tvName = view
        // var tvEmail = view
    }
}
