package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BioskopServiceImpl implements BioskopService {

    @Autowired
    BioskopDB bioskopDB;

    @Override
    public void addBioskop(BioskopModel bioskop) {
        bioskopDB.save(bioskop);
    }

    @Override
    public void updateBioskop(BioskopModel bioskop) { bioskopDB.save(bioskop); }

    @Override
    public List<BioskopModel> getBioskopList() {
        return bioskopDB.findAll();
    }

    @Override
    public BioskopModel getBioskopByNoBioskop(Long noBioskop) {
        Optional<BioskopModel> bioskop = bioskopDB.findByNoBioskop(noBioskop);
        if (bioskop.isPresent()) {
            return bioskop.get();
        }
        return null;
    }

    @Override
    public List<BioskopModel> getBioskopListByNama() { return bioskopDB.findAll(Sort.by(Sort.Direction.ASC, "namaBioskop")); }

    @Override
    public String deleteBioskop(BioskopModel bioskop) {
        if (bioskop.getListPenjaga().isEmpty()) {
            if (bioskop.getWaktuBuka().isBefore(LocalTime.now()) || bioskop.getWaktuTutup().isAfter(LocalTime.now())) {
                return "waktu-failed";
            } else {
                bioskopDB.delete(bioskop);
                return "delete-success";
            }
        } else {
            return "delete-bioskop-failed";
        }
    }
}
