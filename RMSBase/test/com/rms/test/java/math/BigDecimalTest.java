package com.rms.test.java.math;


public class BigDecimalTest {

	public static void main(String[] args) {
		// 入力スキーマと出力スキーマに従って生成されたコード
		Iinput_row input_row = new Iinput_row();

		int pricelistYear = (input_row.pricelistYear != null && input_row.pricelistYear.matches("\\d+")) ? Integer.parseInt(input_row.pricelistYear) : Integer.MAX_VALUE;
		int coverDateYear = (input_row.coverDateYear != null && input_row.coverDateYear.matches("\\d+")) ? Integer.parseInt(input_row.coverDateYear) : Integer.MAX_VALUE;
		int coverDateMonth = (input_row.coverDateMonth != null && input_row.coverDateMonth.matches("\\d+")) ? Integer.parseInt(input_row.coverDateMonth) : Integer.MAX_VALUE;
		int printDateYear = (input_row.printDateYear != null && input_row.printDateYear.matches("\\d+")) ? Integer.parseInt(input_row.printDateYear) : Integer.MAX_VALUE;
		int printDateMonth = (input_row.printDateMonth != null && input_row.printDateMonth.matches("\\d+")) ? Integer.parseInt(input_row.printDateMonth) : Integer.MAX_VALUE;
		int printDateDay = (input_row.printDateDay != null && input_row.printDateDay.matches("\\d+")) ? Integer.parseInt(input_row.printDateDay) : Integer.MAX_VALUE;
		int copyrightYear = (input_row.copyrightYear != null && input_row.copyrightYear.matches("\\d+")) ? Integer.parseInt(input_row.copyrightYear) : Integer.MAX_VALUE;
		int onlineDateYear = (input_row.onlineDateYear != null && input_row.onlineDateYear.matches("\\d+")) ? Integer.parseInt(input_row.onlineDateYear) : Integer.MAX_VALUE;

		Integer minYear = Math.min(pricelistYear, Math.min(Math.min(coverDateYear, printDateYear), Math.min(copyrightYear, onlineDateYear)));
		Integer minMonth = null;
		Integer minDay = null;

		if (pricelistYear == minYear&& minYear) {
			input_row.pricelistYearTag = null;
		} else if (copyrightYear == minYear) {
			input_row.copyrightYearTag = null;
		} else if (onlineDateYear == minYear) {
			input_row.onlineDateYearTag = null;
		} else if (coverDateYear == minYear) {
			input_row.coverDateYearTag = null;
			minMonth = coverDateMonth;
		} else if (printDateYear == minYear) {
			input_row.printDateYearTag = null;
			minMonth = printDateMonth;
			minDay = printDateDay;
		}

		input_row.pub_date_year = minYear == null ? "" : String.valueOf(minYear);
		input_row.pub_date_month = minMonth == null ? "" : String.valueOf(minMonth);
		input_row.pub_date_day = minDay == null ? "" : String.valueOf(minDay);
	}

	class Iinput_row {

		String pricelistYear;
		String coverDateYear;
		String coverDateMonth;
		String printDateYear;
		String printDateMonth;
		String printDateDay;
		String copyrightYear;
		String onlineDateYear;
	}
}
