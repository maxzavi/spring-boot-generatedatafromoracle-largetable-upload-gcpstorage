package com.example.demo.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class OpenCsvWriter {
    private CSVWriter writer ;

    public OpenCsvWriter(String pathFile) throws IOException {
        writer = new CSVWriter(new FileWriter(pathFile));
    }

    public void writeData(List<Object[]> dataList) throws IOException{
        for (Object[] fields : dataList) {
			String[] record = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				if (fields[i]!=null){
					record[i] = fields[i].toString();
				}else{
					record[i] = "";
				}
			};
    		writer.writeNext(record, false);
		}
    	writer.close();
    }

}
