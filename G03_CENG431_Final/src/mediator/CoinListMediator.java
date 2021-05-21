package mediator;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import fileio.repository.BanknoteRepository;
import fileio.repository.CoinRepository;
import fileio.repository.IRestrictedRepository;
import model.Currency;
import view.LabelInfo;
import view.color.ColorPalette;
import view.color.DarkTheme;

public class CoinListMediator {

	private IRestrictedRepository<Currency> coinRepository;
	private IRestrictedRepository<Currency> banknoteRepository;

	public CoinListMediator() {
		coinRepository = new CoinRepository();
		banknoteRepository = new BanknoteRepository();
	}

	private List<LabelInfo> getLabel(Currency currency) {

		List<LabelInfo> labels = new ArrayList<LabelInfo>();
		final Iterator<Currency> banknoteIterator = banknoteRepository.getAll();
		Currency banknote = null;

		while (banknoteIterator.hasNext()) {

			banknote = banknoteIterator.next();
			Double oldValue = currency.getOldValue().get(banknote.getName());

			Double value = currency.getValue().get(banknote.getName());
			if (value == null) {
				value = (double) 0;
			}

			if (oldValue == null) {
				oldValue = value;
			}
			Double howMuch;
			if (value != 0) {
				howMuch = (value * 100 / oldValue);
			} else {
				howMuch = (double) 100;
			}

			Color result = null;
			ColorPalette palette = new ColorPalette(new DarkTheme());
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(4);
			Double howMuchFormatted = Double.valueOf(df.format(howMuch).replaceAll(",","."));
			if (howMuchFormatted > 100) {
				howMuch = howMuch - 100;
				result = Color.GREEN;
			} else if (howMuchFormatted == 100) {
				howMuch = (double) 0;
				result = palette.FIRST_COLOR;
			}

			else {
				howMuch = 100 - howMuch;
				result = Color.RED;
			}

			LabelInfo newLabel = new LabelInfo(result, howMuch, currency.getName(), value, banknote.name);
			labels.add(newLabel);

		}
		return labels;

	}

	public DefaultListModel<JLabel> getList() {
		Thread.LOCK_MUTEX(Thread.MUTEX);
		DefaultListModel<JLabel> list = new DefaultListModel<JLabel>();
		final Iterator<Currency> newIterator = coinRepository.getAll();
		Currency currency = null;
		while (newIterator.hasNext()) {
		
			currency = newIterator.next();
			List<LabelInfo> labels = getLabel(currency);

			setLabels(labels, list);
		}
		Thread.UNLOCK_MUTEX(Thread.MUTEX);
		return list;
	}

	public void setLabels(List<LabelInfo> labels, DefaultListModel<JLabel> listModel) {
		int index = 0;
		for (LabelInfo labelInfo : labels) {
			JLabel label = createJLabel(labelInfo);
			listModel.add(index, label);
			index++;
		}
	}

	public JLabel createJLabel(LabelInfo label) {

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		String text = label.coinName + "/" + label.banknote + " : " + label.value + " (" + df.format(label.percent)
				+ "%)";

		JLabel jLabel = new JLabel(text);
		jLabel.setForeground(label.color);

		return jLabel;
	}
}
