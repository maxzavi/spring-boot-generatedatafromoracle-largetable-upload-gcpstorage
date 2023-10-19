package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.DemoRepository;
import com.example.demo.repository.GcpStorageUpload;
import com.example.demo.util.OpenCsvWriter;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class DemoApplication implements CommandLineRunner{

	@Value("${filepath}")
	String pathFile;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private DemoRepository demoRepository;

	@Autowired
	private GcpStorageUpload gcpStorageUpload;

	@Override
	public void run(String... args) throws Exception {
		boolean sendGcp=false;
		log.info("Start");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String name = "mytable.csv";
		var result= demoRepository.getDataShort();
		
		//var result = demoRepository.getData();
		log.info(result.size());
		try {
			String dateString = sdf.format(Calendar.getInstance().getTime());
			OpenCsvWriter writer = new OpenCsvWriter(pathFile + dateString + "_" + name);
			writer.writeData(result);

			if(sendGcp){
				//Upload to GCP
				gcpStorageUpload.upload(pathFile,  dateString + "_" + name);
			}

		} catch (Exception e) {
			log.error("Error writing data :" , e);
		}
		log.info("end");
	}

}
