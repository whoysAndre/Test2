
package utils;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHash {
   public static String hashPassword(String plainText){
      return BCrypt.hashpw(plainText, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String plainText, String hashedPassword){
      return BCrypt.checkpw(plainText, hashedPassword);
    } 
}
