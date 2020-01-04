package model.bean;

public class AccountModeratore_Bean extends AccountUtenteRegistrato_Bean {

	private Long id;

	public AccountModeratore_Bean(String email, String password, Long id) {
		super(email, password, AccountUtenteRegistrato_Bean.Moderatore);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
