package com.flexicore.order.request;

public class CreateSupplyTime {

	private String name;
	private Integer dayOfTheWeek;
	private Integer hour;
	private Integer minute;
	private Integer second;

	public String getName() {
		return name;
	}

	public <T extends CreateSupplyTime> T setName(String name) {
		this.name = name;
		return (T) this;
	}

	public Integer getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public <T extends CreateSupplyTime> T setDayOfTheWeek(Integer dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
		return (T) this;
	}

	public Integer getHour() {
		return hour;
	}

	public <T extends CreateSupplyTime> T setHour(Integer hour) {
		this.hour = hour;
		return (T) this;
	}

	public Integer getMinute() {
		return minute;
	}

	public <T extends CreateSupplyTime> T setMinute(Integer minute) {
		this.minute = minute;
		return (T) this;
	}

	public Integer getSecond() {
		return second;
	}

	public <T extends CreateSupplyTime> T setSecond(Integer second) {
		this.second = second;
		return (T) this;
	}
}
