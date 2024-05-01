package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * exception for forbidden operation
 */
public final class ForbiddenException  extends BaseException {

    public ForbiddenException(String code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.FORBIDDEN;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }
}
