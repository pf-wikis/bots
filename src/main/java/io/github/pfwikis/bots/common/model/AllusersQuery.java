package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Datapublic class AllusersQuery {
	private List<WUser> allusers = new ArrayList<>();
	
	@Data
	public static class WUser {
		private int userid;
		private String name;
	}
}