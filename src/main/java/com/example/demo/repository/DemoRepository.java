package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class DemoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Object[]> getData() {
        var result = new ArrayList<Object[]>();
        String sql = "select prd_lvl_number, prd_full_name, cod_bar from ifhprdmst";
        jdbcTemplate.setFetchSize(10000);
        
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Object[] data = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    data[i] = rs.getObject(i + 1);
                }
                result.add(data);
            }
        });
        log.debug("End get data");
        return result;
    }

}
