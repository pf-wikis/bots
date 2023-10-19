package io.github.pfwikis.bots.common.model;

import java.time.Instant;

import lombok.Data;

public record RecentChanges(
		RecentChange[] recentchanges
) {
	
	@Data
	public static class RecentChange {
		private String type;
        private int ns;
        private String title;
        private long pageid;
        private long revid;
        private long old_revid;
        private long rcid;
        private Instant timestamp;
        private String user;
        private long oldlen;
        private long newlen;
	}
}
