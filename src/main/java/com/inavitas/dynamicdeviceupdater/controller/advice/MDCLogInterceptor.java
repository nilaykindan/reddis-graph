package com.inavitas.dynamicdeviceupdater.controller.advice;

import com.inavitas.dynamicdeviceupdater.constants.DriverConstants;
import com.inavitas.dynamicdeviceupdater.constants.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

@Component
public class MDCLogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(MDCLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map map = new TreeMap<>((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));

        String mdcDeviceIdentifierParam = System.getenv(EnvironmentVariables.MDC_DEVICE_PARAM.getName());

        String deviceSerialValue = (String) map.get(DriverConstants.PATH_PARAM_DEVICE_SERIAL_NAME);

        if(mdcDeviceIdentifierParam != null) {
            MDC.put(mdcDeviceIdentifierParam, deviceSerialValue);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {



    }
}
