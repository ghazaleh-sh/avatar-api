package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

public final class DuplicateException extends BaseException {

    public DuplicateException(String code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }
}