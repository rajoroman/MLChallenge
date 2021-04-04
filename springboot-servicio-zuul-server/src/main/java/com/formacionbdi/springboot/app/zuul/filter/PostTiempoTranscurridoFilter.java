package com.formacionbdi.springboot.app.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		final  Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
		
		log.info("Entrando a Post");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		log.info(String.format("Tiempo Trnascurrido en segundos %s seg.", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo Trnascurrido en milisegundos %s ms.", tiempoTranscurrido));
		
		
		return null;
	}

	@Override
	public String filterType() {
		
		return "post";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}
	

}
