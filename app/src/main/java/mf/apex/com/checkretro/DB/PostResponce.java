package mf.apex.com.checkretro.DB;

import java.util.List;

/**
 * Created by user on 05/07/2016.
 */
public class PostResponce {

    List<Users> posts;  //Json Object

    public List<Users> getPosts() {
        return posts;
    }

    public void setPosts(List<Users> posts) {
        this.posts = posts;
    }
}
