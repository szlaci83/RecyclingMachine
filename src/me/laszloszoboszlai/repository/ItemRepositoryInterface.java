package me.laszloszoboszlai.repository;

import java.io.IOException;
import java.util.Map;

/**
 * Interface implemented by the item repository classes in the system.
 * @author Laszlo Szoboszlai
 */
public interface ItemRepositoryInterface {
    public void setItems(Map items) throws IOException;
    public Map getItems();
}
