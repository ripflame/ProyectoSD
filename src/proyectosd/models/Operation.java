/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosd.models;

import proyectosd.controllers.OperationController;

/**
 *
 * @author bzeheatnix
 */
public class Operation {

    OperationType operation;
    OperationController operationManager = new OperationController();
    
    public Operation(OperationType operation) {
        this.operation = operation;
    }

    public void OperationRequest() {
        switch (operation) {
            case DEPOSIT:
                operationManager.beginTransaction();
                operationManager.deposit(100);
                operationManager.commitTransaction();
                break;

            case WITHDRAW:
                operationManager.beginTransaction();
                operationManager.withdraw(100);
                operationManager.commitTransaction();
                break;

            case CONSULT:
                operationManager.beginTransaction();
                operationManager.consult(100);
                operationManager.commitTransaction();
                break;
        }
    }
}
