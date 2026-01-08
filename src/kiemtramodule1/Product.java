package kiemtramodule1;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = formatName(name); // Chuẩn hóa tên ngay khi khởi tạo
        this.price = price;
        this.quantity = quantity;
    }

    // --- YÊU CẦU 2.3: Hàm chuẩn hóa tên dùng StringBuilder ---
    public static String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        String[] words = name.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                String rest = word.substring(1).toLowerCase();
                sb.append(firstChar).append(rest).append(" ");
            }
        }
        return sb.toString().trim();
    }

    // --- YÊU CẦU 2.1: Override equals ---
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;
        return this.id == other.id && this.name.equalsIgnoreCase(other.name);
    }

    // --- YÊU CẦU 2.2: Override toString dùng StringBuilder ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id)
                .append(" | Name: ").append(this.name)
                .append(" | Price: ").append(String.format("%,.0f", this.price)) // Format số cho đẹp
                .append(" | Quantity: ").append(this.quantity);
        return sb.toString();
    }

    // Getter Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = formatName(name); }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}