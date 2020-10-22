package chats;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorBitacora_Client{
    static Logger getBitacora(){
        return Logger.getGlobal();
    }

    static Logger getBitacora(String paquete, String nombreArcBitacora, Level nivel){
        Logger bitacora;
        bitacora = Logger.getLogger(paquete);

        try{
            FileHandler handler = new FileHandler(nombreArcBitacora,true);
            bitacora.addHandler(handler);
        }
        catch(IOException e){
            bitacora = Logger.getGlobal();
            bitacora.severe("Error en la creación de la bitácora personalizada" + e.getMessage());
            return bitacora;
        }
        bitacora.setLevel(nivel);
        return bitacora;
    }
}