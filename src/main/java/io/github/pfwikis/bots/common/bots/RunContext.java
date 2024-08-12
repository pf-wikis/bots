package io.github.pfwikis.bots.common.bots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunContext {

	private String page;
	private Object scatterShard;
	
	public String toInfoText() {
		var sb = new StringBuilder();
		if(page != null) sb.append(" on "+page);
		if(scatterShard != null) sb.append(" with shard "+scatterShard);
		return sb.toString();
	}
}
