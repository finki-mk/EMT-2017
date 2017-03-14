package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Riste Stojanov
 */
@Controller
public class BasicController {


  QueryService queryService;

  @Autowired
  public BasicController(QueryService queryService) {
    this.queryService = queryService;
  }

  @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("products", queryService.getPromotedBooks(1, 20));

    return "index";
  }

  @RequestMapping(value = {"/category/{categoryId}"}, method = RequestMethod.GET)
  public String categoryProducts(
    @PathVariable Long categoryId,
    Model model
  ) {
    model.addAttribute("products", queryService.getBooksInCategory(
      categoryId, 1, 20
    ));

    return "index";
  }
}
