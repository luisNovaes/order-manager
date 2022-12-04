package com.order.response;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.order.request.Request;
import com.order.service.LoggerService;
import com.order.service.dto.ControllerDto;

import jakarta.mail.MessagingException;
		
@Service
public class Response {
	private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);
	private final JavaMailSender javaMailSender;
	
	@Autowired
	LoggerService loggerService;

    public Response(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @SuppressWarnings("deprecation")
	public boolean enviarEmail(Request request, ControllerDto orderDto) {
    	try {
    		String titulo = "ORDER MANAGER";
        	String para = orderDto.getUser().getEmail();
        	String conteudo = orderDto.getId() + " , "   + orderDto.getCreationDate().toLocaleString() + " , "   + request.getUser() +  " , "  +  
        					  request.getItem() +  " , "  + request.getQuantity();
            var mensagem = new SimpleMailMessage();
            mensagem.setTo(para);
            mensagem.setSubject(titulo);
            mensagem.setText(conteudo);
            javaMailSender.send(mensagem);
            return true;
		} catch (Exception e) {
			loggerService.printErros("Error system Email send" + e);
		}
		return false;	
    }

    public boolean enviarEmailComAnexo(String para, String titulo, String conteudo, String arquivo) throws MessagingException {
    	try {
	        var mensagem = javaMailSender.createMimeMessage();
	        var helper = new MimeMessageHelper(mensagem, true);
	        helper.setTo(para);
	        helper.setSubject(titulo);
	        helper.setText(conteudo, true);
	        helper.addAttachment("image1.jpeg", new ClassPathResource(arquivo));
	        javaMailSender.send(mensagem);
	        return true;	
		} catch (Exception e) {
			loggerService.printErros("Error system Email send" + e);
		}
		return false;
        
    }
}

