package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

public final class NotFoundException extends BaseException {

    public NotFoundException(String code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }
}
