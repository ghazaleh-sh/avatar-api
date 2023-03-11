package ir.co.sadad.avatarapi.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * exception for token
 * <pre>
 *     when token is null or not in right pattern (brear) this exception will throws
 * </pre>
 */
public final class TokenException extends BaseException {

    public TokenException() {
        this.code = "AA.H.V.001";
        this.httpStatusCode = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }
}
