
package com.company.safalmart.dao;

import com.company.safalmart.entities.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CategoryDao {
    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public int saveCategory( Category category){
        
        Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();
        int categoryId=(int) session.save(category);
        tx.commit();
        session.close();
        
        return categoryId;
        
    }
    
    public List<Category> getCategories(){
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Category");
        List<Category> list=query.list();
        return list;
    }
    
    public  Category getCategoryById(int cid){
        Category cat=null;
        try {
            Session session = this.factory.openSession();
            cat = session.getReference(Category.class, cid);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  cat;
    }
}
