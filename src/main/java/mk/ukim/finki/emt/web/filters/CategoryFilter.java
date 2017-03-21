package mk.ukim.finki.emt.web.filters;

import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by bubo on 3/9/16.
 */
@Component
public class CategoryFilter implements Filter {


    private QueryService service;

    @Autowired
    public CategoryFilter(QueryService service) {
        this.service = service;
    }






    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
      ServletRequest servletRequest,
      ServletResponse servletResponse,
      FilterChain filterChain
    ) throws IOException, ServletException {
        List<Category> categories = service.findTopLevelCategories();
        servletRequest.setAttribute("categories", categories);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
