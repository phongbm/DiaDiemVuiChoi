package com.phongbm.diadiemvuichoi;

public class DiaDiem {
    private String khuVuc, tenDiaDiem, linkHinh, diaChi;

    public DiaDiem(String khuVuc, String tenDiaDiem, String linkHinh, String diaChi) {
        this.khuVuc = khuVuc;
        this.tenDiaDiem = tenDiaDiem;
        this.linkHinh = linkHinh;
        this.diaChi = diaChi;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

}