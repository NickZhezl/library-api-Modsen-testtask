package dev.earlspilner.books.feign.advice;

import feign.FeignException;
import feign.Response;

/**
 * @author Nikita Zhelezko
 */
public interface FeignExceptionHandler {
    FeignException handleException(Response response);
}
