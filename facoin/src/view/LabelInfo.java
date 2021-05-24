package view;

import java.awt.Color;

/**
 * This class holds JLabel information for JList
 */
public class LabelInfo {
	public Color color; // color of label
	public Double percent; // percent increase/decrease
	public String coinName;// name of the coin
	public Double value; // value of the coin
	public String banknote;// name of the banknote

	public LabelInfo(Color color, Double percent, String name, Double value, String banknote) {
		this.color = color;
		this.percent = percent;
		this.coinName = name;
		this.value = value;
		this.banknote = banknote;
	}

	public String toString() {
		return this.coinName + "/" + this.banknote + " : " + this.value + " (" + this.percent + "%)";
	}
}