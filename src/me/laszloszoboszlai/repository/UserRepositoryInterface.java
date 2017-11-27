package me.laszloszoboszlai.repository;


/**
 * Interface implemented by the user repository classes in the system.
 * @author Laszlo Szoboszlai
 */
public interface UserRepositoryInterface {
   /**
    * Returns the password for the given user
    * @param userName the name of the user we want the password for
    * @return the password of the user
    */
   String  getUserByName(String userName);
}
