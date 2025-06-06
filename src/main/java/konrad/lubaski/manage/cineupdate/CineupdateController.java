package konrad.lubaski.manage.cineupdate;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cineupdate")
public class CineupdateController {

    CineupdateService cineupdateService;

    public CineupdateController(CineupdateService cineupdateService) {
        this.cineupdateService = cineupdateService;
    }

    @PostMapping("/{cineupdateId}")
    public void sendCineupdate(@PathVariable int cineupdateId, String accountEmail) {
        cineupdateService.sendCineupdates(cineupdateId, accountEmail);
    }
}