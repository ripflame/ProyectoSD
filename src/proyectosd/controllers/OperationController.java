/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd.controllers;

import proyectosd.models.User;

/**
 *
 * @author bzeheatnix
 */
public class OperationController {

    User user = new User();    
    
    public OperationController() {
    }

    public void beginTransaction() {
        UserController userController = new UserController("127.0.0.1", 1234);
        
    }

    public void commitTransaction() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public void deposit(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void withdraw(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void consult(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
