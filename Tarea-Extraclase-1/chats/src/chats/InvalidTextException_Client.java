package chats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvalidTextException_Client extends Exception {
    static Logger bitacora =
            GestorBitacora_Client.getBitacora("chats.InvalidTextException_Client","bitacora_client.txt", Level.FINE);
    public InvalidTextException_Client(String message){
        bitacora.severe(message);
    }
}
