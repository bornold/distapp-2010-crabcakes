/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.Model;
import java.util.List;

/**
 *
 * @author johanssb
 */
public interface IModelHandler {
    public List<Model> getAllModels();
    public void saveModel(Model m);
}
