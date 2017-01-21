package broken.external;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ahm3dhany
 */
@Controller
public class ExternalController {
    
    @RequestMapping("/ourFriend")
    public String evil() {
        return "/external/malicious";
    }
    
}
