package it.uniroma3.siw.configuration;

import jakarta.servlet.http.HttpServletRequest;

public class Utilita {

    public static String getSiteUrl(HttpServletRequest request) {
        String UrlSito = request.getRequestURL().toString();
        return UrlSito.replace(request.getServletPath(), "");
    }
}