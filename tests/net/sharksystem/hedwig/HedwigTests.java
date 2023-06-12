package net.sharksystem.hedwig;

import net.sharksystem.SharkException;
import net.sharksystem.SharkPeer;
import net.sharksystem.SharkTestPeerFS;
import net.sharksystem.asap.ASAPSecurityException;
import net.sharksystem.asap.crypto.ASAPCryptoAlgorithms;
import net.sharksystem.asap.crypto.ASAPKeyStore;
import net.sharksystem.pki.SharkPKIComponent;
import net.sharksystem.pki.SharkPKIComponentFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static net.sharksystem.hedwig.TestConstants.*;

public class HedwigTests {
    public static final String SPECIFIC_ROOT_FOLDER = ROOT_DIRECTORY + "sharkHedwigTests/";
    public static final String ALICE_FOLDER = SPECIFIC_ROOT_FOLDER + ALICE_NAME;
    public static final String BOB_FOLDER = SPECIFIC_ROOT_FOLDER + BOB_NAME;

    private static int portnumber = 7000;
    private int getPortNumber() {
        portnumber++;
        return portnumber;
    }

    public SharkHedwigComponent setupComponent(SharkPeer sharkPeer) throws SharkException, ASAPSecurityException {
        // setup PKI first

        // create a component factory
        SharkPKIComponentFactory sharkPKIComponentFactory = new SharkPKIComponentFactory();

        // register this component with shark peer
        sharkPeer.addComponent(sharkPKIComponentFactory, SharkPKIComponent.class);

        // project "clean code" :) we only use interfaces - unfortunately casting is unavoidable
        SharkPKIComponent sharkPKI = (SharkPKIComponent) sharkPeer.getComponent(SharkPKIComponent.class);

        // setup hedwig
        SharkHedwigComponentFactory hedwigFactory = new SharkHedwigComponentFactory(sharkPKI);

        // register with peer
        sharkPeer.addComponent(hedwigFactory, SharkHedwigComponent.class);

        return (SharkHedwigComponent)sharkPeer.getComponent(SharkHedwigComponent.class);
    }

    @Test // should run
    public void setupTest1() throws SharkException, IOException, InterruptedException {
        // clean up data from previous tests
        SharkTestPeerFS.removeFolder(ROOT_DIRECTORY);

        /////////////////// setup alice
        SharkTestPeerFS aliceSharkPeer = new SharkTestPeerFS(ALICE_NAME, ALICE_FOLDER);

        SharkHedwigComponent aliceHedwig = this.setupComponent(aliceSharkPeer);

        aliceSharkPeer.start();

        /////////////////// setup bob - if required
        SharkTestPeerFS bobSharkPeer = new SharkTestPeerFS(BOB_NAME, BOB_FOLDER);

        SharkHedwigComponent bobHedwig = this.setupComponent(bobSharkPeer);

        bobSharkPeer.start();

        ///////////////////////////////// Encounter #1 Alice - Bob ////////////////////////////////////////////////////
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> start encounter Alice - Bob >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        aliceSharkPeer.getASAPTestPeerFS().startEncounter(this.getPortNumber(), bobSharkPeer.getASAPTestPeerFS());

        // give them moment to exchange data
        Thread.sleep(1000);

        // check for results
    }

    @Test // TODO - buggy
    public void how2Encrypt() throws SharkException {
        /////////////////// setup alice
        SharkTestPeerFS aliceSharkPeer = new SharkTestPeerFS(ALICE_NAME, ALICE_FOLDER);
        SharkHedwigComponent aliceHedwig = this.setupComponent(aliceSharkPeer);
        SharkPKIComponent alicePKI = aliceHedwig.getSharkPKI();
        ASAPKeyStore asapKeyStore = alicePKI.getASAPKeyStore();

        // example
        byte[] encrypted4Bob =
                ASAPCryptoAlgorithms.produceEncryptedMessagePackage(
                        "data2Encrypt".getBytes(), "Bob", asapKeyStore);

    }

}
