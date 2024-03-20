import java.util.Scanner;


class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;

    
    public User(String username, String password, String fullName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Authentication {
    private User[] users;
    private int userCount;

    
    public Authentication() {
        users = new User[100]; 
        userCount = 0;
    }

    public void register(String username, String password, String fullName, String email, String phoneNumber) {
        users[userCount] = new User(username, password, fullName, email, phoneNumber);
        userCount++;
        System.out.println("Registrasi berhasil!");
    }

    // Method  login
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login berhasil!");
                return true;
            }
        }
        System.out.println("Username atau password salah.");
        return false;
    }

    // Method untuk menampilkan data user setelah login
    public void displayUserInfo(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username)) {
                System.out.println("\nInformasi Pengguna:");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Nama lengkap: " + user.getFullName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Nomor Telepon: " + user.getPhoneNumber());
                return;
            }
        }
        System.out.println("User tidak ditemukan.");
    }

    // Method untuk memeriksa apakah username sudah terdaftar
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

// Class utama
public class SistemLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nim = 123456; 
        String systemType;
        if (nim % 3 == 0) {
            systemType = "Facebook";
        } else if (nim % 3 == 1) {
            systemType = "Genshin Impact";
        } else {
            systemType = "Mobile Banking";
        }
        System.out.println("Sistem Login dan Registrasi " + systemType);

        Authentication authentication = new Authentication();

        // Menu utama
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membuang newline

            switch (choice) {
                case 1:
                    System.out.println("\nLogin:");
                    System.out.print("Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();
                    if (authentication.login(loginUsername, loginPassword)) {
                        authentication.displayUserInfo(loginUsername);
                        displayCRUDMenu();
                    }
                    break;
                case 2:
                    System.out.println("\nRegistrasi:");
                    System.out.print("Username: ");
                    String regUsername = scanner.nextLine();
                    if (authentication.isUsernameTaken(regUsername)) {
                        System.out.println("Username sudah terdaftar.");
                        break;
                    }
                    System.out.print("Password: ");
                    String regPassword = scanner.nextLine();
                    System.out.print("Nama lengkap: ");
                    String regFullName = scanner.nextLine();
                    System.out.print("Email: ");
                    String regEmail = scanner.nextLine();
                    System.out.print("Nomor Telepon: ");
                    String regPhoneNumber = scanner.nextLine();
                    authentication.register(regUsername, regPassword, regFullName, regEmail, regPhoneNumber);
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void displayCRUDMenu() {
        System.out.println("\nMenu CRUD:");
        System.out.println("1. Tambah data");
        System.out.println("2. Lihat data");
        System.out.println("3. Ubah data");
        System.out.println("4. Hapus data");
        System.out.println("5. Logout");
        System.out.print("Pilih: ");
    }
}
