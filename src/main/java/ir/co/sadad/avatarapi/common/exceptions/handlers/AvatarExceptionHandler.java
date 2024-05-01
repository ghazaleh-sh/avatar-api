package ir.co.sadad.avatarapi.common.exceptions.handlers;

import io.minio.errors.ErrorResponseException;
import ir.co.sadad.avatarapi.common.exceptions.BaseException;
import ir.co.sadad.avatarapi.common.exceptions.model.ApiError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MissingRequestValueException;

import java.util.ArrayList;
import java.util.Locale;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AvatarExceptionHandler {
    private static final String ERROR_GENERAL_VALIDATION = "PPA.general.validation";
    private static final String ERROR_METHOD_ARGUMENT_NOT_VALID = "PPA.general.validator.EBP40000002";
    private static final String ERROR_CONSTRAINT_VIOLATION = "PPA.general.validator.EBP40000001";
    private static final String ERROR_INTERNAL_SERVER = "PPA.general.internal.server.exception";
    private static final String ERROR_MINIO_EXCEPTION = "FILE.general.exception";
    private static final String PHOTO_SIZE = "PHOTO.SIZE.EXCEED";
    private static final Locale LOCALE_EN = Locale.ENGLISH;
    protected static final Locale LOCALE_FA = new Locale("fa");
    private final MessageSource messageSource;


    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex) {
        log.error("General occurred.", ex.getCause());
        ApiError errorModel = this.getErrorModel(ex.getHttpStatusCode(), ex.getErrorCode());
        return ResponseEntity.status(errorModel.getStatus()).body(errorModel);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred.", ex);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, ERROR_GENERAL_VALIDATION);

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorModel.addValidationError(fieldError.getField(),
                    fieldError.getObjectName(),
                    ERROR_METHOD_ARGUMENT_NOT_VALID,
                    fieldError.getDefaultMessage(),
                    this.getMessage(ERROR_METHOD_ARGUMENT_NOT_VALID, LOCALE_FA));
        }

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<ApiError> handleBindException(BindException ex) {
        log.error("BindException occurred.", ex);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, ERROR_GENERAL_VALIDATION);

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorModel.addValidationError(fieldError.getField(),
                    fieldError.getObjectName(),
                    ERROR_METHOD_ARGUMENT_NOT_VALID,
                    fieldError.getDefaultMessage(),
                    this.getMessage(ERROR_METHOD_ARGUMENT_NOT_VALID, LOCALE_FA));
        }

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException occurred.", ex);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, ERROR_CONSTRAINT_VIOLATION);

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            errorModel.addValidationError(constraintViolation.getMessage(),
                    getMessage(constraintViolation.getMessage(), LOCALE_EN),
                    getMessage(constraintViolation.getMessage(), LOCALE_FA));
        }

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException occurred.", e);
        ApiError errorModel = this
                .getErrorModel(HttpStatus.BAD_REQUEST, ERROR_GENERAL_VALIDATION);

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiError> handleWebExchangeBindException(WebExchangeBindException e) {
        log.error("WebExchangeBindException occurred.", e);
        ApiError errorModel = this
                .getErrorModel(HttpStatus.BAD_REQUEST, ERROR_GENERAL_VALIDATION);

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorModel.addValidationError(fieldError.getField(),
                    fieldError.getObjectName(),
                    ERROR_METHOD_ARGUMENT_NOT_VALID,
                    fieldError.getDefaultMessage(),
                    this.getMessage(ERROR_METHOD_ARGUMENT_NOT_VALID, LOCALE_FA));
        }

        return ResponseEntity.badRequest().body(errorModel);
    }


    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<ApiError> handleMissingRequestValueException(MissingRequestValueException e) {
        log.error("MissingRequestValueException occurred.", e);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, ERROR_GENERAL_VALIDATION);

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatchException(TypeMismatchException e) {
        log.error("TypeMismatchException occurred.", e);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, ERROR_METHOD_ARGUMENT_NOT_VALID);

        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(DataBufferLimitException.class)
    public ResponseEntity<ApiError> handleDataBufferLimitException(DataBufferLimitException e) {
        log.error("DataBufferLimitException occurred.", e);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST, PHOTO_SIZE);

        return ResponseEntity.badRequest().body(errorModel);
    }


    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ApiError> handleErrorResponseException(ErrorResponseException e)
    {
        log.error("MinIO Error Response exception",e);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST,
                ERROR_MINIO_EXCEPTION, e.getMessage());

        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleExceptions(Exception e) {
        log.error("Exception occurred.", e);
        ApiError errorModel = this.getErrorModel(HttpStatus.BAD_REQUEST,
                ERROR_INTERNAL_SERVER, e.getMessage());

        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    private ApiError getErrorModel(HttpStatus httpStatusCode, String errorCode) {
        return ApiError.builder()
                .status(httpStatusCode)
                .message(this.getMessage(errorCode, LOCALE_EN))
                .localizedMessage(this.getMessage(errorCode, LOCALE_FA))
                .code(errorCode)
                .subErrors(new ArrayList<>())
                .build();
    }

    private ApiError getErrorModel(HttpStatus httpStatusCode, String errorCode, String extraData) {
        return ApiError.builder()
                .status(httpStatusCode)
                .message(this.getMessage(errorCode, LOCALE_EN))
                .localizedMessage(this.getMessage(errorCode, LOCALE_FA))
                .code(errorCode)
                .extraData(extraData)
                .subErrors(new ArrayList<>())
                .build();
    }

    /**
     * Localizes the message according to the selected language in config file.
     */
    private String getMessage(String message, Locale locale) {
        try {
            return messageSource.getMessage(message, new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            return message;
        }
    }


}