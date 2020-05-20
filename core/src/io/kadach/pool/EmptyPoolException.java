package io.kadach.pool;

public class EmptyPoolException extends RuntimeException {

    public EmptyPoolException(String message) {
        super(message);
    }
}
