package ua.kpi.skilift.transfer;


public class IncorrectRequestFormatException extends RuntimeException {

    public IncorrectRequestFormatException(String message) {
        super(message);
    }
}
