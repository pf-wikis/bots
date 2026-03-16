package io.github.pfwikis.bots.common.api.generator.api;

import lombok.Data;

@Data
public class GenAPIInterwiki {
	 private String prefix;
     private boolean local;
     private boolean trans;
     private String url;
     private boolean protorel;
}
