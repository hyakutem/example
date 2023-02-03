package com.example.example.filter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MDCFilter extends OncePerRequestFilter {

    private static final String X_REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        putMDC(request, response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            clearMDC();
        }
        
    }
    
	protected void putMDC(HttpServletRequest request, HttpServletResponse response) {
		String requestId = request.getHeader(X_REQUEST_ID_HEADER);
		if (Objects.isNull(requestId)) {
			// もしHeaderが渡ってきていない場合は、自ら生成する
			requestId = UUID.randomUUID().toString().replace("-", "");
		}
		MDC.put(X_REQUEST_ID_HEADER, requestId);
	}

	protected void clearMDC() {
		MDC.remove(X_REQUEST_ID_HEADER);
	}

}
