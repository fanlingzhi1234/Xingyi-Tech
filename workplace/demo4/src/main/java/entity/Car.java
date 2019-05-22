package entity;

public class Car {
    private String brand;
    private String location;
    private double price;
    private int miles;

    public Car(){

    }
    public Car(String brand, String location, int miles) {
        this.brand = brand;
        this.location = location;
        this.miles = miles;
    }

    public Car(String brand, String location, double price) {
        this.brand = brand;
        this.location = location;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", miles=" + miles +
                '}';
    }
}
