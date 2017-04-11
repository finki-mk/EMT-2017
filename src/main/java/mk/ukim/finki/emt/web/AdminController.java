package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.AuthorsRepository;
import mk.ukim.finki.emt.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Riste Stojanov
 */
@Controller
public class AdminController {

  StoreManagementService storeManagementService;

  @Autowired
  AuthorsRepository authorsRepository;

  @Autowired
  public AdminController(StoreManagementService storeManagementService) {
    this.storeManagementService = storeManagementService;
  }

  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.GET)
  public String addCategory(Model model) {
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    if(authentication.isAuthenticated()) {
      UserDetails details= (UserDetails) authentication.getPrincipal();
    }
    model.addAttribute("pageFragment", "addCategory");
    return "index";
  }

  @RequestMapping(value = {"/admin/book"}, method = RequestMethod.GET)
  public String addProduct(Model model) {
    model.addAttribute("pageFragment", "addBook");
    model.addAttribute("authors", authorsRepository.findAll());
    return "index";
  }


  @RequestMapping(value = {"/admin/category"}, method = RequestMethod.POST)
  public String createCategory(Model model,
                               @RequestParam String categoryName) {
    Category category = storeManagementService.createTopLevelCategory(categoryName);
    return "redirect:/admin/category";
  }


  @RequestMapping(value = {"/admin/book"}, method = RequestMethod.POST)
  public String createProduct(Model model,
                              @RequestParam String name,
                              @RequestParam Long categoryId,
                              @RequestParam String authors,
                              @RequestParam Long[] authorIds,
                              @RequestParam String isbn,
                              @RequestParam Double price,
                              @RequestParam String description,
                              MultipartFile picture) throws IOException, SQLException {

    Book product = storeManagementService.createBook(
      name,
      categoryId,
      authors.split(";"),
      authorIds,
      isbn,
      price
    );
    storeManagementService.addBookPicture(product.id, picture.getBytes(), picture.getContentType());

    model.addAttribute("product", product);
    return "index";
  }


}
