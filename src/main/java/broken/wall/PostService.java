package broken.wall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author ahm3dhany
 */
@Service
public class PostService {
    
    private List<Post> posts = new ArrayList<>(Arrays.asList(
            new Post("Regret", "Changed his mind after he jumped."),
            new Post("Grief", "\"Wrong number,\" says a familiar voice."),
            new Post("Dark Humor", "Failed class. Attempted suicide. Failed again."),
            new Post("Tragedy", "Failed suicide attempt. Paralyzed. Suffer life.")           
            ));
        
    public List<Post> getAllPosts() {
        return this.posts;
    }
    
    public void addPost(Post post) {
        this.posts.add(post);
    }
        
    public void deletePost(int id) {
        this.posts.remove(id - 1);
    }    
    
}
