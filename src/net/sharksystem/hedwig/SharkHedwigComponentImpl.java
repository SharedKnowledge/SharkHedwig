package net.sharksystem.hedwig;

import net.sharksystem.AbstractSharkComponent;
import net.sharksystem.SharkException;
import net.sharksystem.asap.ASAPPeer;
import net.sharksystem.pki.SharkPKIComponent;

import java.io.IOException;
import java.util.Set;

public class SharkHedwigComponentImpl extends AbstractSharkComponent
        implements SharkHedwigComponent {

    private final SharkPKIComponent sharkPKI;
    private ASAPPeer asapPeer;

    public SharkHedwigComponentImpl(SharkPKIComponent sharkPKI) {
        this.sharkPKI = sharkPKI;
    }

    public SharkPKIComponent getSharkPKI() {
        return this.sharkPKI;
    }

    @Override
    public void onStart(ASAPPeer asapPeer) throws SharkException {
        // remember peer
        this.asapPeer = asapPeer;

        // add listener - this is an example / template
        SharkHedwigMessageListener hedwigMessageListener = new SharkHedwigMessageListener(this);

        // tell peer to notify about received messages
        this.asapPeer.addASAPMessageReceivedListener(HEDWIG_MESSAGE_URI_A, hedwigMessageListener);

        // Es gibt offenbar mehrere Messagetypen

        /* Es gibt prinzipiell zwei Varianten wie man das implementiert:
        a) Es gibt eine Listenerklasse, die auf alle Arten von Nachrichten reagiert.
        b) Es gibt für jede Messageart eine eigene Klasse.
        Beides ist völlig in Ordnung aus meiner Sicht
         */

    }

    @Override
    public void example_SendTypeAMessage(byte[] something) throws IOException, SharkException {
        // send message and tag it with app description, message type followed by message content
        this.asapPeer.sendASAPMessage(SHARK_HEDWIG_APP, HEDWIG_MESSAGE_URI_A, something);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        environment changed listener                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onlinePeersChanged(Set<CharSequence> peerIDList) {
        // something changed - here comes a fresh peerIDs list
        // TODO ? send an initial message
    }
}
