package model;

import java.util.Date;

import enums.CandleStatus;

public class CandleParams{

	public Date candleDate, nowDate;
	public String coinName, high,low,open,close,volume;
	public CandleStatus status;

	public CandleParams(String coinName, Date date, Date nowDate, String high, String low, String open, String close, String volume,CandleStatus status){
		
	}	
}