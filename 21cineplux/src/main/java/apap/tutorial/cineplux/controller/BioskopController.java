package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BioskopController {

    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;

    //Routing URL yang diinginkan
    @GetMapping("/bioskop/add")
    public String addBioskopForm(Model model) {
        //Add variabel ... ke "..." untuk dirender ke dalam thymeleaf
        model.addAttribute("bioskop", new BioskopModel());
        //Return view template yang digunakan
        return "form-add-bioskop";
    }

    @PostMapping("/bioskop/add")
    public String addBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        //Menambahkan objek BioskopModel ke dalam service
        bioskopService.addBioskop(bioskop);
        //Add variabel ... ke "..." untuk dirender ke dalam thymeleaf
        model.addAttribute("noBioskop", bioskop.getNoBioskop());
        //Return view template yang digunakan
        return "add-bioskop";
    }

    @GetMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        //Mendapatkan semua bioskop
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();

        //Add variable semua BioskopModel ke 'listBioskop' untuk dirender dalam thymeleaf
        model.addAttribute("listBioskop", listBioskop);

        //Return view template yang diinginkan
        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/viewall-sorted")
    public String listBioskopByNama(Model model) {
        //Mendapatkan semua bioskop
        List<BioskopModel> listBioskopSorted = bioskopService.getBioskopListByNama();

        //Add variable semua BioskopModel ke 'listBioskop' untuk dirender dalam thymeleaf
        model.addAttribute("listBioskop", listBioskopSorted);

        //Return view template yang diinginkan
        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/view")
    public String viewDetailBioskop(
            @RequestParam(value = "noBioskop") Long noBioskop,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();

        //Add variable BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
        model.addAttribute("bioskop", bioskop);

        //Add variable listPenjaga ke 'listPenjaga' untuk dirender dalam thymeleaf
        model.addAttribute("listPenjaga", listPenjaga);

        //Return view template yang diinginkan
        return "view-bioskop";


    }

    @GetMapping("/bioskop/update/{noBioskop}")
    public String updateBioskopForm(
            @PathVariable Long noBioskop,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        //Add objek BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
        model.addAttribute("bioskop", bioskop);

        //Return view template yang diinginkan
        return "form-update-bioskop";
    }

    @PostMapping("/bioskop/update")
    public String updateBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        //Meng-update objek BioskopModel ke dalam service
        bioskopService.updateBioskop(bioskop);

        //Add no bioskop ke 'noBioskop' untuk dirender dalam thymeleaf
        model.addAttribute("noBioskop", bioskop.getNoBioskop());

        //Return view template yang diinginkan
        return "update-bioskop";
    }

    @GetMapping("/bioskop/delete/{noBioskop}")
    public String DeleteBioskop(
            @PathVariable Long noBioskop,
            Model model
    ) {
        //Mendapatkan penjaga sesuai dengan noPenjaga
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        model.addAttribute("bioskop", bioskop);

        String keyword = bioskopService.deleteBioskop(bioskop);

        if (keyword.equals("waktu-failed")) {
            return "error-page-waktu-bioskop";
        } else if (keyword.equals("delete-bioskop-failed")) {
            return "error-page-delete-bioskop";
        } else {
            //Return view template yang diinginkan
            return "delete-bioskop";
        }
    }
}

/*
    //Routing URL yang diinginkan
    @RequestMapping("/bioskop/add")
    public String addBioskop(
        //Request parameter yang ingin digunakan
        @RequestParam(value = "idBioskop", required = true) String idBioskop,
        @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon,
        @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
        Model model
    ) {
        //Membuat objek BioskopModel
        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop, alamat, noTelepon, jumlahStudio);

        //Menambahkan objek BioskopModel ke dalam service
        bioskopService.addBioskop(bioskopModel);

        //Add variabel id bioskop ke "idBioskop" untuk dirender ke dalam thymeleaf
        model.addAttribute("idBioskop", idBioskop);

        //Return view template yang digunakan
        return "add-bioskop";
    }

    @RequestMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        //Mendapatkan semua bioskop
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();

        //Add variable semua BioskopModel ke 'listBioskop' untuk dirender dalam thymeleaf
        model.addAttribute("listBioskop", listBioskop);

        //Return view template yang diinginkan
        return "viewall-bioskop";
    }

    @RequestMapping("/bioskop/view")
    public String detailBioskop(
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);

        if (bioskopModel != null) {
            //Add variable BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
            model.addAttribute("bioskop", bioskopModel);

            //Return view template yang diinginkan
            return "view-bioskop";

        } else {
            //Return view template error page
            return "error-page";
        }
    }

    @RequestMapping("bioskop/view/id-bioskop/{idBioskop}")
    public String viewBioskop(
            @PathVariable("idBioskop") String idBioskop,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);

        if (bioskopModel != null) {
            //Add variable BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
            model.addAttribute("bioskop", bioskopModel);

            //Return view template yang diinginkan
            return "view-bioskop";
        } else {
            //Return view template error page
            return "error-page";
        }
    }

    @RequestMapping("bioskop/update/id-bioskop/{idBioskop}/jumlah-studio/{jumlahStudio}")
    public String updateJumlahStudio(
            @PathVariable("idBioskop") String idBioskop,
            @PathVariable("jumlahStudio") int jumlahStudio,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);

        if (bioskopModel != null) {
            //Meng-update jumlah studio pada bioskop
            bioskopModel.setJumlahStudio(jumlahStudio);

            //Add variable BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
            model.addAttribute("bioskop", bioskopModel);

            //Return view template yang diinginkan
            return "view-bioskop";
        } else {
            //Return view template error page
            return "error-page";
        }
    }

    @RequestMapping("bioskop/delete/id-bioskop/{idBioskop}")
    public String deleteBioskop(
            @PathVariable("idBioskop") String idBioskop,
            Model model
    ) {
        //Mendapatkan bioskop sesuai dengan idBioskop
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);

        if (bioskopModel != null) {
            //Add variable BioskopModel ke 'bioskop' untuk dirender dalam thymeleaf
            model.addAttribute("bioskop", bioskopModel);

            //Menghapus objek BioskopModel dari dalam service
            bioskopService.deleteBioskop(bioskopModel);

            //Return view template yang diinginkan
            return "delete-bioskop";
        } else {
            //Return view template error page
            return "error-page";
        }
    }
 */
