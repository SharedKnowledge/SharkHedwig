package net.sharksystem.hedwig;

import net.sharksystem.asap.ASAPHop;
import net.sharksystem.asap.ASAPMessageReceivedListener;
import net.sharksystem.asap.ASAPMessages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SharkHedwigMessageListener implements ASAPMessageReceivedListener {
    private final SharkHedwigComponent hedwig;

    public SharkHedwigMessageListener(SharkHedwigComponent sharkHedwigComponent) {
        this.hedwig = sharkHedwigComponent;
    }

    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages, String s, List<ASAPHop> list) throws IOException {
        // messages contains a PDU
        switch(String.valueOf(asapMessages.getFormat())) {
            case SharkHedwigComponent.HEDWIG_MESSAGE_URI_A:
                // TODO - messages of type A received
                this.handleMessageA(asapMessages.getMessages());
            case SharkHedwigComponent.HEDWIG_MESSAGE_URI_B:
                // TODO - messages of type B received
        }
    }

    private void handleMessageA(Iterator<byte[]> messages) {
        // TODO
    }
}
