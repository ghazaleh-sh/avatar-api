package ir.co.sadad.avatarapi.common.exceptions.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.ConstraintViolation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.*;

/**
 * main body of exception
 *
 * @author a.nadi
 */
@Getter
public class ApiError {

    /**
     * code of exception - 400 ~ 500
     */
    private final HttpStatus status;

    /**
     * when error occurred
     */
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.NUMBER, timezone = "Asia/Tehran")
    private final Long timestamp;

    /**
     * code of exception
     */
    private final String code;

    /**
     * message of exception
     */
    private final String message;

    /**
     * translated message
     */
    private final String localizedMessage;

    /**
     * list of sub errors
     */
    private List<ApiSubError> subErrors;

    /**
     * meta data
     */
    @Setter
    private Map<String, Object> meta;

    /**
     * extra date
     */
    private final String extraData;

    @Builder
    public ApiError(HttpStatus status,
                    Long timestamp,
                    String code,
                    String message,
                    String localizedMessage,
                    List<ApiSubError> subErrors,
                    Map<String, Object> meta,
                    String extraData) {

        this.subErrors = new ArrayList<>();

        this.status = status;
        this.timestamp = timestamp == null ? new Date().getTime() : timestamp;
        this.code = code;
        this.message = message;
        this.localizedMessage = localizedMessage;
        this.subErrors = subErrors;
        this.meta = meta;
        this.extraData = extraData;


    }

    @JsonAnyGetter
    public Map<String, Object> getMeta() {
        if (meta == null) {
            meta = new HashMap<>();
        }

        return meta;
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    public void addValidationError(String field, String object,
                                   String code, String message, String localizedMessage) {
        addSubError(ApiValidationError.builder()
                .field(field)
                .localizedMessage(localizedMessage)
                .message(message)
                .code(code)
                .object(object)
                .build());
    }

    public void addValidationError(String code,
                                   String message, String localizedMessage) {
        addSubError(ApiValidationError.builder()
                .code(code)
                .message(message)
                .localizedMessage(localizedMessage)
                .build());
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(null, cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue() == null ? "" : cv.getInvalidValue().toString(),
                cv.getMessage());
    }

    public void addValidationError(FieldError fieldError) {
        this.addValidationError("", fieldError.getObjectName(), fieldError.getField(),
                fieldError.getRejectedValue() == null ? ""
                        : fieldError.getRejectedValue().toString(),
                fieldError.getDefaultMessage());
    }


}