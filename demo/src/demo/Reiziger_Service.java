package demo;

import java.sql.Date;
import java.sql.SQLException;

public class Reiziger_Service {

	public static void main(String[] args) throws SQLException {
		
		ReizigerDao rDao = new ReizigerOracleDaoImpl();
	
		Reiziger reiziger = new Reiziger(10, "F", "", "Memari", Date.valueOf("2002-12-03"));
		
		OVChipkaart ovChip = new OVChipkaart(123456, Date.valueOf("2018-10-10"), 2, 50.0, reiziger);
		
		reiziger.setOVChipkaart(ovChip);
		
		for (Reiziger r : rDao.findAll()) {
			
			System.out.print("naam: " + r.getVoorletters() + " " + r.getTussenvoegsels() + " " + r.getAchternaam());
			System.out.print("geboortedatum: " + r.getGbdatum());
			
			
			for (OVChipkaart ov : r.getOVChipkaart()) {
		
				System.out.print("kaartnummer: " + ov.getKaartNummer());
				System.out.print("	geldig: " + ov.getGeldigTot());
				System.out.print("	klasse: " + ov.getKlasse());
				System.out.print("	saldo: " + ov.getSaldo());
				System.out.print(" reiziger id: " + ov.getReiziger().getReizigerID());
				System.out.println("");
				
			}
			
		}
		
		for (Reiziger r : rDao.findByGBdatum("03-12-02")) {
			
			System.out.print("naam: " + r.getVoorletters() + " " + r.getTussenvoegsels() + " " + r.getAchternaam());
			System.out.print("geboortedatum: " + r.getGbdatum());
			System.out.println("");
			
			for (OVChipkaart ov : r.getOVChipkaart()) {
				
				System.out.print("kaartnummer: " + ov.getKaartNummer());
				System.out.print("	geldig: " + ov.getGeldigTot());
				System.out.print("	klasse: " + ov.getKlasse());
				System.out.print("	saldo: " + ov.getSaldo());
				System.out.print(" reiziger id: " + ov.getReiziger().getReizigerID());
				System.out.println("");
				
			}
			
		}
		
		rDao.save(reiziger);
		rDao.update(reiziger);
		rDao.delete(reiziger);

	}

}
