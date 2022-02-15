package ph.edu.dlsu.mobdeve.group.machineproject.keeb.model

import android.media.Image
import android.net.Uri

class Post {
    var title: String? = null
    var caption: String? = null
    var postUser: String? = null

    constructor(){}

    constructor(title: String, caption: String, postUser: String){
        this.title = title
        this.caption = caption
        this.postUser = postUser
    }
}