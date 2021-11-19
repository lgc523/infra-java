package dev.spider.cli;

/**
 * @author lgc
 */
public class Errors {

    public String errors(Throwable throwable) {
        String message = throwable.getMessage();
        return message;
    }
}
