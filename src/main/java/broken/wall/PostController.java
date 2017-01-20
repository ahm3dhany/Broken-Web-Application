package broken.wall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ahm3dhany
 */
@Controller
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value="/sixWordStories", method = RequestMethod.GET)
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "sixWordStories";
    }
    
    @RequestMapping(value="/sixWordStories", method = RequestMethod.POST)
    public String addPost(@RequestParam String title, @RequestParam String content) {
        postService.addPost(new Post(title, content));
        return "redirect:/sixWordStories";
    }
    
}
