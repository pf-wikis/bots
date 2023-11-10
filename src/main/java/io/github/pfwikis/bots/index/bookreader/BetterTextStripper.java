package io.github.pfwikis.bots.index.bookreader;

import java.io.IOException;
import java.util.IdentityHashMap;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

public class BetterTextStripper extends PDFTextStripper {
	
	private IdentityHashMap<PDFont, PDFont> wrapperCache = new IdentityHashMap<>();

	@Override
	protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, Vector displacement) throws IOException {
		
		if(font instanceof PDType1CFont pd1cFont) {
			var wFont = wrapperCache.get(font);
			if(wFont == null) {
				wFont = new WrappedPDType1CFont(font.getCOSObject());
				wrapperCache.put(font, wFont);
			}
			font = wFont;
		}
		super.showGlyph(textRenderingMatrix, font, code, displacement);
	}
	
	// copied from parent class except the handling of non-contained symbols
	private static class WrappedPDType1CFont extends PDType1CFont {

		public WrappedPDType1CFont(COSDictionary fontDictionary) throws IOException {
			super(fontDictionary);
		}
		
		@Override
		public String toUnicode(int code, GlyphList customGlyphList) {
			GlyphList unicodeGlyphList;
			if (this.glyphList == GlyphList.getAdobeGlyphList()) {
				unicodeGlyphList = customGlyphList;
			} else {
				unicodeGlyphList = this.glyphList;
			}

			String unicode = basicToUnicode(code);
			if (unicode != null) {
				return unicode;
			}

			String name = null;
			if (encoding != null) {
				name = encoding.getName(code);
				unicode = unicodeGlyphList.toUnicode(name);
				if (unicode != null) {
					return unicode;
				}
			}

			if (name != null) {
				return name.replaceAll("\\P{IsAlphabetic}", "");
			} else
				return name;
		}

		private String basicToUnicode(int code) {
			if (getToUnicodeCMap() != null) {
				if (getToUnicodeCMap().getName() != null && getToUnicodeCMap().getName().startsWith("Identity-")
						&& (dict.getCOSName(COSName.TO_UNICODE) != null || !getToUnicodeCMap().hasUnicodeMappings())) {
					return new String(new char[] { (char) code });
				} else {
					if (code < 256) {
						COSName encoding = dict.getCOSName(COSName.ENCODING);
						if (encoding != null && !encoding.getName().startsWith("Identity")) {
							return getToUnicodeCMap().toUnicode(code, 1);
						}
					}
					return getToUnicodeCMap().toUnicode(code);
				}
			}
			return null;
		}
	}

}
