package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.repository.BioskopDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class PenjagaServiceImpl implements PenjagaService {

    @Autowired
    PenjagaDB penjagaDB;
    BioskopDB bioskopDB;

    @Override
    public void addPenjaga(PenjagaModel penjaga) {
        penjagaDB.save(penjaga);
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> penjaga = penjagaDB.findByNoPenjaga(noPenjaga);
        if (penjaga.isPresent()) {
            return penjaga.get();
        }
        return null;
    }

    @Override
    public String updatePenjaga(PenjagaModel penjaga) {
        LocalTime now = LocalTime.now();
        BioskopModel bioskop = penjaga.getBioskop();
        if (now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup())) {
            penjagaDB.save(penjaga);
            return "update-success";
        }
        return "waktu-failed";
    }

    @Override
    public String deletePenjaga(PenjagaModel penjaga) {
        if (penjaga.getBioskop().getWaktuBuka().isBefore(LocalTime.now()) || penjaga.getBioskop().getWaktuTutup().isAfter(LocalTime.now())) {
            return "waktu-failed";
        } else {
            penjagaDB.delete(penjaga);
            return "delete-success";
        }
    }


    @Override
    public int deletePenjagaSubmit(PenjagaModel penjaga) {
        LocalTime now = LocalTime.now();
        BioskopModel bioskop = penjaga.getBioskop();
        if (now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup())) {
            penjagaDB.delete(penjaga);
            return 1;
        }
        return 0;
    }
}
