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

class Eliquid extends Product {
    private String flavor;
    private String nicotineLevel;

    public Eliquid(String name, double price, int stock, String flavor, String nicotineLevel) {
        super(name, price, stock);
        this.flavor = flavor;
        this.nicotineLevel = nicotineLevel;
    }

    // Getter dan setter untuk variabel flavor
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    // Getter dan setter untuk variabel nicotineLevel
    public String getNicotineLevel() {
        return nicotineLevel;
    }

    public void setNicotineLevel(String nicotineLevel) {
        this.nicotineLevel = nicotineLevel;
    }

    public String toString() {
        return super.toString() + "\n" +
               "Flavor        : " + flavor + "\n" +
               "Nicotine Level: " + nicotineLevel;
    }
}

class Hardware extends Product {
    private String type;

    public Hardware(String name, double price, int stock, String type) {
        super(name, price, stock);
        this.type = type;
    }

    // Getter dan setter untuk variabel type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
    }
}

class Accessory extends Product {
    private String type;

    public Accessory(String name, double price, int stock, String type) {
        super(name, price, stock);
        this.type = type;
    }

    // Getter dan setter untuk variabel type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
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
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter product price: Rp");
                String priceInput = scanner.nextLine();
                price = Double.parseDouble(priceInput);
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Input price number.");
                pressEnterToContinue();
                addProduct(); // Rekursi, memanggil kembali addProduct() setelah kesalahan
                return; // Jangan lupa mengembalikan agar tidak menjalankan kode setelahnya
            }
        }
        System.out.print("Enter product stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product type (Eliquid/Hardware/Accessory): ");
        String type = scanner.nextLine();

        Product product;
        switch (type.toLowerCase()) {
            case "eliquid":
                System.out.print("Enter product flavor: ");
                String flavor = scanner.nextLine();
                System.out.print("Enter product nicotine level: ");
                String nicotineLevel = scanner.nextLine();
                product = new Eliquid(name, price, stock, flavor, nicotineLevel);
                break;
            case "hardware":
                System.out.print("Enter product type (Mod/Atomizer/Coil): ");
                String hardwareType = scanner.nextLine();
                product = new Hardware(name, price, stock, hardwareType);
                break;
            case "accessory":
                System.out.print("Enter product type (Tank/Charger/Drip Tip): ");
                String accessoryType = scanner.nextLine();
                product = new Accessory(name, price, stock, accessoryType);
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
