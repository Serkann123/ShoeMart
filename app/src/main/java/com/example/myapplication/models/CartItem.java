package com.example.myapplication.models;

public class CartItem {

    private int sepetId;
    private int userId;
    private String urunAdi;
    private double fiyat;
    private String resim;

    public int getSepetId() { return sepetId; }
    public void setSepetId(int sepetId) { this.sepetId = sepetId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUrunAdi() { return urunAdi; }
    public void setUrunAdi(String urunAdi) { this.urunAdi = urunAdi; }

    public double getFiyat() { return fiyat; }
    public void setFiyat(double fiyat) { this.fiyat = fiyat; }

    public String getResim() { return resim; }
    public void setResim(String resim) { this.resim = resim; }
}
