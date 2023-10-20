package com.example.demooraclegcp.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2

public class ProducRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Object[]> getAll(){
        String sql = "SELECT org_lvl_number, org_name_full FROM orgmstee";
        //String sql = "SELECT * FROM ifhprdmst";
        log.info("Fecth data ...");
        jdbcTemplate.setFetchSize(10000);
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) ->{
                Object[] register = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    register[i]=rs.getObject(i+1);
                }
                return register;
            }
            );

    } 
}
