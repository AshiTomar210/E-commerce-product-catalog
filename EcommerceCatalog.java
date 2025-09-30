import java.util.*;

class Product {
    private int id;
    private String name;
    private String category;
    private double price;

    public Product(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category + " | Rs." + price;
    }
}

public class EcommerceCatalog {
    private static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) {
        // Sample Data
        products.add(new Product(1, "Laptop", "Electronics", 45000));
        products.add(new Product(2, "Mobile", "Electronics", 15000));
        products.add(new Product(3, "Shoes", "Fashion", 2500));
        products.add(new Product(4, "T-Shirt", "Fashion", 800));
        products.add(new Product(5, "Washing Machine", "Appliances", 22000));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== E-COMMERCE PRODUCT CATALOG ===");
            System.out.println("1. View All Products");
            System.out.println("2. Search by Name");
            System.out.println("3. Filter by Category");
            System.out.println("4. Sort by Price (Low to High)");
            System.out.println("5. Sort by Price (High to Low)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    searchByName(name);
                    break;
                case 3:
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();
                    filterByCategory(cat);
                    break;
                case 4:
                    sortByPriceLowToHigh();
                    break;
                case 5:
                    sortByPriceHighToLow();
                    break;
                case 0:
                    System.out.println("Thank you for visiting!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void viewAllProducts() {
        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void searchByName(String name) {
        boolean found = false;
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No products found!");
    }

    private static void filterByCategory(String category) {
        boolean found = false;
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No products in this category!");
    }

    private static void sortByPriceLowToHigh() {
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        viewAllProducts();
    }

    private static void sortByPriceHighToLow() {
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        viewAllProducts();
    }
}
