package buoi3;
import java.util.Scanner;

public class BTTrenLop {
    //Bài 1: Cấu trúc rẽ nhánh & Hàm (Kiểm tra năm nhuận)
    public static void checkLeapYear(int year) {
        // Logic: Chia hết cho 400 HOẶC (chia hết cho 4 và không chia hết cho 100)
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            System.out.println(year + " là năm nhuận.");
        } else {
            System.out.println(year + " là năm thường.");
        }
    }

    //Bài 2: Vòng lặp & Điều khiển luồng (Số nguyên tố)
    public static void printPrimes(int n) {
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            // Tối ưu: Chỉ kiểm tra đến căn bậc hai của i
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break; // Tìm thấy ước số, dừng vòng lặp ngay lập tức
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
    }

    //Bài 3: Vòng lặp do-while & Menu điều khiển
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Tính tổng từ 1 đến 100");
            System.out.println("2. In bảng chữ cái A-Z");
            System.out.println("3. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Tổng: " + calculateSum());
                    break;
                case 2:
                    printAlphabet();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 3);
    }

    static int calculateSum() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) sum += i;
        return sum;
    }

    static void printAlphabet() {
        for (char c = 'A'; c <= 'Z'; c++) System.out.print(c + " ");
    }

    //Bài 4: Giải thuật Đệ quy (Tính Giai thừa)
    public static long tinhGiaiThua(int n) {
        // 1. Điểm dừng (Base case)
        if (n == 0 || n == 1) return 1;
        // 2. Phần đệ quy
        return n * tinhGiaiThua(n - 1);
    }

    //Bài 5: Hệ thống Giao dịch Ngân hàng (Nâng cao)
   static class Account {
        static String bankName = "T3H Bank";
        String customerName;
        double balance;

        Account(String name, double initialBalance) {
            this.customerName = name;
            this.balance = initialBalance;
        }

        void showInfo() {
            System.out.println("[" + bankName + "] Khách hàng: " + customerName + " | Số dư: " + balance);
        }

        void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Lỗi: Số tiền nạp phải lớn hơn 0!");
            } else {
                double newBalance = balance + amount; // Biến local
                balance = newBalance;
                System.out.println("Nạp tiền thành công.");
            }
        }

        // Đệ quy tính lãi suất kép (1%/tháng)
        double calculateCompoundInterest(double currentBalance, int months) {
            if (months == 0) return currentBalance; // Điểm dừng
            return calculateCompoundInterest(currentBalance * 1.01, months - 1); // Đệ quy
        }
    }

    // Trong hàm Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account myAcc = new Account("Nguyen Van A", 1000);

        double amount;
        do {
            System.out.print("Nhập số tiền muốn nạp (nhập 0 để dừng): ");
            amount = sc.nextDouble();
            if (amount != 0) {
                myAcc.deposit(amount);
                myAcc.showInfo();
            }
        } while (amount != 0);

        System.out.print("Nhập số tháng muốn gửi lấy lãi: ");
        int months = sc.nextInt();
        double futureBalance = myAcc.calculateCompoundInterest(myAcc.balance, months);
        System.out.printf("Số dư dự kiến sau %d tháng là: %.2f", months, futureBalance);
    }
}
