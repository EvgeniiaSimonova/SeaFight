package ru.testhf.srv3.h37945.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.domain.Game;
import ru.testhf.srv3.h37945.domain.Request;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.lists.GameList;
import ru.testhf.srv3.h37945.domain.lists.RequestList;
import ru.testhf.srv3.h37945.domain.lists.UserList;
import ru.testhf.srv3.h37945.forms.IdForm;
import ru.testhf.srv3.h37945.forms.LoginForm;
import ru.testhf.srv3.h37945.service.dao.FieldService;
import ru.testhf.srv3.h37945.service.dao.GameService;
import ru.testhf.srv3.h37945.service.dao.RequestService;
import ru.testhf.srv3.h37945.service.dao.UserService;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private FieldService fieldService;

    @ModelAttribute
    public RequestList getRequests() {
        List<Request> requests =
                requestService.requestsForUser(SecurityContextHolder.getContext().getAuthentication().getName());
        RequestList requestList = new RequestList(requests);
        return requestList;
    }

    @ModelAttribute(value = "completedGameList")
    public GameList getCompletedGames() {
        List<Game> games = gameService.
                completedGameListForUser(SecurityContextHolder.getContext().getAuthentication().getName());
        GameList gameList = new GameList(games);
        return gameList;
    }

    @ModelAttribute(value = "continuedGameList")
    public GameList getContinuedGames() {
        List<Game> games = gameService.
                gameListForUser(SecurityContextHolder.getContext().getAuthentication().getName());
        GameList gameList = new GameList(games);
        return gameList;
    }

    @ModelAttribute
    public UserList getUsers() {
        List<User> users = userService.userList();
        UserList userList = new UserList(users);
        return userList;
    }

    @RequestMapping(value = "/game/requests/send/", method = RequestMethod.GET)
    public String getSendRequestPage(ModelMap model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "gamePages/sendRequest";
    }

    @RequestMapping(value = "/game/requests/send/", method = RequestMethod.POST)
    public String checkSendRequestPage(LoginForm loginForm, ModelMap model) {
        String login = loginForm.getLogin();
        if (!login.equals("")) {
            try {
                User user = userService.getUserByLogin(login);
                Request request =
                        new Request(SecurityContextHolder.getContext().getAuthentication().getName(), login, 0, -1);
                requestService.addRequest(request);

                return "gamePages/successfulSend";
            } catch (Exception e){
                model.put("Error", "Wrong login");
                loginForm.setLogin("");
            }
        }
        return "gamePages/sendRequest";
    }

    @RequestMapping(value = "/game/requests/response/", method = RequestMethod.GET)
    public String getResponseRequestPage(ModelMap model) {
        IdForm idForm = new IdForm();
        model.put("idForm", idForm);
        return "gamePages/responseRequest";
    }

    @RequestMapping(value = "/game/requests/response/", method = RequestMethod.POST)
    public String checkResponseRequestPage(IdForm idForm, ModelMap model){
        if (!idForm.getId().equals("")) {
            int id = Integer.parseInt(idForm.getId());
            String state = idForm.getState();
            try {
                if (!getRequests().isIdIncludeList(id)) {
                    throw new Exception();
                }
                Request request = requestService.getRequestById(id);
                if (state.equals("agree")) {
                    Field field1 = new Field("", "", false);
                    Field field2 = new Field("", "", false);
                    fieldService.addField(field1);
                    fieldService.addField(field2);

                    Game game = new Game(request.getFirstLogin(), request.getSecondLogin(),
                            false,"", field1.getId(), field2.getId());
                    gameService.addGame(game);
                    requestService.updateRequest(id, 1, game.getId());
                }
                if (state.equals("refuse")) {
                    requestService.updateRequest(id, -1, -1);
                }
                return "gamePages/successfulResponse";
            } catch (Exception e) {
            }
        }
        model.put("Error", "Wrong id");
        idForm.setId("");
        return "gamePages/responseRequest";
    }
}
