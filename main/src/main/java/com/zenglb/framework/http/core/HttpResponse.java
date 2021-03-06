package com.zenglb.framework.http.core;
/**
 * 这个类和具体的业务api 结构有关，本Demo的API 结构大致如下：
 {
  "code": 0,
  "error": "",
  "result":{
   }
 }
 * Created by anylife.zlb@gmail.com on 2016/7/11.
 */
public class HttpResponse<T> {
	private int code;
	private String error;
	private T result;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "httpResponse{" +
				"code=" + code +
				", error='" + error + '\'' +
				", result=" + result +
				'}';
	}
}
