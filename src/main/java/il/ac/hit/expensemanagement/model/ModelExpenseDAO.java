package il.ac.hit.expensemanagement.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ModelExpenseDAO implements IModelExpenseDAO {

    private static SessionFactory factory;
    private static IModelExpenseDAO instance;

    static {
        instance = new ModelExpenseDAO();
    }


    public static IModelExpenseDAO getInstance() {
        return ModelExpenseDAO.instance;
    }

    private ModelExpenseDAO() {;
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }


    /**
     * Adds a new expense to the database
     *
     * @param item ExpenseItem object holds a reference to a new expense
     * @throws ExpenseManagerException
     */
    @Override
    public void addExpenseItem(ExpenseItem item) throws ExpenseManagerException {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.save(item);
            tx = session.getTransaction();
            tx.commit();

        } catch (HibernateException e) {
            if (tx == null)
                tx.rollback();
            throw new ExpenseManagerException("", e);
        }
        finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes an expense by id
     *
     * @param item ExpenseItem object holds a reference to a new expense
     * @throws ExpenseManagerException
     */
    @Override
    public void deleteExpenseItem(ExpenseItem item) throws ExpenseManagerException {
        int id = item.getId();
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            String hqlDelete = "delete ExpenseItem  where id =" + id;
            int deletedEntities = session.createQuery(hqlDelete).executeUpdate();

            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx == null)
                tx.rollback();
            throw new ExpenseManagerException("", e);
        }
        finally {
            try {
                if (session != null)
                    session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Extracts all the data from the database on expense item
     *
     * @return DataAllExpenses object which holds all the expense item data
     * @throws ExpenseManagerException
     */
    @Override
    public Report getAllExpenses(String user) throws ExpenseManagerException {
        Session session = null;
        List<ExpenseItem> items;
        ExpenseItem item;
        List<ExpenseItem> itemsFilter = new LinkedList<>();
        try {
            session = factory.openSession();
            session.beginTransaction();
            items = session.createQuery("from ExpenseItem").list();
            //

            for (int i = 0; i < items.size(); i++) {
                item = items.get(i);
                if (user.equals(item.getNameUser()) == true) {
                    itemsFilter.add(item);
                }
            }

        } catch (HibernateException e) {
            throw new ExpenseManagerException("", e);
        }
        finally {
            if (session != null)
                session.close();
        }
        return new Report(itemsFilter);
    }


    /**
     * Builds a Report object that holds all the expense items in the database between start and end dates
     *
     * @param start Holds reference to an sql.date object for the starting date
     * @param end   Holds reference to an sql.date object for the ending date
     * @return Report object that holds all the data between the dates start and end
     * @throws ExpenseManagerException
     */
    @Override
    public Report getReport(Date start, Date end,String user) throws ExpenseManagerException {

        List<ExpenseItem> items = getAllExpenses(user).getListItem();

        List<ExpenseItem> itemsFilter = new LinkedList<>();
        ExpenseItem item;
        System.out.println("getReport");

        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            if (item.getDate().after(start) && item.getDate().before(end)) {
                itemsFilter.add(item);
            }
        }

        return new Report(itemsFilter);
    }


    /**
     * Adds a new category to the table categories in the database
     *
     * @param category Represents the name of the new category
     * @throws ExpenseManagerException
     */
    @Override
    public void addCategory(Category category) throws ExpenseManagerException {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.save(category);
            tx.commit();

        } catch (HibernateException e) {
            if (tx == null)
                tx.rollback();
            throw new ExpenseManagerException("", e);
        }finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Extracts all the categories from the database table: categories
     *
     * @return list of categories
     * @throws ExpenseManagerException
     */
    @Override
    public List<Category> getCategories() throws ExpenseManagerException {
        Session session = null;
        List<Category> categories;
        try {
            session = factory.openSession();
            categories = session.createQuery("from Category").list();

            System.out.println("getCategories");

            Iterator i = categories.iterator();
            while (i.hasNext()) {
                System.out.println(i.next());
            }

        } catch (HibernateException e) {
            throw new ExpenseManagerException("", e);
        }
        finally {
            if (session != null)
                session.close();
        }
        return categories;
    }


    /**
     * @param user
     * @throws ExpenseManagerException
     */
    @Override
    public void addUser(User user) throws ExpenseManagerException {
        // login.. ... and registration
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.save(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (tx == null)
                tx.rollback();
            throw new ExpenseManagerException("", e);
        } finally {
            if (session != null)
                session.close();
        }

    }

    /**
     * @param userName
     * @return
     * @throws ExpenseManagerException
     */
    @Override
    public Boolean checkExistingUserName(String userName) throws ExpenseManagerException {
        Session session = null;
        boolean bool = true;

        try {
            session = factory.openSession();

            //  List<User> users = anotherSession.createQuery("from User where userName = 'dudi'").list();
            List<User> users = session.createQuery("from User").list();

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(userName)) {
                    bool = true;break;
                } else {
                    bool = false;
                }
            }
        } catch (HibernateException e) {
            throw new ExpenseManagerException("", e);
        } finally {
            if (session != null)
                session.close();
        }

        System.out.println(bool);
        return bool;
    }

    /**
     * @param user
     * @return
     * @throws ExpenseManagerException
     */
    @Override
    public Boolean login(User user) throws ExpenseManagerException {
        Session session = null;
        boolean bool = true;

        try {
            session = factory.openSession();
            List<User> users = session.createQuery("from User").list();

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(user.getUserName()) &&
                    users.get(i).getPassword().equals(user.getPassword()) ) {
                    bool = true;break;
                } else {
                    bool = false;
                }
            }
        } catch (HibernateException e) {
            throw new ExpenseManagerException("", e);
        } finally {
            if (session != null)
                session.close();
        }

        System.out.println(bool);
        return bool;
    }

}
