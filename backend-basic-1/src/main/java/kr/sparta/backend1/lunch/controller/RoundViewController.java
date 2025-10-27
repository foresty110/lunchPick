package kr.sparta.backend1.lunch.controller;


import kr.sparta.backend1.lunch.service.RoundService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoundViewController {
    private final RoundService roundService;
    public RoundViewController(RoundService roundService) { this.roundService = roundService; }

    @GetMapping("/view/rounds/today")
    public String todayView(Model model) {
        model.addAttribute("rounds", roundService.getTodayRounds());
        return "rounds/today";
    }
}