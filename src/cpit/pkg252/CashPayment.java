/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit.pkg252;

/**
 *
 * @author wedalotibi
 */
public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
