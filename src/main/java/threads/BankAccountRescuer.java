/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author Walker
 */
public class BankAccountRescuer extends BankAccountUser {
    
    BankAccountRescuer(String name, BankAccount account, BankAccountUser[] users) {
        super(name, account, null);
        this.users = users;
    }
    
    public void run() {
        while (allFinished() == false) {
            if (allWaiting() == true) {
                getAccount().deposit(100, this);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    private boolean allFinished() {
        boolean isFinished = false;
        for (BankAccountUser user : users) {
            if (user.getTransactionsRemaining() == 0) {
                isFinished = true;
            }
        }
        return isFinished;
    }
    
    private boolean allWaiting() {
        boolean isWaiting = false;
        for (BankAccountUser user : users) {
            if (allFinished() == false && user.waiting == true) {
                isWaiting = true;
            }
        }
        return isWaiting;
    }
    //private String name = "Parent";
    private BankAccountUser users[];
    
}
