package mk.ukim.finki.emt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Riste Stojanov
 */
@Controller
@RequestMapping("/manage")
public class StoreManagementController {


  @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
  public String index(Model model) {


    return "index";
  }
}
