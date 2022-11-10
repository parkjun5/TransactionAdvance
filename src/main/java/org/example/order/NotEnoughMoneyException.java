package org.example.order;

//체크예외 -> 비지니스 로직과 관련 있다. 그래서 롤백을 안하고 커밋한다
public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
