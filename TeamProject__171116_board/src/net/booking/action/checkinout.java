package net.booking.action;

public class checkinout {
	private String checkin;
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	private String checkout;
	public checkinout(String in,String out){
		checkin = in;
		checkout = out;
	}
}
