import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter dan setter untuk variabel name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan setter untuk variabel price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter dan setter untuk variabel stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPriceFormatted() {
        return "Rp" + String.format("%,.2f", price);
    }

    public String toString() {
        return "Name  : " + name + "\n" +
               "Price : " + getPriceFormatted() + "\n" +
               "Stock : " + stock;
    }
}

class VapeStore {
    private ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addProduct() {
        clearScreen();
        System.out.println("|=======================|");
        System.out.println("|      Add Product      |");
        System.out.println("|=======================|");
        System.out.println("                         ");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: Rp");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); 

        Product product = new Product(name, price, stock);
        products.add(product);

        System.out.println("\nProduct added successfully!");
        pressEnterToContinue();
    }

    public void displayInventory() {
        clearScreen();
        System.out.println("================================");
        System.out.println("      Vape Store Inventory      ");
        System.out.println("================================");
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
                System.out.println();
            }
        }
        pressEnterToContinue();
    }

    public void updateProduct() {
        clearScreen();
        System.out.println("================================");
        System.out.println("          Update Product        ");
        System.out.println("            Vape Store          ");
        System.out.println("================================");
        if (products.isEmpty()) {
            System.out.println("No products available.");
            pressEnterToContinue();
            return;
        }

        System.out.print("Enter product name to update: ");
        String name = scanner.nextLine();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new price for " + product.getName() + ": Rp");
                double price = scanner.nextDouble();
                System.out.print("Enter new stock for " + product.getName() + ": ");
                int stock = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                product.setStock(stock);
                product.setPrice(price);

                System.out.println("\nProduct updated successfully!");
                pressEnterToContinue();
                return;
            }
        }

        System.out.println("Product with name " + name + " not found.");
        pressEnterToContinue();
    }

    public void deleteProduct() {
        clearScreen();
        System.out.println("================================");
        System.out.println("          Delete Product        ");
        System.out.println("            Vape Store          ");
        System.out.println("================================");
        if (products.isEmpty()) {
            System.out.println("No products available.");
            pressEnterToContinue();
            return;
        }

        System.out.print("Enter product name to delete: ");
        String name = scanner.nextLine();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                products.remove(product);
                System.out.println("\nProduct deleted successfully!");
                pressEnterToContinue();
                return;
            }
        }

        System.out.println("Product with name " + name + " not found.");
        pressEnterToContinue();
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine(); 
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VapeStore vapeStore = new VapeStore();

        while (true) {
            clearScreen();
            System.out.println("|=========================|");
            System.out.println("|        Vape Store       |");
            System.out.println("|                         |");
            System.out.println("| Paket   A.S.A.P    Kebul|");
            System.out.println("|=========================|");
            System.out.println("                           ");
            System.out.println("|=========================|");
            System.out.println("|1. Add Product           |");
            System.out.println("|2. Display Inventory     |");
            System.out.println("|3. Update Product        |");
            System.out.println("|4. Delete Product        |");
            System.out.println("|5. Exit                  |");
            System.out.println("|=========================|");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    vapeStore.addProduct();
                    break;
                case 2:
                    vapeStore.displayInventory();
                    break;
                case 3:
                    vapeStore.updateProduct();
                    break;
                case 4:
                    vapeStore.deleteProduct();
                    break;
                case 5:
                    System.out.println("\nExiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
                    pressEnterToContinue();
            }
        }
    }
}
