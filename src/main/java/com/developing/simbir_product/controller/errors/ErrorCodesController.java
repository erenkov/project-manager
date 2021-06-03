package com.developing.simbir_product.controller.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@Controller
public class ErrorCodesController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ErrorCodesController.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            logger.error(messageSource.getMessage("errorCodesController.handleError.logger",
                    new Integer[]{statusCode}, Locale.getDefault()));
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-403";
            }

            if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error-403";
            }
        }
        return "redirect:/board";
    }
}
