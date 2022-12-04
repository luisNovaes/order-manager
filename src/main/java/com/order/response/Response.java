package com.order.response;



import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.order.request.Request;
import com.order.service.dto.ControllerDto;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
		
@Service
@Slf4j
public class Response {
	
	private final JavaMailSender javaMailSender;

    public Response(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @SuppressWarnings("deprecation")
	public void enviarEmail(Request request, ControllerDto orderDto) {
    	
    	try {
    		
    		String titulo = "ORDER MANAGER";
        	String para = orderDto.getUser().getEmail();
        	String conteudo = orderDto.getId() + " , "   + 
        					  orderDto.getCreationDate().toLocaleString() + " , "   + 
        					  request.getUser() +  " , "  +  
        					  request.getItem() +  " , "  + 
        					  request.getQuantity();
        	
            //og.info("Enviando email simples");
            var mensagem = new SimpleMailMessage();
            mensagem.setTo(para);

            mensagem.setSubject(titulo);
            mensagem.setText(conteudo);
            javaMailSender.send(mensagem);
            //log.info("Email enviado com sucesso!");
            System.out.println("Email enviado com sucesso!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

    public void enviarEmailComAnexo(String para, String titulo, String conteudo, String arquivo) throws MessagingException {
        //log.info("Enviando email com anexo");
        var mensagem = javaMailSender.createMimeMessage();
        System.out.println("Enviando email com anexo!");
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(conteudo, true);

        helper.addAttachment("image1.jpeg", new ClassPathResource(arquivo));

        javaMailSender.send(mensagem);
       // log.info("Email com anexo enviado com sucesso.");
        System.out.println("Email com anexo enviado com sucess!");
    }
}

