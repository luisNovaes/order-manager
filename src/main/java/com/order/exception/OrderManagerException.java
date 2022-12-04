package com.order.exception;

public class OrderManagerException {
	
	private Long id;
	private String message;
	private String tipo;
	private String Detalhe;
	
	public OrderManagerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDetalhe() {
		return Detalhe;
	}

	public void setDetalhe(String detalhe) {
		Detalhe = detalhe;
	}
	
	
	
	

}
