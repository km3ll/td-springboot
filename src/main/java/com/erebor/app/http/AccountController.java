package com.erebor.app.http;

import com.erebor.app.http.dto.AccountResponse;
import com.erebor.app.http.dto.BaseResponse;
import com.erebor.app.kafka.AccountOpenedPublisher;
import com.erebor.app.kafka.dto.KafkaAccountOpenedEvent;
import com.erebor.domain.model.Account;
import com.erebor.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountOpenedPublisher accountOpenedPublisher;

	@RequestMapping(value = "{country}/account", method = RequestMethod.GET)
	public ResponseEntity findAccount(@PathVariable(name = "country") String countryCode,
			@RequestParam("no") int accountNo) {

		Optional<Account> result = accountRepository.find(countryCode, accountNo);
		if (result.isPresent()) {
			AccountResponse response = new AccountResponse(LocalDateTime.now().toString(), result.get());

			KafkaAccountOpenedEvent event = new KafkaAccountOpenedEvent(response.getDateTime(),
					response.getAccount().getCountryCode(), String.valueOf(response.getAccount().getNo()),
					Double.toString(response.getAccount().getBalance()));
			accountOpenedPublisher.publish(event);

			return new ResponseEntity<AccountResponse>(response, HttpStatus.OK);
		}
		else {
			BaseResponse response = new BaseResponse(LocalDateTime.now().toString(),
					String.format("Account number %s for country code %s not found", accountNo, countryCode));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NOT_FOUND);
		}

	}

}
