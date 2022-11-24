package cn.foofun.forge;

public class ForgeException extends RuntimeException {

    public ForgeException() {
    }

    public ForgeException(String message) {
        super(message);
    }

    public ForgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForgeException(Throwable cause) {
        super(cause);
    }

    public ForgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
