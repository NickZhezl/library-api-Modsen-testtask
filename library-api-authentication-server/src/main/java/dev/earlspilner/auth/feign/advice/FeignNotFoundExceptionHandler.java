package dev.earlspilner.auth.feign.advice;

import feign.FeignException;
import feign.Response;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Zhelezko
 */
@Component("404")
public class FeignNotFoundExceptionHandler implements FeignExceptionHandler {

    @Override
    public FeignException handleException(Response response) {
        return FeignException.errorStatus(response.request().httpMethod().name(), response);
    }

}
