import java.util.ArrayList;
import java.util.Scanner;

abstract class AbstractProduct {
    protected final int id;
    protected final String name;
    protected final double price;
    protected final int stock;

    public AbstractProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public abstract String getType();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getPriceFormatted() {
        return "Rp" + String.format("%,.2f", price);
    }

    public abstract void setStock(int stock);

    public abstract void setPrice(double price);

    public String toString() {
        return "ID    : " + id + "\n" +
               "Name  : " + name + "\n" +
               "Price : " + getPriceFormatted() + "\n" +
               "Stock : " + stock + "\n" +
               "Type  : " + getType();
    }
}

class Eliquid extends AbstractProduct {
    private final String flavor;
    private final String nicotineLevel;

    public Eliquid(int id, String name, double price, int stock, String flavor, String nicotineLevel) {
        super(id, name, price, stock);
        this.flavor = flavor;
        this.nicotineLevel = nicotineLevel;
    }

    @Override
    public String getType() {
        return "Eliquid";
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Flavor        : " + flavor + "\n" +
               "Nicotine Level: " + nicotineLevel;
    }

    @Override
    public void setStock(int stock) {
        // Tidak perlu implementasi di sini karena stock bersifat final
    }

    @Override
    public void setPrice(double price) {
        // Tidak perlu implementasi di sini karena price bersifat final
    }
}

class Hardware extends AbstractProduct {
    private final String type;

    public Hardware(int id, String name, double price, int stock, String type) {
        super(id, name, price, stock);
        this.type = type;
    }

    @Override
    public String getType() {
        return "Hardware";
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
    }

    @Override
    public void setStock(int stock) {
        // Tidak perlu implementasi di sini karena stock bersifat final
    }

    @Override
    public void setPrice(double price) {
        // Tidak perlu implementasi di sini karena price bersifat final
    }
}

class Accessory extends AbstractProduct {
    private final String type;

    public Accessory(int id, String name, double price, int stock, String type) {
        super(id, name, price, stock);
        this.type = type;
    }

    @Override
    public String getType() {
        return "Accessory";
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Type          : " + type;
    }

    @Override
    public void setStock(int stock) {
        // Tidak perlu implementasi di sini karena stock bersifat final
    }

    @Override
    public void setPrice(double price) {
        // Tidak perlu implementasi di sini karena price bersifat final
    }
}

class VapeStore {
    private final ArrayList
    <Product> products = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);
    private final int currentId = 1; // Menambahkan variabel currentId

    public final void addProduct() {
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

        AbstractProduct product;
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

    public final void displayInventory() {
        clearScreen();
        System.out.println("================================");
        System.out.println("      Vape Store Inventory      ");
        System.out.println("================================");
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (AbstractProduct product : products) {
                System.out.println(product);
                System.out.println();
            }
        }
        pressEnterToContinue();
    }

    public final void updateProduct() {
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

        for (AbstractProduct product : products) {
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

    public final void deleteProduct() {
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

        for (AbstractProduct product : products) {
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

    private final static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private final static void clearScreen() {
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
