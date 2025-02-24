package konrad.lubaski.manage.pdf;

import konrad.lubaski.manage.common.DisableSpringSecurity;
import org.springframework.stereotype.Component;

@Component
public class DependencyInjectionDemo {


    private DisableSpringSecurity disableSpringSecurity;

    public DependencyInjectionDemo(DisableSpringSecurity disableSpringSecurity) {
        this.disableSpringSecurity = disableSpringSecurity;

        System.out.println("Created pdf service");
    }
}
