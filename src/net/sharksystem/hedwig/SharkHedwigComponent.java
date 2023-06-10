package net.sharksystem.hedwig;

import net.sharksystem.ASAPFormats;
import net.sharksystem.SharkComponent;
import net.sharksystem.SharkException;
import net.sharksystem.asap.ASAPEnvironmentChangesListener;

import java.io.IOException;

@ASAPFormats(formats = {SharkHedwigComponent.SHARK_HEDWIG_APP})
public interface SharkHedwigComponent extends SharkComponent, ASAPEnvironmentChangesListener {
    String SHARK_HEDWIG_APP = "shark/hedwig";

    String HEDWIG_MESSAGE_URI_A = "sn://hedwigMsgA";
    String HEDWIG_MESSAGE_URI_B = "sn://hedwigMsgB";


    // TODO add methods
    void example_SendTypeAMessage(byte[] something) throws IOException, SharkException;
}
