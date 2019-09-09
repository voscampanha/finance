package com.finance.websocket;

import static com.finance.websocket.WebSocketConfiguration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.finance.entities.Account;

@Component
@RepositoryEventHandler(Account.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newAccount(Account account) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newAccount", getPath(account));
	}

	@HandleAfterDelete
	public void deleteAccount(Account account) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteAccount", getPath(account));
	}

	@HandleAfterSave
	public void updateAccount(Account account) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateAccount", getPath(account));
	}

	/**
	 * Take an {@link Account} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param account
	 */
	private String getPath(Account account) {
		return this.entityLinks.linkForSingleResource(account.getClass(),
				account.getId()).toUri().getPath();
	}

}