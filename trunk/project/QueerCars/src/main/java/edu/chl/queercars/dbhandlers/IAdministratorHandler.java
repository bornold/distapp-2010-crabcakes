/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Administrator;
import java.util.List;

/**
 *
 * @author mviktor
 */
public interface IAdministratorHandler {
    public List<Administrator> getAllAdministrators();
    public void removeAdministrator(String id);
    public void saveAdministrator(Administrator a);
    public Administrator getAdministrator(String id);
}
