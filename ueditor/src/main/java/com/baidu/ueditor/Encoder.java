package com.baidu.ueditor;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encoder {

	private static final Logger LOG = LoggerFactory.getLogger(Encoder.class);

	public static String toUnicode ( String input ) {

		StringBuilder builder = new StringBuilder();
		char[] chars = input.toCharArray();

		for ( char ch : chars ) {

			if ( ch < 256 ) {
				builder.append( ch );
			} else {
				builder.append( "\\u" +  Integer.toHexString( ch& 0xffff ) );
			}

		}

		return builder.toString();

	}

	/**
	 * 加密
	 * @param input 明文
	 * @return
	 */
	public static String base64Encoder( String input ) {
		return new String(Base64.encodeBase64(input.getBytes()));
	}

	/**
	 * 解密
	 * @param input 秘文
	 * @return
	 */
	public static String base64Decoder( String input ) {
		return new String(Base64.decodeBase64(input.getBytes()));
	}

	/**
	 * 加密
	 * @param input 明文
	 * @param charset 编码
	 * @return
	 */
	public static String base64Encoder( String input , String charset ) {
		String ret = null;
		try {
			ret = new String(Base64.encodeBase64(input.getBytes(charset)));
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Encoder.base64Encoder", e);
		}
		return ret;
	}

	/**
	 * 解密
	 * @param input 秘文
	 * @param charset 编码
	 * @return
	 */
	public static String base64Decoder( String input , String charset ) {
		String ret = null;
		try {
			ret = new String(Base64.decodeBase64(input.getBytes(charset)));
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Encoder.base64Decoder", e);
		}
		return ret;
	}

}
