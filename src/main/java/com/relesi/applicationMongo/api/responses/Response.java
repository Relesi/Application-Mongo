package com.relesi.applicationMongo.api.responses;

import java.util.List;
import java.util.Optional;

public class Response<T> {

	private T data;
	private List<String> erros;
	
	public Response(T data) {
		this.data = data;
	}
	 public Response(List<String> erros) {
		 this.erros  = erros;
	 }

	public Response(Optional<T> cliente) {
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<String> getErros() {
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	 
	 
}
