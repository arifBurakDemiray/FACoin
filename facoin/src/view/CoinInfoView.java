package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import view.color.ColorPalette;
import view.list.List;

public class CoinInfoView extends AppView {

	private static final long serialVersionUID = -5200070696436660254L;
	private ChartPanel chartPanel;
	private ColorPalette palette;
	private JLabel coinValue;
	private JButton dayCandle;
	private JButton hourCandle;
	private JButton sell;
	private JButton buy;
	private JButton back;
	private JButton favorite;
	private JTextField buyQuantity;
	private JTextField sellQuantity;
	private JTextField buyPrice;
	private JTextField sellPrice;
	private JLabel infoLabel;
	private JLabel alert;

	public CoinInfoView() {
		setLayout(null);

		coinValue = new JLabel("Label");
		coinValue.setFont(new Font("Arial", Font.BOLD, 14));
		coinValue.setBounds(345, 363, 200, 50);
		add(coinValue);

		chartPanel = new ChartPanel(null);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBounds(100, 25, 600, 300);
		add(chartPanel);

		dayCandle = new JButton("DAY");
		dayCandle.setBounds(29, 302, 65, 23);
		dayCandle.setFont(new Font("Arial", Font.BOLD, 11));
		add(dayCandle);

		hourCandle = new JButton("HOUR");
		hourCandle.setBounds(29, 275, 65, 23);
		hourCandle.setFont(new Font("Arial", Font.BOLD, 11));
		add(hourCandle);

		favorite = new JButton("\u2605");
		favorite.setBounds(chartPanel.getX() + chartPanel.getWidth() + 10, chartPanel.getY(), 55, 40);
		favorite.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
		add(favorite);

		sell = new JButton("SELL");
		sell.setBounds(546, 441, 75, 25);
		sell.setFont(new Font("Arial", Font.BOLD, 14));
		add(sell);

		buy = new JButton("BUY");
		buy.setBounds(175, 441, 75, 25);
		buy.setFont(new Font("Arial", Font.BOLD, 14));
		add(buy);

		alert = new JLabel("");
		alert.setBounds(chartPanel.getX() + 25, coinValue.getY() + 110, 500, 50);
		alert.setFont(new Font("Arial", Font.BOLD, 14));
		alert.setVisible(false);
		add(alert);

		buyQuantity = new JTextField("Buy Quantity");
		buyQuantity.setBounds(chartPanel.getX() + 50, 349, 130, 35);
		buyQuantity.setFont(new Font("Arial", Font.BOLD, 14));
		add(buyQuantity);

		sellQuantity = new JTextField("Sell Quantity");
		sellQuantity.setBounds(chartPanel.getWidth() + chartPanel.getX() - 180, 349, 130, 35);
		sellQuantity.setFont(new Font("Arial", Font.BOLD, 14));
		add(sellQuantity);

		buyPrice = new JTextField("Buy Price");
		buyPrice.setBounds(150, 395, 130, 35);
		buyPrice.setFont(new Font("Arial", Font.BOLD, 14));
		add(buyPrice);

		sellPrice = new JTextField("Sell Price");
		sellPrice.setBounds(520, 395, 130, 35);
		sellPrice.setFont(new Font("Arial", Font.BOLD, 14));
		add(sellPrice);

		back = new JButton("Back");
		back.setBounds(14, 25, 76, 30);
		back.setFont(new Font("Arial", Font.BOLD, 14));
		add(back);

		infoLabel = new JLabel("<html>In every 10 seconds, value of coin is updated automatically."
				+ "<br>You can see day/hour candles using day/hour buttons."
				+ "<br>You can favor/unfavor coin using star button.<br>(yellow = favored, blue = unfavored).</html>");
		infoLabel.setBounds(225, 460, 600, 100);
		infoLabel.setForeground(Color.GRAY);
		add(infoLabel);

		AppWindow.FRAME.getContentPane().add(this);

	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public void addDayCandleButtonListener(ActionListener listener) {
		dayCandle.addActionListener(listener);
	}

	public void addHourCandleButtonListener(ActionListener listener) {
		hourCandle.addActionListener(listener);
	}

	public void addBuyButtonListener(ActionListener listener) {
		buy.addActionListener(listener);
	}

	public void addSellButtonListener(ActionListener listener) {
		sell.addActionListener(listener);
	}

	public void addFavoriteButtonListener(ActionListener listener) {
		favorite.addActionListener(listener);
	}

	/**
	 * This function sets chart of the charPanel
	 * 
	 * @param chart new chart for chart panel
	 */
	public void setChart(JFreeChart chart) {
		chart.setBackgroundPaint(getBackground());
		chart.clearSubtitles();
		chartPanel.setChart(chart);
		chart.getXYPlot().zoom(HEIGHT);
		chart.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
		updateUI();
	}

	/**
	 * This function returns but quantity and buy price when buy button pressed
	 * 
	 * @returns buy quantity and buy price
	 */
	public String[] getBuy() {
		String[] trade = { buyQuantity.getText(), buyPrice.getText() };
		return trade;
	}

	/**
	 * This function gets sell quantity and sell price texts when sell button
	 * pressed
	 * 
	 * @returns sell quantity and sell price
	 */
	public String[] getSell() {
		String[] trade = { sellQuantity.getText(), sellPrice.getText() };
		return trade;
	}

	@Override
	public List getList() {
		return null;
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColors();

	}

	@Override
	public void setLabel(JLabel coinValue) {
		this.coinValue.setText(coinValue.getText());
		updateUI();
	}

	/**
	 * This function sets color of fav button
	 * 
	 * @param color
	 */
	public void setFavoriteColor(Color color) {
		favorite.setBackground(color);
	}

	@Override
	public void setList(List list) {
		return;
	}

	/**
	 * This function shows a 2 seconds alert by given text
	 * 
	 * @param string
	 */
	public void showAlert(String string) {
		alert.setText(string);
		alert.setVisible(true);
		new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alert.setVisible(false);
			}
		}).start();
	}

	private void updateColors() {
		setBackground(palette.BACKGROUND);
		chartPanel.setBackground(palette.BACKGROUND);
		coinValue.setForeground(palette.FIRST_COLOR);
		dayCandle.setBackground(palette.BACKGROUND);
		dayCandle.setForeground(palette.SECOND_COLOR);
		hourCandle.setBackground(palette.BACKGROUND);
		hourCandle.setForeground(palette.SECOND_COLOR);
		favorite.setBackground(palette.SECOND_COLOR);
		favorite.setForeground(palette.BACKGROUND);
		back.setBackground(palette.FIRST_COLOR);
		back.setForeground(palette.BACKGROUND);
		sell.setBackground(Color.RED);
		sell.setForeground(palette.BACKGROUND);
		buy.setBackground(Color.GREEN);
		buy.setForeground(palette.BACKGROUND);
		buyQuantity.setBackground(palette.BACKGROUND);
		buyQuantity.setForeground(palette.FIRST_COLOR);
		sellQuantity.setBackground(palette.BACKGROUND);
		sellQuantity.setForeground(palette.FIRST_COLOR);
		buyPrice.setBackground(palette.BACKGROUND);
		buyPrice.setForeground(palette.FIRST_COLOR);
		sellPrice.setBackground(palette.BACKGROUND);
		sellPrice.setForeground(palette.FIRST_COLOR);
		alert.setBackground(palette.BACKGROUND);
		alert.setForeground(palette.FIRST_COLOR);
	}
}
