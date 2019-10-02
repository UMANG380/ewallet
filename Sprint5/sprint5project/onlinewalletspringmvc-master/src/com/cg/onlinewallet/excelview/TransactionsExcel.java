package com.cg.onlinewallet.excelview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.cg.onlinewallet.dto.TransactionHistory;



public class TransactionsExcel extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		res.setHeader("Content-Disposition", "attachment;filename=\"transactions.xls\"");
		List<TransactionHistory> transactions = (List<TransactionHistory>) model.get("transactionList");
		 Sheet sheet = workbook.createSheet("transaction history Data");
		 Row header = sheet.createRow(0);
		 header.createCell(0).setCellValue("transaction ID");
		 header.createCell(1).setCellValue("transaction amount");
		 header.createCell(2).setCellValue("transaction description");
		 header.createCell(3).setCellValue("transaction balance");
		 int rowNum = 1;
		 for(TransactionHistory transaction:transactions){
		 Row row = sheet.createRow(rowNum++);
		 row.createCell(0).setCellValue(transaction.getTransactionId());
		 row.createCell(1).setCellValue(transaction.getDateOfTransaction().toString());
		 row.createCell(2).setCellValue(transaction.getAmount());
		 row.createCell(3).setCellValue(transaction.getDescription());
		 row.createCell(4).setCellValue(transaction.getBalance());
		 }
		 }
	}

