package org.avaje.barftor.server.ingest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class Md5 {

  /**
   * Return the MD5 hash of the underlying sql.
   */
  public static String hash(String content) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return digestToHex(md.digest(content.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      throw new RuntimeException("MD5 hashing failed", e);
    }
  }

  /**
   * Convert the digest into a hex value.
   */
  private static String digestToHex(byte[] digest) {
    StringBuilder sb = new StringBuilder();
    for (byte aDigest : digest) {
      sb.append(Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }

}
