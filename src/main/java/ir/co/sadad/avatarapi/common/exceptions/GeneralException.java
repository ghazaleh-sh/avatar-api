package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * general exception
 *
 * @author a.nadi
 */
public final class GeneralException extends BaseException {

    public GeneralException(String code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public GeneralException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatusCode = httpStatus;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }
}
