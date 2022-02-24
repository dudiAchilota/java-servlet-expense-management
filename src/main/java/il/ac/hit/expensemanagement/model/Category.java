package il.ac.hit.expensemanagement.model;

/**
 * This class represents a category for an expense
 */
public class Category {

    private int id;
    private String category;


    public Category() {}

    public Category(String category) {
        setCategory(category);
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id +
               ", category='" + category + '\'' ;
    }
}
