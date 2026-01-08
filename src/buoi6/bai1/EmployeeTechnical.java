package buoi6.bai1;

public class EmployeeTechnical extends Employee {

    private int completedProjects; // Số dự án hoàn thành

    // Constructor không tham số
    public EmployeeTechnical() {
        super();
    }

    // Constructor đầy đủ tham số
    public EmployeeTechnical(String employeeId, String fullName, String address, double phoneNumber, int completedProjects) {
        // Gọi constructor của lớp cha (Employee) để gán các thuộc tính chung
        super(employeeId, fullName, address, phoneNumber);
        this.completedProjects = completedProjects;
    }

    /**
     * Ghi đè phương thức tính lương.
     * Ví dụ: Mỗi dự án được thưởng 2.000.000
     */
    @Override
    public int calculateSalary() {
        return completedProjects * 2000000;
    }

    @Override
    public void display() {
        super.display(); // Hiển thị thông tin chung (Id, Tên, Địa chỉ...)
        System.out.println("Completed Projects: " + completedProjects);
        System.out.println("Salary: " + calculateSalary() + " VND");
        System.out.println("--------------------------");
    }

    // Getter và Setter
    public int getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(int completedProjects) {
        this.completedProjects = completedProjects;
    }
}