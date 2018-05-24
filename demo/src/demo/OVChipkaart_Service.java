package demo;

import java.sql.Date;
import java.sql.SQLException;

public class OVChipkaart_Service {

	public static void main(String[] args) throws SQLException {
		
		OVChipkaartDao ovDao = new OVChipkaartOracleDaoImpl();
		
		for (OVChipkaart ov : ovDao.findAll()) {
			System.out.print("kaartnummer: " + ov.getKaartNummer());
			System.out.print("	geldig: " + ov.getGeldigTot());
			System.out.print("	klasse: " + ov.getKlasse());
			System.out.print("	saldo: " + ov.getSaldo());
			System.out.print("	naam:" + ov.getReiziger().getVoorletters() + " " + ov.getReiziger().getTussenvoegsels() + " " + ov.getReiziger().getAchternaam());
			System.out.println("");
		}
		
		System.out.println("");
		
		Reiziger reiziger = new Reiziger(2, "B", "van", "Rijn", Date.valueOf("2002-10-22"));
		
		for (OVChipkaart ov : ovDao.findByReiziger(reiziger)) {
			System.out.print("kaartnummer: " + ov.getKaartNummer());
			System.out.print("	geldig: " + ov.getGeldigTot());
			System.out.print("	klasse: " + ov.getKlasse());
			System.out.print("	saldo: " + ov.getSaldo());
			System.out.print("	naam:" + ov.getReiziger().getVoorletters() + " " + ov.getReiziger().getTussenvoegsels() + " " + ov.getReiziger().getAchternaam());
			System.out.println("");

		}
		
		OVChipkaart ovChip = new OVChipkaart(123467, Date.valueOf("2018-10-10"), 3, 150.0, reiziger);

		System.out.println(ovDao.save(ovChip)); 
		System.out.println(ovDao.update(ovChip));
		System.out.println(ovDao.delete(ovChip));
	}

}
