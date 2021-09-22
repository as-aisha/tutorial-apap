package apap.tutorial.cineplux.model;

public class BioskopModel {
    private String idBioskop;
    private String namaBioskop;
    private String alamat;
    private String noTelepon;
    private int jumlahStudio;

    public BioskopModel(String idBioskop, String namaBioskop, String alamat, String noTelepon, int jumlahStudio) {
        this.idBioskop = idBioskop;
        this.namaBioskop = namaBioskop;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.jumlahStudio = jumlahStudio;
    }

    public void setIdBioskop(String idBioskop) {
        this.idBioskop = idBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public void setJumlahStudio(int jumlahStudio) {
        this.jumlahStudio = jumlahStudio;
    }

    public String getIdBioskop() {
        return idBioskop;
    }

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public int getJumlahStudio() {
        return jumlahStudio;
    }
}
