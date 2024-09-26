package dev.earlspilner.library.feign.advice;

import feign.FeignException;
import feign.Response;

/**
 * @author Nikita Zhelezko
 */
public interface FeignExceptionHandler {
    FeignException handleException(Response response);
}
