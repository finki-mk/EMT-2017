package mk.ukim.finki.emt.persistence.impl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.PhraseMatchingContext;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Search methods for the entity User using Hibernate search.
 * The Transactional annotation ensure that transactions will be opened and
 * closed at the beginning and at the end of each method.
 */
@Repository
@Transactional(readOnly = true)
public class SearchRepository {

  // Spring will inject here the entity manager object
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * A basic search for the entity User. The search is done by exact match per
   * keywords on fields name, city and email.
   *
   * @param text The query text.
   */
  public <T> List<T> searchKeyword(Class<T> entityClass, String text, String... fields) {

    // get the full text entity manager
    FullTextEntityManager fullTextEntityManager =
      Search.getFullTextEntityManager(entityManager);

    // create the query using Hibernate Search query DSL
    QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
      .buildQueryBuilder().forEntity(entityClass).get();

    // a very basic query by keywords
    Query query =
      queryBuilder
        .keyword()
        .onFields(fields)
        .matching(text)
        .createQuery();

    // wrap Lucene query in an Hibernate Query object
    org.hibernate.search.jpa.FullTextQuery jpaQuery =
      fullTextEntityManager.createFullTextQuery(query, entityClass);

    // execute search and return results (sorted by relevance as default)
    List<T> results = jpaQuery.getResultList();

    return results;
  }

  public <T> List<T> searchPhrase(Class<T> entityClass, String text, String... fields) {
    // get the full text entity manager
    FullTextEntityManager fullTextEntityManager =
      Search.getFullTextEntityManager(entityManager);

    // create the query using Hibernate Search query DSL
    QueryBuilder qb = fullTextEntityManager.getSearchFactory()
      .buildQueryBuilder().forEntity(entityClass).get();

    Query query = null;

    BooleanJunction<BooleanJunction> bool = qb.bool();
    if (text.contains(" ")) {
      String[] tokens = text.split(" ");

      for (int i = 0; i < tokens.length - 1; i++) {
        try {
          bool.should(qb.keyword().onFields(fields).matching(tokens[i]).createQuery()).boostedTo(.5f);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      bool.should(getWildcardMultipleFiledQuery(qb, fields).matching(tokens[tokens.length - 1] + "*").createQuery()).boostedTo(.3f);

      PhraseMatchingContext phraseQuery = getMultipleFiledQuery(qb, fields);
      bool.should(phraseQuery.sentence(text).createQuery()).boostedTo(3f);
      query = bool.createQuery();
    } else {
      bool.should(getWildcardMultipleFiledQuery(qb, fields)
        .matching(text.toLowerCase() + "*").createQuery()).boostedTo(2f);
      bool.should(
        qb.keyword().fuzzy().
          withEditDistanceUpTo(1)
          .onFields(fields)
          .matching(text)
          .createQuery());
      query = bool.createQuery();
    }

    org.hibernate.search.jpa.FullTextQuery jpaQuery =
      fullTextEntityManager.createFullTextQuery(query, entityClass);

    // execute search and return results (sorted by relevance as default)
    List<T> results = jpaQuery.getResultList();

    return results;
  }

  public PhraseMatchingContext getMultipleFiledQuery(QueryBuilder qb, String... fields) {
    PhraseMatchingContext phraseQuery = qb.phrase().onField(fields[0]);
    for (int i = 1; i < fields.length; i++) {
      phraseQuery = phraseQuery.andField(fields[i]);
    }
    return phraseQuery;
  }

  public TermMatchingContext getWildcardMultipleFiledQuery(QueryBuilder qb, String... fields) {
    TermMatchingContext phraseQuery = qb.keyword().wildcard().onField(fields[0]);
    for (int i = 1; i < fields.length; i++) {
      phraseQuery = phraseQuery.andField(fields[i]);
    }
    return phraseQuery;
  }
}
