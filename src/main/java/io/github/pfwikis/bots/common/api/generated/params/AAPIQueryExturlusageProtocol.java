package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Protocol of the URL. If empty and <var>euquery</var> is set, the protocol is <kbd>http</kbd> and <kbd>https</kbd>. Leave both this and <var>euquery</var> empty to list all external links.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryExturlusageProtocol {
	EMPTY(""),

	BITCOIN("bitcoin"),

	FTP("ftp"),

	FTPS("ftps"),

	GEO("geo"),

	GIT("git"),

	GOPHER("gopher"),

	HTTP("http"),

	HTTPS("https"),

	IRC("irc"),

	IRCS("ircs"),

	MAGNET("magnet"),

	MAILTO("mailto"),

	MATRIX("matrix"),

	MMS("mms"),

	NEWS("news"),

	NNTP("nntp"),

	REDIS("redis"),

	SFTP("sftp"),

	SIP("sip"),

	SIPS("sips"),

	SMS("sms"),

	SSH("ssh"),

	SVN("svn"),

	TEL("tel"),

	TELNET("telnet"),

	URN("urn"),

	WORLDWIND("worldwind"),

	XMPP("xmpp");

	private final String jsonValue;
}
