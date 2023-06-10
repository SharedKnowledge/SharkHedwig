package net.sharksystem.hedwig;

import net.sharksystem.SharkComponent;
import net.sharksystem.SharkComponentFactory;
import net.sharksystem.pki.SharkPKIComponent;

public class SharkHedwigComponentFactory implements SharkComponentFactory {

    private final SharkPKIComponent sharkPKI;
    private SharkHedwigComponentImpl instance;

    public SharkHedwigComponentFactory(SharkPKIComponent sharkPKI) {
        this.sharkPKI = sharkPKI;
    }
    @Override
    public SharkComponent getComponent() {
        if(this.instance == null) {
            this.instance = new SharkHedwigComponentImpl(this.sharkPKI);
        }

        return this.instance;
    }
}
