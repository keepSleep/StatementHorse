package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.financialstatements.model.FinancialStatementsDAO;
import com.financialstatements.model.FinancialStatementsVO;

public class createFinancialStatements {

	public static void createTime(int stock_no, String showtime) throws ParseException {
		FinancialStatementsDAO financialStatementsDAO = new FinancialStatementsDAO();
		FinancialStatementsVO financialStatementsVO = new FinancialStatementsVO();

		for (int statementDate = 10201; statementDate <= 10503; statementDate++) {
			String input = null;
			if (statementDate == 10201) {
				input = "2013-05-10";
			}
			if (statementDate == 10202) {
				input = "2013-08-10";
			}
			if (statementDate == 10203) {
				input = "2013-10-11";
			}
			if (statementDate == 10204) {
				input = "2014-03-10";
			}
			if (statementDate == 10205) {
				statementDate = 10301;
			}
			if (statementDate == 10301) {
				input = "2014-05-10";
			}
			if (statementDate == 10302) {
				input = "2014-08-10";
			}
			if (statementDate == 10303) {
				input = "2014-10-12";
			}
			if (statementDate == 10304) {
				input = "2015-03-10";
			}
			if (statementDate == 10305) {
				statementDate = 10401;
			}
			if (statementDate == 10401) {
				input = "2015-05-10";
			}
			if (statementDate == 10402) {
				input = "2015-08-10";
			}
			if (statementDate == 10403) {
				input = "2015-10-11";
			}
			if (statementDate == 10404) {
				input = "2016-03-10";
			}
			if (statementDate == 10405) {
				statementDate = 10501;
			}
			if (statementDate == 10501) {
				input = "2016-05-10";
			}
			if (statementDate == 10502) {
				input = "2016-08-10";
			}
			if (statementDate == 10503) {
				input = showtime;
			}

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date day = formatter.parse(input);
			// 時間
			int hh = (int) ((Math.random() * 7) + 13);
			int mm = (int) ((Math.random() * 59) + 1);

			String time = hh + ":" + String.format("%02d", mm);

			financialStatementsVO.setStockNo(stock_no);
			financialStatementsVO.setStatementDate("" + statementDate);
			financialStatementsVO.setPostDate(day);
			financialStatementsVO.setPostTime(time);

			financialStatementsDAO.insert(financialStatementsVO);
		}
	}

	public static void main(String[] args) throws ParseException {
		// 股號&第三季的假日期
		int[] stock = new int[] { 1101, 1102, 1216, 1301, 1303, 1326, 1402, 1476, 2002, 2105, 2207, 2227, 2301, 2303,
				2308, 2311, 2317, 2325, 2330, 2354, 2357, 2382, 2395, 2408, 2409, 2412, 2454, 2474, 2801, 2880, 2881,
				2882, 2883, 2884, 2885, 2886, 2887, 2890, 2891, 2892, 2912, 3008, 3045, 3474, 3481, 4904, 4938, 5880,
				6505, 9904 };
		for (int i = 0; i < 25; i++) {
			createTime(stock[i], "2016-11-10");
		}
	}

}
