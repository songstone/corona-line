package com.songstone.coronaline.error;

import com.songstone.coronaline.Exception.GeneralException;
import com.songstone.coronaline.constant.ErrorCode;
import com.songstone.coronaline.dto.APIErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice(annotations = RestController.class) // 레스트 컨트롤러의 동작을 감시
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode.getCode(), errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e){
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ?
//                HttpStatus.BAD_REQUEST :
//                HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage(e)
//                ));
//    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode.getCode(), errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> exception(Exception e){
//        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false, errorCode, errorCode.getMessage(e)
//                ));
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorCode errorCode = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST :
                ErrorCode.SPRING_INTERNAL_ERROR;

        return super.handleExceptionInternal(
                ex,
                APIErrorResponse.of(false,errorCode.getCode(), errorCode.getMessage(ex)),
                headers,
                status,
                request
        );
    }
}
