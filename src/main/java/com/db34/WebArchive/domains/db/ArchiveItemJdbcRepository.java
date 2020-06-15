package com.db34.WebArchive.domains.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveItemJdbcRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Spring Boot will create and configure DataSource and JdbcTemplate
	// To use it, just @Autowired
	@Autowired
	private JdbcTemplate jdbcTemplate;

//    public ArchiveItemJdbcRepository() {
//    	super();
//        try {
//			Connection conn = DriverManager.getConnection ("jdbc:h2:tcp://2280.hostserv.eu:8321/webarchive", "scott","T1g3rH2$Docker");
//			System.out.println("connection: " + conn.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    }

//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

	class ArchiveItemRowMapper implements RowMapper<ArchiveItem> {

		@Override
		public ArchiveItem mapRow(ResultSet rs, int rowNum) throws DataAccessException {
			ArchiveItem item = new ArchiveItem();
			try {
				item.setId(rs.getLong("id"));
				item.setName(rs.getString("name"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//	        student.setPassportNumber(rs.getString("passport_number"));
			return item;
		}
	}

	public List<ArchiveItem> findAll() {
		List<ArchiveItem> list = null;
		if (this.jdbcTemplate == null) {
			logger.error("no jdbc connection");
			return null;
		}
		try {
			ArchiveItemRowMapper mapper = new ArchiveItemRowMapper();
			list = jdbcTemplate.query("select * from ARCHIVE_ITEM", mapper);
		} catch (DataAccessException sx) {
			logger.error(sx.getMessage());
		}
		return list;
	}

	public ArchiveItem findById(long id) {
		try {
			return jdbcTemplate.queryForObject("select * from ARCHIVE_ITEM where id=?", new Object[] { id },
					new BeanPropertyRowMapper<ArchiveItem>(ArchiveItem.class));
		} catch (DataAccessException sx) {
			logger.error(sx.getMessage());
		}
		return null;
	}

	public int deleteById(long id) {
		try {
			return jdbcTemplate.update("delete from ARCHIVE_ITEM where id=?", new Object[] { id });
		} catch (DataAccessException sx) {
			logger.error(sx.getMessage());
		}
		return -1;
	}

	public int insert(ArchiveItem item) {
		try {
			return jdbcTemplate.update("insert into ARCHIVE_ITEM (id, name) " + "values(?,  ?)",
					new Object[] { item.getId(), item.getName() });
		} catch (DataAccessException sx) {
			logger.error(sx.getMessage());
		}
		return -1;
	}

	public int update(ArchiveItem item) {
		try {
			return jdbcTemplate.update("update ARCHIVE_ITEM " + " set name = ? " + " where id = ?",
					new Object[] { item.getName(), item.getId() });
		} catch (DataAccessException sx) {
			logger.error(sx.getMessage());
		}
		return -1;
	}

	public Long nextId() {
		return jdbcTemplate.queryForObject("SELECT MAX(NVL(ID,0))+1 FROM ARCHIVE_ITEM", Long.class);
	}
}
