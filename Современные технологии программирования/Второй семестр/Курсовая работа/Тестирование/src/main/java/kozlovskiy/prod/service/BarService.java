package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Menu;
import kozlovskiy.prod.repo.BarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarService {

    @Autowired
    private BarRepo barRepo;

    public List<Menu> getMenu() {
        return barRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
}
