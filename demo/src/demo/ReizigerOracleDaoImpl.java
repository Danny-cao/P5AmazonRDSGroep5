package demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
	
	public List<Reiziger> findAll() throws SQLException {
		
		Connection conn = super.getConnection();
		
		String getReizigers = "select kaartnummer, geldigtot, klasse, saldo, reiziger.reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum from ov_chipkaart, reiziger where ov_chipkaart.reizigerid = reiziger.reizigerid";
		
		PreparedStatement pstmt1 = conn.prepareStatement(getReizigers);
		
		ResultSet rs = pstmt1.executeQuery();
		
		int id;
		String voorletters;
		String tussenvoegsels;
		String achternaam;
		Date geboortedatum;
		
		int kaartnummer;
		Date geldigtot;
		int klasse;
		double saldo;
		
		List<Reiziger> reizigers = new ArrayList<>();
		
		while (rs.next()) {   
			
			kaartnummer = rs.getInt("kaartnummer");
			geldigtot = rs.getDate("geldigtot");
			klasse = rs.getInt("klasse");
			saldo = rs.getDouble("saldo");
			
			id = rs.getInt("reizigerid");
			voorletters = rs.getString("voorletters");
			tussenvoegsels = rs.getString("tussenvoegsel");
			achternaam = rs.getString("achternaam");
			geboortedatum = rs.getDate("gebortedatum");
			
			Reiziger r = new Reiziger(id, voorletters, tussenvoegsels, achternaam, geboortedatum);
			reizigers.add(r);
	
			OVChipkaart o = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, r);
			r.setOVChipkaart(o);
			
		}
		
		return reizigers;
		
	}
	
	public List<Reiziger> findByGBdatum(String GBdatum) throws SQLException {
		
		Connection conn = super.getConnection();
		
		String getReizigers = "select kaartnummer, geldigtot, klasse, saldo, reiziger.reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum from ov_chipkaart, reiziger where ov_chipkaart.reizigerid = reiziger.reizigerid and gebortedatum = ?";
		
		PreparedStatement pstmt1 = conn.prepareStatement(getReizigers);
		
		pstmt1.setString(1, GBdatum);
		
		ResultSet rs = pstmt1.executeQuery();
		
		int id;
		String voorletters;
		String tussenvoegsels;
		String achternaam;
		Date geboortedatum;
		
		int kaartnummer;
		Date geldigtot;
		int klasse;
		double saldo;
		
		List<Reiziger> reizigers = new ArrayList<>();
		
		while (rs.next()) {   
			
			kaartnummer = rs.getInt("kaartnummer");
			geldigtot = rs.getDate("geldigtot");
			klasse = rs.getInt("klasse");
			saldo = rs.getDouble("saldo");
			
			id = rs.getInt("reizigerid");
			voorletters = rs.getString("voorletters");
			tussenvoegsels = rs.getString("tussenvoegsel");
			achternaam = rs.getString("achternaam");
			geboortedatum = rs.getDate("gebortedatum");
			
			Reiziger r = new Reiziger(id, voorletters, tussenvoegsels, achternaam, geboortedatum);
			reizigers.add(r);
	
			OVChipkaart o = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, r);
			r.setOVChipkaart(o);
			
		}
		
		return reizigers;
		
	}
	
	public Reiziger save(Reiziger reiziger) throws SQLException {
		
		Connection conn = super.getConnection();
		
		String saveReiziger = "insert into REIZIGER values (?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt1 = conn.prepareStatement(saveReiziger);
		
		pstmt1.setInt(1, reiziger.getReizigerID());
		pstmt1.setString(2, reiziger.getVoorletters());
		pstmt1.setString(3, reiziger.getTussenvoegsels());
		pstmt1.setString(4, reiziger.getAchternaam());
		pstmt1.setDate(5, reiziger.getGbdatum());
		
		int count = pstmt1.executeUpdate();
		
		if (count > 0) {
			
			return reiziger;
			
		}
		
		return null;
		
	}
	
	public Reiziger update(Reiziger reiziger) throws SQLException {
		
		Connection conn = super.getConnection();
		
		String updateReiziger = "update reiziger set voorletters = ?, tussenvoegsel = ?, achternaam = ?, gebortedatum = ? where reizigerid = ?";
		
		PreparedStatement pstmt1 = conn.prepareStatement(updateReiziger);
		
		pstmt1.setString(1, reiziger.getVoorletters());
		pstmt1.setString(2, reiziger.getTussenvoegsels());
		pstmt1.setString(3, reiziger.getAchternaam());
		pstmt1.setDate(4, reiziger.getGbdatum());
		pstmt1.setInt(5, reiziger.getReizigerID());
		
		if (pstmt1.executeUpdate() > 0) {
			
			return reiziger;
				
		}
		
		return null;
		
	}
	
	public boolean delete(Reiziger reiziger) throws SQLException {
		
		Connection conn = super.getConnection();
		
		String rVoorletters = reiziger.getVoorletters();
		String rTussenvoegsel = reiziger.getTussenvoegsels();
		String rAchternaam = reiziger.getAchternaam();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String rGBDatum = df.format(reiziger.getGbdatum());
		
		String getReiziger;
		boolean tv;
		
		if (rTussenvoegsel.equals("")) {
			
			tv = false;
	
			getReiziger = "select reizigerid from reiziger where voorletters = ? and tussenvoegsel is null and achternaam = ? and gebortedatum = ?";
			
		} else {
			
			tv = true;
			
			getReiziger = "select reizigerid from reiziger where voorletters = ? and tussenvoegsel = ? and achternaam = ? and gebortedatum = ?";
			
		}
		
		PreparedStatement pstmt1 = conn.prepareStatement(getReiziger);
		
		if (tv) {
			
			pstmt1.setString(1, rVoorletters);
			pstmt1.setString(2, rTussenvoegsel); 
			pstmt1.setString(3, rAchternaam);
			pstmt1.setString(4, rGBDatum);
			
		} else {
			
			pstmt1.setString(1, rVoorletters);
			pstmt1.setString(2, rAchternaam);
			pstmt1.setString(3, rGBDatum);
			
		}
		
		ResultSet rs = pstmt1.executeQuery();
		
		while(rs.next()) {
			
			int rID = rs.getInt("reizigerid");
			
			System.out.println(rID);
			
			String deleteReiziger = "delete from reiziger where reizigerid = ?";
			
			PreparedStatement pstmt2 = conn.prepareStatement(deleteReiziger);
			
			pstmt2.setInt(1, rID);
			
			if (pstmt2.executeUpdate() > 0) {
					
				return true;
					
			}
			
		}
		
		return false;
		
	}
	
	public void closeConnection(Connection conn) throws SQLException {
		
		conn.close();
		
	}

}
