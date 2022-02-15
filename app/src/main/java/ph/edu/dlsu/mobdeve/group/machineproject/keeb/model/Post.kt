package ph.edu.dlsu.mobdeve.group.machineproject.keeb.model

import android.media.Image
import android.net.Uri

class Post {
    var title: String? = null
    var caption: String? = null
    var postUser: String? = null
    var uid: String? = null

    constructor(){}

    constructor(title: String, caption: String, postUser: String, uid: String){
        this.title = title
        this.caption = caption
        this.postUser = postUser
        this.uid = uid
    }
}