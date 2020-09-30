package com.jsp.i18n.rest;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class UrlLocaleResolver implements LocaleResolver {

	private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// ==> /SomeContextPath/en/...
		// ==> /SomeContextPath/fr/...
		// ==> /SomeContextPath/WEB-INF/pages/...
		String uri = request.getRequestURI();

		System.out.println("in UrlLocaleResolver class URI=" + uri);

		String prefixEn = request.getServletContext().getContextPath() + "/en/";
		String prefixFr = request.getServletContext().getContextPath() + "/fr/";
		String prefixVi = request.getServletContext().getContextPath() + "/vi/";
		String prefixHi = request.getServletContext().getContextPath() + "/hi/";
		String prefixOd = request.getServletContext().getContextPath() + "/od/";
		

		Locale locale = null;

		// English
		if (uri.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		}
		// French
		else if (uri.startsWith(prefixFr)) {
			locale = Locale.FRANCE;
		}
		// Vietnamese
		else if (uri.startsWith(prefixVi)) {
			locale = new Locale("vi", "vi");
		}
		else if (uri.startsWith(prefixHi)) {
			locale = new Locale("hi","hi");
		}
		else if (uri.startsWith(prefixOd)) {
			locale = new Locale("od","od");
		}
		
		if (locale != null) {
			request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		}
		if (locale == null) {
			locale = (Locale) request.getSession().getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
			if (locale == null) {
				locale = Locale.ENGLISH;
			}
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// Nothing
	}

}