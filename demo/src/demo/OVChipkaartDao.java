package demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDao {
	
	public List<OVChipkaart> findAll() throws SQLException;
	public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
	public OVChipkaart save(OVChipkaart ovchipkaart) throws SQLException;
	public OVChipkaart update(OVChipkaart ovchipkaart) throws SQLException;
	public boolean delete(OVChipkaart ovchipkaart) throws SQLException;
	public void closeConnection(Connection conn) throws SQLException;

}
