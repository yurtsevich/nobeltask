package info.yurtsevich.nobel.web;

import info.yurtsevich.nobel.model.Game;
import info.yurtsevich.nobel.service.GameEngine;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by yurtsevich on 10/15/21
 */
@Controller
@CommonsLog
public class GameController {
    @Autowired
    private GameEngine gameEngine;

    @GetMapping("/game")
    public String showGameForm(Model model) {
        if (!model.containsAttribute("game")) {
            model.addAttribute("game", new Game());
        }
        return "gameForm";
    }

    @PostMapping(value = "/game")
    public String submitPlayerMove(Game game) {
        gameEngine.makeMove(game);
        return "gameForm";
    }
}
