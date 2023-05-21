package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

public class ProfileServiceException extends BaseException {

    public ProfileServiceException() {
        this.code = "AA.PROFILE.SERVICE";
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
