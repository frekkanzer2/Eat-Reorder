package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Carrello;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;

public interface GestoreOrdineDao {

	public Ordine_Bean creaOrdine(Ordine_Bean order, AccountCliente_Bean user, Carrello cart) throws SQLException;

	public boolean controlloEsistenzaOrdine(Long idOrdine) throws SQLException;

	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException;

	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException;

	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException;

	public void ordineSetRitirato(Long idOrdine) throws SQLException;

	public void ordineSetConsegnato(Long idOrdine) throws SQLException;
}
