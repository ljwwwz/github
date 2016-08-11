package com.coolshow.jeesite.common.tool;

/**
 * @Description 返回前台json信息
 * ClassName: FormBean.java
 * @author LJW
 * @date 下午2:45:31
 */
public class FormBean {

	public static enum Result{
		SUCCESS(1),FAILURE(0);
		private int value;		
		private Result(int status){
			this.value = value;
		}
		public void getValue(int value) {
			this.value = value;
		}
	}
	
	private Result status;
	private String msg;
	
	public Result getStatus() {
		return status;
	}
	public void setStatus(Result status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
