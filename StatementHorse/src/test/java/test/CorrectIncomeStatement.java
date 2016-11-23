package test;

import java.text.DecimalFormat;

import com.incomestatement.model.IncomeStatementDAO;
import com.incomestatement.model.IncomeStatementService;
import com.incomestatement.model.IncomeStatementVO;
import com.stock.model.StockVO;

public class CorrectIncomeStatement {

	public static void main(String[] args) {
		IncomeStatementDAO incomeStatementDAO = new IncomeStatementDAO();

		// 抓出每季EPS
		int seasonArray = 3;

		// 第四季EPS
		Double fourthEPS = incomeStatementDAO.getByStockNo(1101).get(seasonArray).getEarningPerShare();
		System.out.println(fourthEPS);

		// 加總前三季EPS
		Double countThreeSeason =0.0;
		// 抓前三季EPS
		for (int varSeason = seasonArray - 3; varSeason < 3; varSeason++) {
			Double theEPS = incomeStatementDAO.getByStockNo(1101).get(varSeason).getEarningPerShare();
			System.out.println(theEPS);
			countThreeSeason=countThreeSeason+theEPS;
			System.out.println(countThreeSeason);
		}
		// 計算第四季EPS
		fourthEPS=fourthEPS-countThreeSeason;
		System.out.println(fourthEPS);

		// 取小數點後兩位
		 DecimalFormat df = new DecimalFormat("##.00");
		 fourthEPS = Double.parseDouble(df.format(fourthEPS));
		 System.out.println(fourthEPS);
	
		 //進資料庫
		 IncomeStatementVO incomeStatementVO = new IncomeStatementVO();
		 StockVO stockVO = new StockVO();
		 stockVO.setStockNo(1101);
		 incomeStatementVO.setStockVO(stockVO);
		 incomeStatementVO.setStatementDate("10204");
		 incomeStatementVO.setEarningPerShare(fourthEPS);
		 incomeStatementDAO.insert(incomeStatementVO);
		 
		 
	}

	

	 
	// System.out.println(firstEPS);
	// System.out.println(secondEPS);
	// System.out.println(thirdEPS);
	// System.out.println(finalEPS);

}
