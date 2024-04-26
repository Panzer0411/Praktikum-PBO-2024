import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter dan setter untuk variabel id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "ID    : " + id + "\n" +
               "Name  : " + name + "\n" +
               "Price : " + getPriceFormatted() + "\n" +
               "Stock : " + stock;
    }
}

class Eliquid extends Product {
    private String flavor;
    private String nicotineLevel;

    public Eliquid(int id, String name, double price, int stock, String flavor, String nicotineLevel) {
        super(id, name, price, stock);
        this.flavor = flavor;
        this.nicotineLevel = nicotineLevel;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getNicotineLevel() {
        return nicotineLevel;
    }

    public void setNicotineLevel(String nicotineLevel) {
        this.nicotineLevel = nicotineLevel;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Flavor        : " + flavor + "\n" +
               "Nicotine Level: " + nicotineLevel;
    }
}

class Hardware extends Product {
    private String type;

    public Hardware(int id, String name, double price, int stock, String type) {
        super(id, name, price, stock);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
    }
}

class Accessory extends Product {
    private String type;

    public Accessory(int id, String name, double price, int stock, String type) {
        super(id, name, price, stock);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
    }
}

class VapeStore {
    private ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private int currentId = 1; // Menambahkan variabel currentId

    public void addProduct() {
        clearScreen();
        System.out.println("|=======================|");
        System.out.println("|      Add Product      |");
        System.out.println("|=======================|");
        System.out.println("                         ");
        int id = currentId++; // Menggunakan currentId dan menaikkannya
        System.out.println("Product ID: " + id); // Menampilkan ID produk
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: Rp");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter product type (Eliquid/Hardware/Accessory): ");
        String type = scanner.nextLine();

        Product product;
        switch (type.toLowerCase()) {
            case "eliquid":
                System.out.print("Enter product flavor: ");
                String flavor = scanner.nextLine();
                System.out.print("Enter product nicotine level: ");
                String nicotineLevel = scanner.nextLine();
                product = new Eliquid(id, name, price, stock, flavor, nicotineLevel);
                break;
            case "hardware":
                System.out.print("Enter product type (Mod/Atomizer/Coil): ");
                String hardwareType = scanner.nextLine();
                product = new Hardware(id, name, price, stock, hardwareType);
                break;
            case "accessory":
                System.out.print("Enter product type (Tank/Charger/Drip Tip): ");
                String accessoryType = scanner.nextLine();
                product = new Accessory(id, name, price, stock, accessoryType);
                break;
            default:
                System.out.println("Invalid product type.");
                pressEnterToContinue();
                return;
        }

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

        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Product product : products) {
            if (product.getId() == id) {
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

        System.out.println("Product with ID " + id + " not found.");
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

        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                System.out.println("\nProduct deleted successfully!");
                pressEnterToContinue();
                return;
            }
        }

        System.out.println("Product with ID " + id + " not found.");
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
            scanner.nextLine(); // Consume newline

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