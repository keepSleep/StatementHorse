package test;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

import com.incomestatement.model.IncomeStatementDAO;
import com.incomestatement.model.IncomeStatementService;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockVO;

public class CorrectIncomeStatement {

	static IncomeStatementDAO incomeStatementDAO = new IncomeStatementDAO();
	static int seasonFromArray = 3;

	public static Double getFourthEPS(int seasonFromArray) {
		Double fourthEPS = incomeStatementDAO.getByStockNo(1101).get(seasonFromArray).getEarningPerShare();
		return fourthEPS;
	}

	public static Double countThreeEPS(int seasonFromArray) {
		Double theEPS = 0.0;
		Double count = 0.0;
		for (int i = 3; i > 0; i--) {
			int varSeason = seasonFromArray - i;
			theEPS = incomeStatementDAO.getByStockNo(1101).get(varSeason).getEarningPerShare();

			count = count + theEPS;
		}
		return count;
	}

	public static Double computeEPS(int seasonFromArray) {
		Double result = getFourthEPS(seasonFromArray) - countThreeEPS(seasonFromArray);

		return result;
	}

	public static Double resultEPS(int seasonFromArray) {
		DecimalFormat df = new DecimalFormat("##.00");
		Double computeEPS = Double.parseDouble(df.format(computeEPS(seasonFromArray)));

		return computeEPS;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		IncomeStatementVO incomeStatementVO = new IncomeStatementVO();
		StockVO stockVO = new StockVO();
		stockVO.setStockNo(1101);
		incomeStatementVO.setStockVO(stockVO);
		incomeStatementVO.setStatementDate("10201");
		IncomeStatementVO target = incomeStatementDAO.findByPrimaryKey(incomeStatementVO);

		// 抓屬性的猛猛法
		Class<?> c = null;
		c = Class.forName("com.incomestatement.model.IncomeStatementVO");
		Field[] fields = c.getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);
		}
		for (Field f : fields) {
			String field = f.toString().substring(f.toString().lastIndexOf(".") + 1); // 取出属性名称

			System.out.println(f.toString());

			// System.out.println(f.get(aa));
		}
		// System.out.println(fields.length);
		// System.out.println(fields[15].get(target));

		//////////////////////

		// int theSize = incomeStatementDAO.getByStockNo(1101).size();
		// for (seasonFromArray = 3; seasonFromArray < theSize;
		// seasonFromArray++) {
		// if ((seasonFromArray + 1) % 4 == 0)
		// System.out.println(resultEPS(seasonFromArray));
		// // 進資料庫
		// IncomeStatementVO incomeStatementVO = new IncomeStatementVO();
		// StockVO stockVO = new StockVO();
		// stockVO.setStockNo(1101);
		// incomeStatementVO.setStockVO(stockVO);
		//
		// Integer yearInt = 102;
		// String year = String.valueOf(yearInt);
		// incomeStatementVO.setStatementDate(year + 04);
		//
		// incomeStatementVO.setEarningPerShare(resultEPS(seasonFromArray));
		// incomeStatementDAO.insert(incomeStatementVO);
		// yearInt++;
		// }
	}
}
