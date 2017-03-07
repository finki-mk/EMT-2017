package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryHelperTest {

  @Autowired
  private CategoryServiceHelper categoryServiceHelper;

  @Autowired
  private CategoryRepository repository;

  private Category parent;
  private Category child;
  private Category nephew;

  @After
  public void clearTestEntities() {
    if (nephew != null) {
      repository.delete(nephew);
    }
    if (child != null) {
      repository.delete(child);
    }
    if (parent != null) {
      repository.delete(parent);
    }
  }

  @Test
  public void testCrud() throws CategoryInUseException {
    parent = categoryServiceHelper.createTopLevelCategory("parent");
    Assert.assertNotNull("Parent category is without id!", parent.id);
    parent = repository.findOne(parent.id);
    Assert.assertNotNull("Parent category not present in DB!", parent);

    child = categoryServiceHelper.createCategory("chil", parent.id);
    Assert.assertNotNull("Child without id!", child.id);

    child = categoryServiceHelper.updateCategoryName(child.id, "child");

    Assert.assertEquals("Child name is not updated!", "child", child.name);

    nephew = categoryServiceHelper.createCategory("nephew", parent.id);
    nephew = categoryServiceHelper.changeCategoryParent(nephew.id, child.id);
    Assert.assertEquals("nephew not moved!", child.id, nephew.parent.id);

    List<Category> topLevelCategories = categoryServiceHelper.getTopLevelCategories();
    Assert.assertEquals("parent not found as top level category!",
      true, topLevelCategories.contains(parent)
    );

    List categoriesInParent = categoryServiceHelper.getSubCategories(parent.id);
    Assert.assertEquals("child not found in 'parent' category!",
      true, categoriesInParent.contains(child)
    );

    Assert.assertEquals("wrong number of children in 'parent' category!",
      1, categoriesInParent.size()
    );

    CategoryInUseException expectedException = null;

    try {
      categoryServiceHelper.removeCategory(parent.id);
    } catch (CategoryInUseException ex) {
      expectedException = ex;
    }
    Assert.assertNotNull("category in use exception not thrown", expectedException);


    categoryServiceHelper.removeCategory(nephew.id);
    categoryServiceHelper.removeCategory(child.id);
    categoryServiceHelper.removeCategory(parent.id);
    parent = null;
    child = null;
    nephew = null;

  }


  @Test
  public void testFindCategoryByParent() {
    List<Category> result = categoryServiceHelper.getTopLevelCategories();
    System.out.println(result);
  }

  @Test
  public void testFindSubcategories() {
    List<Category> result = categoryServiceHelper.getSubCategories(1l);
    System.out.println(result);
  }
}
