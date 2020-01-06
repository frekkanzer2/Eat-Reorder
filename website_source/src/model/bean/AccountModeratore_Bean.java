package model.bean;

public class AccountModeratore_Bean extends AccountUtenteRegistrato_Bean {

	private Long id;

	/**
	 * Costruttore pubblico per la classe AccountModeratore_Bean
	 * @param email email del moderatore
	 * @param password password del moderatore
	 * @param id id del moderatore
	 */
	public AccountModeratore_Bean(String email, String password, Long id) {
		super(email, password, AccountUtenteRegistrato_Bean.Moderatore);
		this.id = id;
	}

	/**
	 * @return Id del moderatore
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id id da settare
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
