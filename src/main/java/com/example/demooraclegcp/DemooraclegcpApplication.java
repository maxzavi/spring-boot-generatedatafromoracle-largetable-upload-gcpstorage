package com.example.demooraclegcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demooraclegcp.repository.FileCsv;
import com.example.demooraclegcp.repository.GCPRepository;
import com.example.demooraclegcp.repository.ProducRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2

public class DemooraclegcpApplication implements CommandLineRunner{
    @Autowired
	private ProducRepository producRepository;

	@Autowired
	private FileCsv fileCsv;

	@Autowired
	private GCPRepository gcpRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemooraclegcpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Start");
		//Extraer la data
		var result= producRepository.getAll();

		log.info("Se encontraron " + result.size() + " filas");

		//Generar archivo
		String filename ="mydata.csv";
		fileCsv.write(filename, result);
		log.info("Archivo generado");

		//Subir al Cloud Storage
		gcpRepository.upload(filename);
		log.info("End");
	}
}
