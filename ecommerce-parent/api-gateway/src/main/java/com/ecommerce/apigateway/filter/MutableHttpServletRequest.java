package com.ecommerce.apigateway.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.*;

public class MutableHttpServletRequest extends HttpServletRequestWrapper {

    private final Map<String, String> customHeaders = new HashMap<>();

    public MutableHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    public void putHeader(String name, String value) {
        customHeaders.put(name.toLowerCase(), value);
    }

    public void removeHeader(String name) {
        // Overwrite with null signals "suppressed"
        customHeaders.put(name.toLowerCase(), null);
    }

    @Override
    public String getHeader(String name) {
        String key = name.toLowerCase();
        if (customHeaders.containsKey(key)) {
            return customHeaders.get(key); // null = removed
        }
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        String key = name.toLowerCase();
        if (customHeaders.containsKey(key)) {
            String val = customHeaders.get(key);
            if (val == null) return Collections.emptyEnumeration();
            return Collections.enumeration(List.of(val));
        }
        return super.getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> names = new HashSet<>(Collections.list(super.getHeaderNames()));
        // Add custom headers, remove suppressed ones
        customHeaders.forEach((k, v) -> {
            if (v != null) names.add(k);
            else names.remove(k);
        });
        return Collections.enumeration(names);
    }
}