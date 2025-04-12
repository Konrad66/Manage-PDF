package konrad.lubaski.manage.pdf;

import konrad.lubaski.manage.common.DisableSpringSecurity;

public class DependencyInjectionDemo {

    private DisableSpringSecurity disableSpringSecurity;

    public DependencyInjectionDemo(DisableSpringSecurity disableSpringSecurity) {
        this.disableSpringSecurity = disableSpringSecurity;
    }
}