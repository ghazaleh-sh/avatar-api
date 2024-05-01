package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

public final class FileException extends BaseException{


    public FileException(String message) {
        this.code = "FILE.general.exception";
        this.extraData = message;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
