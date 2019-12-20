package dakhli.elyes;

import java.math.BigDecimal;

import dakhli.elyes.model.Account;
import dakhli.elyes.model.CurrencyEnum;
import dakhli.elyes.services.AccountService;
import dakhli.elyes.services.AccountServiceImpl;
import dakhli.elyes.utils.CommonUtils;

public class App {
	
	public static void main(String[] args) {
		AccountService accountServices = new AccountServiceImpl();
		
		Account account = new Account(CurrencyEnum.EURO);
		accountServices.deposit(account, new BigDecimal(1500), CommonUtils.date("15/12/2019"));
		accountServices.withdrawal(account, new BigDecimal(250), CommonUtils.date("16/12/2019"));
		accountServices.printStatement(account, System.out);
		
	}

}
