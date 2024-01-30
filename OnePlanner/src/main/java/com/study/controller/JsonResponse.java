package com.study.controller;

import com.study.controller.JsonResponse;

public class JsonResponse<T> {
	private final T data;
	
	public JsonResponse(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
		

}
