/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.ui.login;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Locale;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.util.MockUtils;

import org.easymock.Capture;
import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Unit test for {@link LoginPresenter}.
 * 
 * @author Petter Holmström
 */
public class LoginPresenterTest {

	AuthenticationManager authenticationManager;
	LoginView loginView;
	I18N i18n;
	LoginPresenter presenter;

	@Before
	public void setUp() {
		authenticationManager = createMock(AuthenticationManager.class);
		loginView = createMock(LoginView.class);
		i18n = createMock(I18N.class);

		presenter = new LoginPresenter(loginView, i18n);
		ReflectionTestUtils.setField(presenter, "authenticationManager",
				authenticationManager);
	}

	@After
	public void tearDown() {
		MockUtils.verifyMock(authenticationManager, loginView, i18n);
	}

	@Test
	public void testChangeLocale() {
		i18n.setLocale(Locale.US);
		replay(i18n);
		replay(loginView);
		replay(authenticationManager);

		presenter.changeLocale(Locale.US);
	}

	// @Test
	public void testAttemptLoginSuccess() {
		final Capture<Authentication> authentication = new Capture<Authentication>();
		final Capture<UserLoggedInEvent> event = new Capture<UserLoggedInEvent>();
		expect(authenticationManager.authenticate(capture(authentication)))
				.andAnswer(new IAnswer<Authentication>() {

					@Override
					public Authentication answer() throws Throwable {
						return authentication.getValue();
					}
				});
		replay(authenticationManager);

		loginView.clearForm();
		replay(loginView);

		replay(i18n);

		// FIXME Add test for the view event
		// applicationContext.publishEvent(capture(event));

		presenter.attemptLogin("joecool", "password");
		assertEquals("joecool", authentication.getValue().getName());
		assertEquals("password", authentication.getValue().getCredentials());
		assertSame(event.getValue().getAuthentication(),
				authentication.getValue());
	}

	@Test
	public void testAttemptLoginBadCredentials() {
		expect(
				authenticationManager
						.authenticate(anyObject(UsernamePasswordAuthenticationToken.class)))
				.andThrow(new BadCredentialsException("Bad credentials"));
		replay(authenticationManager);

		loginView.clearForm();
		loginView.showBadCredentials();
		replay(loginView);

		replay(i18n);

		presenter.attemptLogin("joecool", "password");
	}

	@Test
	public void testAttemptLoginLockedAccount() {
		expect(
				authenticationManager
						.authenticate(anyObject(UsernamePasswordAuthenticationToken.class)))
				.andThrow(new LockedException("Account locked"));
		replay(authenticationManager);

		loginView.clearForm();
		loginView.showAccountLocked();
		replay(loginView);

		replay(i18n);

		presenter.attemptLogin("joecool", "password");
	}

	@Test
	public void testAttemptLoginDisabledAccount() {
		expect(
				authenticationManager
						.authenticate(anyObject(UsernamePasswordAuthenticationToken.class)))
				.andThrow(new DisabledException("Account disabled"));
		replay(authenticationManager);

		loginView.clearForm();
		loginView.showAccountDisabled();
		replay(loginView);

		replay(i18n);

		presenter.attemptLogin("joecool", "password");
	}

}
