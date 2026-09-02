package io.github.pfwikis.bots.paizoretriever;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTAccessToken {
	private String applicationKey;
	private String token;
	private String refreshToken;
	private long refreshTokenTTL;
}