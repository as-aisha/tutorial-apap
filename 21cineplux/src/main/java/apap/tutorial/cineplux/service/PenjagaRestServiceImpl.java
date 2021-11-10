package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.PenjagaDB;
import apap.tutorial.cineplux.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
//    private final WebClient webClient;

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

//    public BioskopRestServiceImpl(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl(Setting.bioskopUrl).build();
//    }
//
//    @Override
//    public Mono<String> getStatus(Long noBioskop) {
//        return this.webClient.get().uri("/rest/bioskop/" + noBioskop + "/status")
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    @Override
//    public Mono<BioskopDetail> postStatus() {
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("namaBioskop", "Bioskop Mock Server");
//        data.add("alamatBioskop", "Depok");
//
//        return this.webClient.post().uri("/rest/bioskop/full")
//                .syncBody(data)
//                .retrieve()
//                .bodyToMono(BioskopDetail.class);
//    }

}

