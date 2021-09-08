package apap.tutorial.IsPalindrome.controller;


import apap.tutorial.IsPalindrome.model.Palindrome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PalindromeController {

    private String getPalindromePage (String kalimat, Model model) {

        if (kalimat.isEmpty()) {
            model.addAttribute("msg", "Tolong masukan sebuah kata: ");
        }
        else {
            final Palindrome palindrome = new Palindrome(kalimat);
            model.addAttribute("palindrome", palindrome);
        }

        return "isPalindromePage.html";

    }

    @GetMapping(value="/is-palindrome/{kalimat}")
    public String isPalindromeWithPathVariable(
            @PathVariable(value = "kalimat") String kalimat, Model model) {
        return getPalindromePage(kalimat, model);
    }
}