package ir.co.sadad.avatarapi.common.exceptions;


import ir.co.sadad.avatarapi.common.Empty;
import ir.co.sadad.avatarapi.common.exceptions.model.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * base of exception
 *
 * @author a.nadi
 */
@Getter
public abstract class BaseException extends RuntimeException {

    protected String code;
    protected HttpStatus httpStatusCode;
    protected String extraData;
    private Map<String, Object> messageArgs;
    private ApiError apiError;

    public BaseException() {
        httpStatusCode = HttpStatus.BAD_REQUEST;
    }

    protected BaseException(Throwable cause) {
        super(cause);
        httpStatusCode = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String message) {
        super(message);
        httpStatusCode = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        httpStatusCode = HttpStatus.BAD_REQUEST;
    }


    public Object getMessageArg(String key) {
        if (Empty.isEmpty(messageArgs)) {
            return "";
        } else {
            return messageArgs.get(key);
        }
    }

    public void addMessageArg(String messageArg, Object messageVal) {
        if (Empty.isEmpty(this.messageArgs)) {
            this.messageArgs = new HashMap<>();
        }
        this.messageArgs.put(messageArg, messageVal);
    }

    public void addMessageExceptions(final List<BaseException> exceptions) {
        if (Empty.isEmpty(this.messageArgs)) {
            this.messageArgs = new HashMap<>();
        }
        this.messageArgs.put("exceptions", exceptions);
    }

    public Map<String, Object> getMessageArgs() {
        if (Empty.isNotEmpty(messageArgs)) {
            return messageArgs;
        }
        return null;
    }

    public abstract String getErrorCode();
    public String getExtraData() {
        return this.extraData;
    }


}