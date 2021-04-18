/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.SYST17796_ProjectStarterCode.view;

import java.util.Scanner;

/**
 *
 * @author Moeez April 18, 2021
 * @author Jinling Cai April 17, 2021
 */
public class UserView {
    public void display(String message){
        System.out.println(message);
    }
    public String userSelection(String prompt){
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        String userResponse = input.nextLine();
        return userResponse;
    }
}
