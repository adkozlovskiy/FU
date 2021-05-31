package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.Menu;
import kozlovskiy.prod.repo.BarRepo;
import kozlovskiy.prod.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/menu")
public class BarController {

    @Autowired
    private BarService service;

    /**
     * @return all {@link Menu} positions from {@link BarRepo}
     */
    @GetMapping
    public ResponseEntity<List<Menu>> getMenu() {
        List<Menu> body = service.getMenu();
        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
