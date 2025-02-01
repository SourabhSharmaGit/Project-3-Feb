package in.co.rays.project_3.dto;

import java.util.Date;

public class DemoDTO extends BaseDTO{

	private String name;
	private String preLoad;
	private Date demoDate;
	private int quantity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreLoad() {
		return preLoad;
	}
	public void setPreLoad(String preLoad) {
		this.preLoad = preLoad;
	}
	public Date getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(Date demoDate) {
		this.demoDate = demoDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
