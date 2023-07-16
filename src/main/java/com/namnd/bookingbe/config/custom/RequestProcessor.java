//package com.namnd.bookingbe.config.custom;
//
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.namnd.bookingbe.dto.RequestApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.util.HashMap;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class RequestProcessor {
//    private final Map<String, RequestHandler> requestHandlerMap;
//
//    @Autowired
//    public RequestProcessor(List<RequestHandler> requestHandlers) {
//        requestHandlerMap = new HashMap<>();
//        for (RequestHandler handler : requestHandlers) {
//            Class<? extends RequestHandler> handlerClass = handler.getClass();
//            for (Method method : handlerClass.getDeclaredMethods()) {
//                RequestType methodAnnotation = method.getAnnotation(RequestType.class);
//                if (methodAnnotation != null) {
//                    String requestType = methodAnnotation.value();
//                    requestHandlerMap.put(requestType, handler);
//                }
//            }
//        }
//    }
//
//    public Object processRequest(RequestApi<Object> request) {
//        String requestType = request.getRequestName();
//        RequestHandler requestHandler = requestHandlerMap.get(requestType);
//        if (requestHandler != null) {
//            Method[] methods = requestHandler.getClass().getDeclaredMethods();
//            for (Method method : methods) {
//                RequestType methodAnnotation = method.getAnnotation(RequestType.class);
//                if (methodAnnotation != null && methodAnnotation.value().equals(requestType)) {
//                    try {
//
//                        Object requestData = request.getBody();
//                        Type methodParameterType = method.getGenericParameterTypes()[0];
//                        Object convertedRequest = convertRequest(requestData, methodParameterType);
//                        return method.invoke(requestHandler, convertedRequest);
//                    } catch (Exception e) {
//                        // Handle any exceptions that may occur during method invocation
//                        e.printStackTrace();
//                        // Return an appropriate response or throw an exception
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    private <T> T convertRequest(Object requestData, Type targetType) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        JavaType javaType = objectMapper.getTypeFactory().constructType(targetType);
//        return objectMapper.convertValue(requestData, javaType);
//    }
//
//
//}
