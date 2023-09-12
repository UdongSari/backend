package com.udongsari.configure.details;

import com.udongsari.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> userAccount = accountRepository.findByUsername(username);

		if (userAccount.isEmpty()) {
			throw new UsernameNotFoundException(" * Not Found Account");
		}

		return new PrincipalDetails(userAccount.get());
	}
}
