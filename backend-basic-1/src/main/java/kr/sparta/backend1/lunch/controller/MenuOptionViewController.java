package kr.sparta.backend1.lunch.controller;


import kr.sparta.backend1.lunch.service.MenuOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view/menu-option")
public class MenuOptionViewController {
    private final MenuOptionService service;
    public MenuOptionViewController(MenuOptionService service) { this.service = service; }

    @GetMapping
    public String search(@RequestParam(required = false) Long roundId,
                         @RequestParam(required = false) String foodType,
                         @RequestParam(required = false) String keyword,
                         Model model) {
        model.addAttribute("roundId", roundId);
        model.addAttribute("foodType", foodType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("results", service.search(roundId, foodType, keyword));
        return "menu-option/search";
    }
}