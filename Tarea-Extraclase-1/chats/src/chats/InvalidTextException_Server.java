package chats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvalidTextException_Server extends Exception {
    static Logger bitacora =
            GestorBitacora_Server.getBitacora("chats.InvalidTextException_Server","bitacora_server.txt", Level.FINE);
    public InvalidTextException_Server(String message){
        bitacora.severe(message);
    }
}
