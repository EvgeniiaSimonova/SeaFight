package ru.testhf.srv3.h37945.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.domain.Game;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.lists.CellsList;
import ru.testhf.srv3.h37945.domain.lists.UserList;
import ru.testhf.srv3.h37945.forms.ShotForm;
import ru.testhf.srv3.h37945.forms.TableForm;
import ru.testhf.srv3.h37945.service.dao.FieldService;
import ru.testhf.srv3.h37945.service.dao.GameService;
import ru.testhf.srv3.h37945.service.dao.RequestService;
import ru.testhf.srv3.h37945.service.dao.UserService;

import java.util.List;

@Controller
@RequestMapping("/games/{idGame}")
public class ShotController {

    @Autowired
    private GameService gameService;

    @Autowired
    private FieldService fieldService;

    @ModelAttribute(value = "myField")
    public CellsList getMyField(@PathVariable int idGame) {
        CellsList cellsList = new CellsList();
        Field field;
        Game game = gameService.getGameById(idGame);
        if (game.getFirstLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            field = fieldService.getFieldById(game.getIdFirstField());
        } else {
            field = fieldService.getFieldById(game.getIdSecondField());
        }
        cellsList.setMyCells(field.getShips(), field.getShots());
        return cellsList;
    }

    @ModelAttribute(value = "opponentField")
    public CellsList getOpponentField(@PathVariable int idGame) {
        CellsList cellsList = new CellsList();
        Field field;
        Game game = gameService.getGameById(idGame);
        if (game.getFirstLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            field = fieldService.getFieldById(game.getIdSecondField());
        } else {
            field = fieldService.getFieldById(game.getIdFirstField());
        }
        cellsList.setOpponentCells(field.getShips(), field.getShots());
        return cellsList;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFields(@PathVariable int idGame, ModelMap model) {
        Game game = gameService.getGameById(idGame);
        if ( !game.isCompleted() &&
             ((game.getFirstLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName()) &&
                game.getMove() == 1) ||
             (game.getSecondLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName()) &&
                game.getMove() == 2))) {
            model.put("idGame", idGame);
            ShotForm shotForm = new ShotForm();
            model.put("shotForm", shotForm);
            return "gamePages/fieldsForGame/shot";
        } else {
            model.put("move", 0);
            return "gamePages/fieldsForGame/game";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String checkShot(@PathVariable int idGame, ShotForm shotForm, ModelMap model) {
        model.put("idGame", idGame);
        int letter = Integer.parseInt(shotForm.getLetter());
        int figure = Integer.parseInt(shotForm.getFigure());
        int cellNumber = figure*10+letter;

         // opponent field
        Field field;
        Game game = gameService.getGameById(idGame);
        if (game.getFirstLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            field = fieldService.getFieldById(game.getIdSecondField());
        } else {
            field = fieldService.getFieldById(game.getIdFirstField());
        }
        //shot
        String shot = field.getShots();
        String[] shots = shot.split(",");
        //String ship
        String ship = field.getShips();
        String[] ships = ship.split(",");

        // check
        if (!shot.equals("")) {
            for (int i = 0; i < shots.length; i++) {
                if (Integer.parseInt(shots[i]) == cellNumber) {
                    model.put("message", "You are wrong. You have shooted this cell!");
                    return "gamePages/fieldsForGame/shotResult";
                }
            }
        }
        fieldService.addShot(field.getId(), cellNumber);
        field = fieldService.getFieldById(field.getId());
        if (!ship.equals("")) {
            for (int i = 0; i < ships.length; i++) {
                if (Integer.parseInt(ships[i]) == cellNumber) {
                    if (field.isKilled()) {
                        gameService.setWinner(idGame, SecurityContextHolder.getContext().getAuthentication().getName());
                        model.put("message", "Congratulations! You are winner");
                        gameService.changeMove(idGame);
                    } else {
                        model.put("message", "Congratulations!");
                    }
                    return "gamePages/fieldsForGame/shotResult";
                }
            }
        }
        model.put("message", "Sorry, you missed");
        gameService.changeMove(idGame);
        return "gamePages/fieldsForGame/shotResult";
    }
}
