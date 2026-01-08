package buoi6.bai1;

public class EmployeeSales extends Employee {

    private int salesAmount; // Doanh số bán hàng

    public EmployeeSales() {
        super();
    }

    public EmployeeSales(String employeeId, String fullName, String address, double phoneNumber, int salesAmount) {
        super(employeeId, fullName, address, phoneNumber);
        this.salesAmount = salesAmount;
    }

    /**
     * Ghi đè phương thức tính lương.
     * Ví dụ: Lương = 10% của doanh số
     */
    @Override
    public int calculateSalary() {
        return (int) (salesAmount * 0.10); // Ép kiểu về int vì hàm cha trả về int
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Sales Amount: " + salesAmount);
        System.out.println("Salary: " + calculateSalary() + " VND");
        System.out.println("--------------------------");
    }

    // Getter và Setter
    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }
}