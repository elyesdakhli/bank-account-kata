package dakhli.elyes.model;

public enum TransactionTypeEnum {
	CREDIT("Credit"), DEBIT("Debit");
	
	private String label;
	
	private TransactionTypeEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
