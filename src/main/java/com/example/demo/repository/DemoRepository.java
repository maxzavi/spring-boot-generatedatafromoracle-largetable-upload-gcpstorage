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
    //private String sql = "select prd_lvl_number, prd_full_name, cod_bar from ifhprdmst";

    private String sql = "select * from ifhprdmst";
    //private String sql = "select prd_lvl_number, prd_full_name, cod_bar from ifhprdmst where rownum<=100";
    //private String sql ="SELECT prd_full_name, DES_ADI_RF  FROM ifhprdmst p WHERE p.PRD_LVL_CHILD  IN (189865,189866)";

    public List<Object[]> getData() {
        var result = new ArrayList<Object[]>();
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

    public List<Object[]> getDataShort(){
        jdbcTemplate.setFetchSize(10000);
        List<Object[]> result = jdbcTemplate.query(
            sql, 
            (rs, rowNum) -> {
                var list = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //
                    String val = "";
                    if (rs.getObject(i + 1)!=null){
                        val=rs.getString(i +1).replace('\r',' ').replace('\n',' ');
                        
                    }
                    list[i] = val;

                }
                return list;
            }
            );
        return result;        
    }

}
