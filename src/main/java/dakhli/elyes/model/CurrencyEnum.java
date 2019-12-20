package dakhli.elyes.model;

public enum CurrencyEnum {
	EURO("EUR", "€"), 
	USDOLLAR("USD", "$");
	
	private String currencyValue;
	private String label;
	
	CurrencyEnum(String currencyValue, String label){
		this.currencyValue = currencyValue;
		this.label = label;
	}

	public String getValue() {
		return currencyValue;
	}

	@Override
	public String toString() {
		return this.label;
	}
	
}
