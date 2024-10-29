/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit.pkg252;

/**
 *
 * @author wedalotibi
 */
public class ApplePayPayment implements PaymentStrategy {

    private String appleAccount;

    public ApplePayPayment(String appleAccount) {
        this.appleAccount = appleAccount;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Apple Pay payment of $" + amount + " for account " + appleAccount);
    }
}
