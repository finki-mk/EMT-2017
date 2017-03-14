package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Riste Stojanov
 */
@Controller
public class AdminController {

  StoreManagementService storeManagementService;

  @Autowired
  public AdminController(StoreManagementService storeManagementService) {
    this.storeManagementService = storeManagementService;
  }

  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.GET)
  public String addCategory(Model model) {
    model.addAttribute("pageFragment", "addCategory");
    return "index";
  }

  @RequestMapping(value = {"/admin/product"}, method = RequestMethod.GET)
  public String addProduct(Model model) {
    model.addAttribute("pageFragment", "addProduct");
    return "index";
  }

  @RequestMapping(value = {"/admin/product"}, method = RequestMethod.POST)
  public String createProduct(HttpServletRequest request,
                              HttpServletResponse resp,
                              Model model,
                              @RequestParam String name,
                              @RequestParam Long categoryId,
                              @RequestParam String[] authors,
                              @RequestParam String isbn,
                              @RequestParam Double price,
                              @RequestParam String description,
                              MultipartFile picture) throws IOException, SQLException {

    Book product = storeManagementService.createBook(
      name,
      categoryId,
      authors,
      isbn,
      price
    );
    storeManagementService.addBookPicture(product.id, picture.getBytes(), picture.getContentType());
    model.addAttribute("product", product);
    return "index";
  }


  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.POST)
  public String createCategory(HttpServletRequest request,
                               HttpServletResponse resp,
                               Model model,
                               @RequestParam String categoryName) {
    Category category = storeManagementService.createTopLevelCategory(categoryName);



//    List<Category> categories = service.findAllCategories();
//    model.addAttribute("category", category);
//    model.addAttribute("products", products);
//    // because there is a new category, so we have to override the category
//    // request param from the filter
//    model.addAttribute("categories", categories);
    return "redirect:/admin/category";
  }

}
