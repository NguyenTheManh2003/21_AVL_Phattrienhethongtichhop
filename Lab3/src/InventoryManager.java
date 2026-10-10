import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static final Path INVENTORY_FILE =
            Path.of("data", "inventory.csv");

    private static final Path REPORT_FILE =
            Path.of("data", "inventory-report.txt");

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        BufferedReader console = new BufferedReader(
                new InputStreamReader(
                        System.in,
                        StandardCharsets.UTF_8
                )
        );

        // 1. Nhập sản phẩm
        inputProducts(console, products);

        // 2. Lưu xuống CSV
        saveProducts(products);

        // 3. Đọc lại CSV
        List<Product> loadedProducts = loadProducts();

        // 4. Hiển thị danh sách
        displayProducts(loadedProducts);

        // 5. Tính tổng
        double total = calculateTotal(loadedProducts);

        System.out.printf(
                "%nTổng giá trị tồn kho: %,.0f VND%n",
                total
        );

        // 6. Tìm sản phẩm có giá trị tồn kho cao nhất
        Product highest = findHighestInventoryValue(loadedProducts);

        if (highest != null) {
            System.out.println(
                    "Sản phẩm có giá trị tồn kho cao nhất:"
            );
            System.out.println(highest);
        }

        // 7. Ghi báo cáo
        writeReport(loadedProducts, total, highest);
    }


    // =========================
    // NHẬP SẢN PHẨM
    // =========================

    private static void inputProducts(
            BufferedReader console,
            List<Product> products) {

        try {
            System.out.print("Nhập số lượng sản phẩm: ");

            int n = Integer.parseInt(console.readLine());

            for (int i = 0; i < n; i++) {

                System.out.println(
                        "\n--- Sản phẩm " + (i + 1) + " ---"
                );

                try {
                    System.out.print("Mã: ");
                    String code = console.readLine();

                    System.out.print("Tên: ");
                    String name = console.readLine();

                    System.out.print("Đơn giá: ");
                    double price =
                            Double.parseDouble(console.readLine());

                    System.out.print("Số lượng: ");
                    int quantity =
                            Integer.parseInt(console.readLine());

                    Product product = new Product(
                            code,
                            name,
                            price,
                            quantity
                    );

                    products.add(product);

                } catch (IllegalArgumentException e) {

                    System.err.println(
                            "Dữ liệu không hợp lệ: "
                                    + e.getMessage()
                    );

                    // Cho nhập lại sản phẩm này
                    i--;
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Lỗi nhập dữ liệu: " + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.err.println(
                    "Số lượng sản phẩm không hợp lệ."
            );
        }
    }


    // =========================
    // LƯU CSV
    // =========================

    private static void saveProducts(List<Product> products) {

        try {

            Files.createDirectories(
                    INVENTORY_FILE.getParent()
            );

            try (BufferedWriter writer =
                         Files.newBufferedWriter(
                                 INVENTORY_FILE,
                                 StandardCharsets.UTF_8)) {

                writer.write("ma,ten,donGia,soLuong");
                writer.newLine();

                for (Product p : products) {

                    writer.write(
                            p.getCode() + ","
                                    + p.getName() + ","
                                    + p.getUnitPrice() + ","
                                    + p.getQuantity()
                    );

                    writer.newLine();
                }
            }

            System.out.println(
                    "\nĐã lưu vào: "
                            + INVENTORY_FILE.toAbsolutePath()
            );

        } catch (IOException e) {

            System.err.println(
                    "Không ghi được tệp "
                            + INVENTORY_FILE
                            + ": "
                            + e.getMessage()
            );
        }
    }


    // =========================
    // ĐỌC CSV
    // =========================

    private static List<Product> loadProducts() {

        List<Product> products = new ArrayList<>();

        if (!Files.exists(INVENTORY_FILE)) {

            System.err.println(
                    "Không tìm thấy tệp: "
                            + INVENTORY_FILE
            );

            return products;
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             INVENTORY_FILE,
                             StandardCharsets.UTF_8)) {

            // Bỏ qua tiêu đề
            reader.readLine();

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {

                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length != 4) {

                    System.err.println(
                            "Dòng " + lineNumber
                                    + " thiếu cột, bỏ qua."
                    );

                    continue;
                }

                try {

                    Product product = new Product(
                            parts[0].trim(),
                            parts[1].trim(),
                            Double.parseDouble(
                                    parts[2].trim()),
                            Integer.parseInt(
                                    parts[3].trim())
                    );

                    products.add(product);

                } catch (IllegalArgumentException e) {

                    System.err.println(
                            "Dòng " + lineNumber
                                    + " không hợp lệ: "
                                    + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Không đọc được tệp "
                            + INVENTORY_FILE
                            + ": "
                            + e.getMessage()
            );
        }

        return products;
    }


    // =========================
    // HIỂN THỊ
    // =========================

    private static void displayProducts(
            List<Product> products) {

        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");

        for (Product product : products) {
            System.out.println(product);
        }
    }


    // =========================
    // TÍNH TỔNG
    // =========================

    private static double calculateTotal(
            List<Product> products) {

        double total = 0;

        for (Product product : products) {
            total += product.inventoryValue();
        }

        return total;
    }


    // =========================
    // TÌM GIÁ TRỊ CAO NHẤT
    // =========================

    private static Product findHighestInventoryValue(
            List<Product> products) {

        if (products.isEmpty()) {
            return null;
        }

        Product highest = products.get(0);

        for (Product product : products) {

            if (product.inventoryValue()
                    > highest.inventoryValue()) {

                highest = product;
            }
        }

        return highest;
    }


    // =========================
    // GHI BÁO CÁO
    // =========================

    private static void writeReport(
            List<Product> products,
            double total,
            Product highest) {

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             REPORT_FILE,
                             StandardCharsets.UTF_8)) {

            writer.write(
                    "Số sản phẩm: " + products.size()
            );

            writer.newLine();

            writer.write(
                    "Tổng giá trị tồn kho: %,.0f VND"
                            .formatted(total)
            );

            writer.newLine();

            if (highest != null) {

                writer.write(
                        "Sản phẩm có giá trị tồn kho cao nhất: "
                                + highest
                );

                writer.newLine();
            }

            System.out.println(
                    "\nĐã ghi báo cáo: "
                            + REPORT_FILE.toAbsolutePath()
            );

        } catch (IOException e) {

            System.err.println(
                    "Không ghi được báo cáo "
                            + REPORT_FILE
                            + ": "
                            + e.getMessage()
            );
        }
    }
}