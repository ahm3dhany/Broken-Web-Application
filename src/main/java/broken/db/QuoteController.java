package broken.db;

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
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;
    
    @RequestMapping(value="/quotes", method = RequestMethod.GET)
    public String getAllQuotes (Model model) throws Exception {
        
        model.addAttribute("quotes", quoteService.getAllQuotes());
        
        return "quotes";
    }
    
    @RequestMapping(value="/quotes", method = RequestMethod.POST)
    public String addQuote(Model model, @RequestParam String id, @RequestParam String content) throws Exception {
        
        quoteService.addQuote(new Quote(Integer.parseInt(id), content));
        
        return "redirect:/quotes";
    }
    
}
