package ru.cs.vsu.yachnyy_m_a;

public class Flat {
    public String district;
    public int room_count;
    public int area;
    public int kitchen_area;
    public int price;

    public Flat(String district, int room_count, int area, int kitchen_area, int price) {
        this.district = district;
        this.room_count = room_count;
        this.area = area;
        this.kitchen_area = kitchen_area;
        this.price = price;
    }
}
