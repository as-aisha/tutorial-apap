package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.PenjagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PenjagaController {

    @Qualifier("penjagaServiceImpl")
    @Autowired
    PenjagaService penjagaService;

    @Qualifier("bioskopServiceImpl")
    @Autowired
    BioskopService bioskopService;

    @GetMapping("/penjaga/add/{noBioskop}")
    public String addPenjagaForm(@PathVariable Long noBioskop, Model model) {
        PenjagaModel penjaga = new PenjagaModel();
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        penjaga.setBioskop(bioskop);
        model.addAttribute("penjaga", penjaga);
        return "form-add-penjaga";
    }

    @PostMapping("/penjaga/add")
    public String addPenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ) {
        penjagaService.addPenjaga(penjaga);
        model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
        model.addAttribute("namaPenjaga", penjaga.getNamaPenjaga());
        return "add-penjaga";

    }

    @GetMapping("/penjaga/update/{noPenjaga}")
    public String updatePenjagaForm(
            @PathVariable Long noPenjaga,
            Model model
    ) {
        //Mendapatkan penjaga sesuai dengan noPenjaga
        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);

        //Add objek BioskopModel ke 'penjaga' untuk dirender dalam thymeleaf
        model.addAttribute("penjaga", penjaga);
        model.addAttribute("bioskop", penjaga.getBioskop());

        String keyword = penjagaService.updatePenjaga(penjaga);

        if (keyword.equals("waktu-failed")) {
            return "error-page-waktu-bioskop";
        } else {
            //Return view template yang diinginkan
            return "form-update-penjaga";
        }
    }

    @PostMapping("/penjaga/update")
    public String updatePenjagaSubmit(
            @ModelAttribute PenjagaModel penjaga,
            Model model
    ) {
        penjagaService.updatePenjaga(penjaga);
        model.addAttribute("noBioskop", penjaga.getBioskop().getNoBioskop());
        model.addAttribute("namaPenjaga", penjaga.getNamaPenjaga());
        return "update-penjaga";
    }

    @GetMapping("/penjaga/delete/{noPenjaga}")
    public String DeletePenjaga(
            @PathVariable Long noPenjaga,
            Model model
    ) {
        //Mendapatkan penjaga sesuai dengan noPenjaga
        PenjagaModel penjaga = penjagaService.getPenjagaByNoPenjaga(noPenjaga);

        model.addAttribute("namaPenjaga", penjaga.getNamaPenjaga());
        model.addAttribute("bioskop", penjaga.getBioskop());

        String keyword = penjagaService.deletePenjaga(penjaga);

        if (keyword.equals("waktu-failed")) {
            return "error-page-waktu-bioskop";
        } else {
            //Return view template yang diinginkan
            return "delete-penjaga";
        }
    }
}
