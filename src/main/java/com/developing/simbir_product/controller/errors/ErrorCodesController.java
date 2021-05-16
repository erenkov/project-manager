package com.developing.simbir_product.controller.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorCodesController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorCodesController.class);

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            logger.error("{} has been thrown!", statusCode);
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()){
                return "error-403";
            }

            if (statusCode == HttpStatus.UNAUTHORIZED.value()){
                return "error-403";
            }
        }
        return "redirect:/board";
    }
}
