package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class BioskopController {
    @Autowired
    private BioskopService bioskopService;

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

    //Challenge
    //Routing URL yang diinginkan
    @RequestMapping("/bioskop/add-no-telp")
    public String addBioskopMinTelp(
            //Request parameter yang ingin digunakan
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = false) String noTelepon,
            @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
            Model model
    ) {
        //Membuat objek BioskopModel
        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop, alamat, noTelepon, jumlahStudio);

        //Mengatur no telepon menjadi "-" apabila user tidak memasukkan parameter no telepon ketika menambahkan bioskop
        if (bioskopModel.getNoTelepon() == null) {
            bioskopModel.setNoTelepon("-");
        }

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
}
