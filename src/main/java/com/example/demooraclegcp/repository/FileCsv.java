package com.example.demooraclegcp.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.opencsv.CSVWriter;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class FileCsv {
    
    @Value("${filepath}")
    String path;

    public void write(String name, List<Object[]> data) throws IOException{
        //log.info(path);
        CSVWriter writer = new CSVWriter(new FileWriter(path + name));
        List<String[]> dataString = new ArrayList<String[]>();
        for (Object[] objects : data) {
            String[] filaString = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                
                if (objects[i]!=null){
                    filaString[i]=objects[i].toString().replace('\n', ' ').replace('\r', ' ');

                }else{
                    filaString[i]="";
                }
            }
            dataString.add(filaString);
        }
        writer.writeAll(dataString);
        writer.close();
        log.info("File created!!");
    }

}
