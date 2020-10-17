package chats;

public class InvalidTextException extends Exception {

    public InvalidTextException() {
        super ("Invalid text was tried to be sent. Enter characters in the message to send it.");
    }
    
}
