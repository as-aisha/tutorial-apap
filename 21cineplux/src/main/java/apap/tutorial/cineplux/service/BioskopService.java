package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;

import java.util.List;

public interface BioskopService {
    //Method untuk menambah Bioskop
    void addBioskop(BioskopModel bioskop);

    void updateBioskop(BioskopModel bioskop);

    //Method untuk mendapatkan daftar Bioskop yang telah tersimpan
    List<BioskopModel> getBioskopList();

    //Method untuk mendapatkan data sebuah bioskop berdasarkan no bioskop
    BioskopModel getBioskopByNoBioskop(Long noBioskop);

    List<BioskopModel> getBioskopListByNama();

    String deleteBioskop(BioskopModel bioskop);
}

/*
    //Method untuk mendapatkan data sebuah bioskop berdasarkan id bioskop
    void deleteBioskop(BioskopModel bioskop);
 */
