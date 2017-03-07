package mk.ukim.finki.emt.service.impl;

import ch.qos.logback.classic.Logger;
import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.service.CategoryServiceHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@Service
public class CategoryHelperImpl implements CategoryServiceHelper {


  static Logger logger = (Logger) LoggerFactory.getLogger(CategoryServiceHelper.class);

  private final CategoryRepository repository;

  @Autowired
  public CategoryHelperImpl(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> getTopLevelCategories() {
    return repository.findByParentIsNull();
  }

  @Override
  public List<Category> getSubCategories(Long categoryId) {
    return repository.findByParentId(categoryId);
  }

  @Override
  public Category createCategory(String name, long parentId) {
    Category category = new Category();
    category.name = name;
    category.parent = repository.findOne(parentId);
    return repository.save(category);
  }


  @Override
  public Category createTopLevelCategory(String name) {
    Category category = new Category();
    category.name = name;
    return repository.save(category);
  }

  @Override
  public Category updateCategoryName(Long id, String newName) {
    Category category = repository.findOne(id);
    logger.debug("category name changes from {} to {}.", category.name, newName);
    category.name = newName;
    return repository.save(category);
  }

  @Override
  public Category changeCategoryParent(Long id, Long parentId) {
    Category category = repository.findOne(id);
    Category parent = repository.findOne(parentId);
    assert (parent != null);
    logger.debug("category name changes from {} to {}.",
      category.parent.name,
      parent.name);
    category.parent = parent;
    return repository.save(category);
  }

  @Override
  public void removeCategory(Long id) throws CategoryInUseException {
    List<Category> children = repository.findByParentId(id);
    if (children != null && !children.isEmpty()) {
      throw new CategoryInUseException();
    }

    repository.delete(id);
    logger.debug("removed category with id {}.", id);
  }

  private void badCode(Integer a, Integer b) {
    if (a == null) {
      if (b == null) {
        // state a==b==null
      } else {
        // state a==null, b!=null
      }
    } else {
      if (b == null) {
        // state a!=null, b==null
      } else {
        // state a!=null, b!=null
      }
    }
  }

  public void betterCode(int a, Integer b) {
    if (b == null) {
      // state a!=null, b==null
    } else {
      // state a!=null, b!=null
    }
  }
}
