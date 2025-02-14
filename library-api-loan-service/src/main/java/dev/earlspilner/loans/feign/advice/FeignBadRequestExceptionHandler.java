package dev.earlspilner.loans.feign.advice;

import feign.FeignException;
import feign.Response;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Zhelezko
 */
@Component("400")
public class FeignBadRequestExceptionHandler implements FeignExceptionHandler {

    @Override
    public FeignException handleException(Response response) {
        return FeignException.errorStatus(response.request().httpMethod().name(), response);
    }

}
