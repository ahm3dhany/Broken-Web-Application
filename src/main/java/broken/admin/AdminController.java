package broken.admin;

import broken.db.QuoteService;
import broken.wall.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ahm3dhany
 */
@Controller
public class AdminController {
    
    @Autowired
    QuoteService quoteService;
    
    @Autowired
    PostService postService;
    
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String getAllQuotes (Model model) throws Exception {
        model.addAttribute("quotes", quoteService.getAllQuotes());
        model.addAttribute("posts", postService.getAllPosts());
        return "/admin/admin";
    }
    
    @RequestMapping(value="/admin/delete/quote/{id}", method = RequestMethod.POST)
    public String deleteQuote(@PathVariable String id) throws Exception {        
        quoteService.deleteQuote(Integer.parseInt(id));
        return "redirect:/admin";
    }
    
    @RequestMapping(value="/admin/delete/post/{id}", method = RequestMethod.POST)
    public String deletePost(@PathVariable String id) throws Exception {        
        postService.deletePost(Integer.parseInt(id));
        return "redirect:/admin";
    }
    
}
