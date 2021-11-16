package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.rest.PredictAge;
import apap.tutorial.cineplux.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PenjagaRestServiceImpl implements PenjagaRestService{
    private final WebClient webClient;

    public PenjagaRestServiceImpl (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.ageUrl).build();
    }

    @Autowired
    private PenjagaDB penjagaDB;

    @Override
    public PenjagaModel createPenjaga(PenjagaModel penjaga){
        return penjagaDB.save(penjaga);
    }

    @Override
    public List<PenjagaModel> retrieveListPenjaga(){
        return penjagaDB.findAll();
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> penjaga = penjagaDB.findByNoPenjaga(noPenjaga);

        if (penjaga.isPresent()) {
            return penjaga.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PenjagaModel updatePenjaga(Long noPenjaga, PenjagaModel penjagaUpdate){
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        penjaga.setNamaPenjaga(penjagaUpdate.getNamaPenjaga());
        penjaga.setJenisKelamin(penjagaUpdate.getJenisKelamin());
        return penjagaDB.save(penjaga);
    }

    @Override
    public void deletePenjaga(Long noPenjaga){
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        LocalTime now = LocalTime.now();
        BioskopModel bioskop = penjaga.getBioskop();
        if(now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup())){
            penjagaDB.delete(penjaga);
        }else {
            throw new UnsupportedOperationException("Bioskop still open!");
        }
    }

    @Override
    public void updateUmurPenjaga(Long noPenjaga, PenjagaModel penjagaUpdate){
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        penjaga.setNamaPenjaga(penjagaUpdate.getNamaPenjaga());
        penjaga.setJenisKelamin(penjagaUpdate.getJenisKelamin());
        penjaga.setUmur(penjagaUpdate.getUmur());
        penjagaDB.save(penjaga);


    }

    @Override
    public PredictAge getPrediksiUmur(Long noPenjaga) {
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = penjaga.getBioskop();
        if(now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup())) {
            String namaPenjaga = penjaga.getNamaPenjaga().split(" ")[0];
            return this.webClient.get().uri("/?name=" + namaPenjaga)
                    .retrieve()
                    .bodyToMono(PredictAge.class).block();
        } else {
            throw new UnsupportedOperationException("Bioskop still open!");
        }
    }


}

