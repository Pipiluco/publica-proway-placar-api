package life.pifrans.exceptions.errors;

/*
 * Classe para tratar erros de integração de dados
 */
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

}
