package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.model.FilmModel;
import apap.tutorial.cineplux.service.BioskopService;
import apap.tutorial.cineplux.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BioskopController {

    @Qualifier("bioskopServiceImpl")
    @Autowired
    private BioskopService bioskopService;
    private FilmService filmService;

    //Routing URL yang diinginkan
    @GetMapping("/bioskop/add")
    public String addBioskopForm(Model model) {
        model.addAttribute("bioskop", new BioskopModel());

        return "form-add-bioskop";
    }

    @PostMapping(value = "/bioskop/add", params = {"save"})
    public String addBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        bioskopService.addBioskop(bioskop);

        model.addAttribute("noBioskop", bioskop.getNoBioskop());

        return "add-bioskop";
    }

    @PostMapping(value = "/bioskop/add", params = {"addRow"})
    public String addRowFilmBioskop(
            @ModelAttribute BioskopModel bioskop,
            BindingResult bindingResult,
            Model model
    ) {
        List<FilmModel> listFilm = filmService.getListFilm();

        if (bioskop.getListFilm() == null) {
            bioskop.setListFilm(new ArrayList<FilmModel>());
        }

        List<FilmModel> newListFilm = bioskop.getListFilm();
        newListFilm.add(new FilmModel());

        model.addAttribute("listFilm", listFilm);
        model.addAttribute("bioskop", bioskop);
        return "form-add-bioskop";
    }

    @RequestMapping(value = "/bioskop/add", method = RequestMethod.POST, params = {"deleteRow"})
    public String deleteRowFilmBioskop(
            @ModelAttribute BioskopModel bioskop,
            final BindingResult bindingResult,
            final HttpServletRequest request,
            Model model
    ) {
        List <FilmModel> listFilm = filmService.getListFilm();
        final Integer rowId = Integer.valueOf(request.getParameter("deleteRow"));
        bioskop.getListFilm().remove(rowId.intValue());
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listFilm", listFilm);
        return "form-add-bioskop";
    }

    @GetMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();

        model.addAttribute("listBioskop", listBioskop);

        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/viewall-sorted")
    public String listBioskopByNama(Model model) {
        List<BioskopModel> listBioskopSorted = bioskopService.getBioskopListByNama();

        model.addAttribute("listBioskop", listBioskopSorted);

        return "viewall-bioskop";
    }

    @GetMapping("/bioskop/view")
    public String viewDetailBioskop(
            @RequestParam(value = "noBioskop") Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);
        List<PenjagaModel> listPenjaga = bioskop.getListPenjaga();
        List<FilmModel> listFilm = bioskop.getListFilm();

        model.addAttribute("bioskop", bioskop);
        model.addAttribute("listPenjaga", listPenjaga);
        model.addAttribute("listFilm", listFilm);

        return "view-bioskop";
    }

    @GetMapping("/bioskop/update/{noBioskop}")
    public String updateBioskopForm(
            @PathVariable Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        model.addAttribute("bioskop", bioskop);

        return "form-update-bioskop";
    }

    @PostMapping("/bioskop/update")
    public String updateBioskopSubmit(
            @ModelAttribute BioskopModel bioskop,
            Model model
    ) {
        bioskopService.updateBioskop(bioskop);

        model.addAttribute("noBioskop", bioskop.getNoBioskop());

        return "update-bioskop";
    }

    @GetMapping("/bioskop/delete/{noBioskop}")
    public String DeleteBioskop(
            @PathVariable Long noBioskop,
            Model model
    ) {
        BioskopModel bioskop = bioskopService.getBioskopByNoBioskop(noBioskop);

        model.addAttribute("bioskop", bioskop);

        String keyword = bioskopService.deleteBioskop(bioskop);

        if (keyword.equals("waktu-failed")) {
            return "error-page-waktu-bioskop";
        } else if (keyword.equals("delete-bioskop-failed")) {
            return "error-page-delete-bioskop";
        } else {

            return "delete-bioskop";
        }
    }

}
