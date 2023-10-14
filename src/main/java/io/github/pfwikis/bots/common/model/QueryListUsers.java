package io.github.pfwikis.bots.common.model;

public record QueryListUsers(
	User[] users
) {
	public static record User(
		String name,
		Integer userId,
		Boolean missing
	) {}
}
