package ph.edu.dlsu.mobdeve.group.machineproject.keeb.model

// Responsible for storing the local data to be passed to firebase and vise versa

class Post {
    var title: String? = null
    var caption: String? = null
    var postUser: String? = null
    var uid: String? = null
    var imgString: String? = null

    constructor(){}

    constructor(title: String, caption: String, postUser: String, uid: String, imgString: String){
        this.title = title
        this.caption = caption
        this.postUser = postUser
        this.uid = uid
        this.imgString = imgString
    }
}