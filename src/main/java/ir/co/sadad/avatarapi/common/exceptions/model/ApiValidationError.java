package ir.co.sadad.avatarapi.common.exceptions.model;

import lombok.Builder;
import lombok.Getter;

/**
 * body of exception for validation error
 */
@Getter
public class ApiValidationError implements ApiSubError {

    /**
     * obj of error
     */
    private String object;

    /**
     * violated field
     */
    private String field;

    /**
     * exception code
     */
    private String code;

    /**
     * message
     */
    private String message;

    /**
     * translated message
     */
    private String localizedMessage;

    @Builder
    public ApiValidationError(String object,
                              String field,
                              String code,
                              String message,
                              String localizedMessage) {
        this.object = object;
        this.field = field;
        this.code = code;
        this.message = message;
        this.localizedMessage = localizedMessage;
    }
}
