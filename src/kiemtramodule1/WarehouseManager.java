package kiemtramodule1;

import java.util.Scanner;

public class WarehouseManager {
    // KHO HÀNG: 5 danh mục, mỗi danh mục tối đa 20 sản phẩm
    // Rows (0-4): Danh mục. Cols (0-19): Sản phẩm
    private static final int MAX_CATEGORIES = 5;
    private static final int MAX_PRODUCTS_PER_CAT = 20;

    private static Product[][] storage = new Product[MAX_CATEGORIES][MAX_PRODUCTS_PER_CAT];

    private static int[] counts = new int[MAX_CATEGORIES];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ KHO ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Tìm kiếm theo tên");
            System.out.println("3. Xóa sản phẩm theo ID");
            System.out.println("4. Sắp xếp kho theo giá giảm dần (QuickSort)");
            System.out.println("5. Thống kê báo cáo");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: addProduct(); break;
                case 2: searchByName(); break;
                case 3: deleteById(); break;
                case 4: sortAndDisplayAll(); break;
                case 5: showStatistics(); break;
                case 0: System.exit(0);
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // --- CHỨC NĂNG 1: THÊM SẢN PHẨM ---
    public static void addProduct() {
        System.out.println("--- THÊM SẢN PHẨM ---");
        System.out.print("Nhập mã danh mục (0-Điện tử, 1-Gia dụng, 2-Thực phẩm, 3-Thời trang, 4-Khác): ");
        int catId = Integer.parseInt(scanner.nextLine());

        if (catId < 0 || catId >= MAX_CATEGORIES) {
            System.out.println("Mã danh mục không hợp lệ!");
            return;
        }

        // Kiểm tra đầy danh mục
        if (counts[catId] >= MAX_PRODUCTS_PER_CAT) {
            System.out.println("Danh mục đã đầy, không thể thêm sản phẩm!");
            return;
        }

        // Nhập thông tin
        System.out.print("Nhập ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập số lượng: ");
        int qty = Integer.parseInt(scanner.nextLine());

        Product newProduct = new Product(id, name, price, qty);

        // Kiểm tra trùng lặp bằng equals()
        boolean isExist = false;
        for (int i = 0; i < counts[catId]; i++) {
            if (storage[catId][i].equals(newProduct)) {
                isExist = true;
                break;
            }
        }

        StringBuilder sbMsg = new StringBuilder();
        if (isExist) {
            sbMsg.append("Sản phẩm đã tồn tại trong danh mục (trùng ID và Tên).");
        } else {
            storage[catId][counts[catId]] = newProduct;
            counts[catId]++;
            sbMsg.append("Đã thêm sản phẩm ").append(newProduct.getName())
                    .append(" vào danh mục ").append(catId);
        }
        System.out.println(sbMsg.toString());
    }

    // --- CHỨC NĂNG 2: TÌM KIẾM THEO TÊN ---
    public static void searchByName() {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < MAX_CATEGORIES; i++) {
            for (int j = 0; j < counts[i]; j++) {
                Product p = storage[i][j];
                // contains: kiểm tra chuỗi con
                if (p.getName().toLowerCase().contains(keyword)) {
                    System.out.println(p.toString());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("Không tìm thấy sản phẩm phù hợp.");
    }

    // --- CHỨC NĂNG 3: XÓA SẢN PHẨM (Dịch mảng) ---
    public static void deleteById() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int delId = Integer.parseInt(scanner.nextLine());
        boolean deleted = false;

        for (int i = 0; i < MAX_CATEGORIES; i++) {
            for (int j = 0; j < counts[i]; j++) {
                if (storage[i][j].getId() == delId) {
                    for (int k = j; k < counts[i] - 1; k++) {
                        storage[i][k] = storage[i][k + 1];
                    }
                    storage[i][counts[i] - 1] = null;

                    counts[i]--;
                    deleted = true;
                    System.out.println("Đã xóa sản phẩm ID " + delId + " khỏi danh mục " + i);

                    break;
                }
            }
        }

        if (!deleted) {
            System.out.println("Không tìm thấy sản phẩm cần xóa.");
        }
    }

    // --- CHỨC NĂNG 4: SẮP XẾP (QUICK SORT) ---
    public static void sortAndDisplayAll() {
        int totalProducts = 0;
        for (int c : counts) totalProducts += c;

        if (totalProducts == 0) {
            System.out.println("Kho trống.");
            return;
        }

        Product[] tempArr = new Product[totalProducts];
        int index = 0;
        for (int i = 0; i < MAX_CATEGORIES; i++) {
            for (int j = 0; j < counts[i]; j++) {
                tempArr[index++] = storage[i][j];
            }
        }
        quickSort(tempArr, 0, totalProducts - 1);
        System.out.println("--- DANH SÁCH SẢN PHẨM (GIÁ GIẢM DẦN) ---");
        for (Product p : tempArr) {
            System.out.println(p.toString());
        }
    }
    private static void quickSort(Product[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Product[] arr, int low, int high) {
        double pivot = arr[high].getPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].getPrice() > pivot) {
                i++;
                Product temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Product temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // --- CHỨC NĂNG 5: THỐNG KÊ (MA TRẬN) ---
    public static void showStatistics() {
        System.out.println("--- BÁO CÁO GIÁ TRỊ KHO HÀNG ---");
        System.out.printf("%-10s | %-20s\n", "Danh mục", "Tổng giá trị");
        System.out.println("--------------------------------");

        double maxVal = -1;
        int maxCatId = -1;

        for (int i = 0; i < MAX_CATEGORIES; i++) {
            double categoryTotal = 0;
            for (int j = 0; j < counts[i]; j++) {
                categoryTotal += (storage[i][j].getPrice() * storage[i][j].getQuantity());
            }
            System.out.printf("%-10d | %,20.0f\n", i, categoryTotal);
            if (categoryTotal > maxVal) {
                maxVal = categoryTotal;
                maxCatId = i;
            }
        }

        System.out.println("--------------------------------");
        if (maxCatId != -1) {
            System.out.println("Danh mục có giá trị lớn nhất: " + maxCatId + " (Giá trị: " + String.format("%,.0f", maxVal) + ")");
        } else {
            System.out.println("Kho chưa có dữ liệu.");
        }
    }
}