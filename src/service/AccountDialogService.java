/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.PermGroupDAO;
import entity.PermGroup;
import java.util.ArrayList;
import entity.Account;
import helper.BCrypt;
import javax.swing.JOptionPane;
import zentech.application.dialog.AccountDialog;

/**
 *
 * @author Duc Pham Ngoc
 */
public class AccountDialogService {
    private ArrayList<PermGroup> listPg = PermGroupDAO.getInstance().selectAll();
    private ArrayList<Account> listAc = AccountDAO.getInstance().selectAll();
    AccountDialog acd = new AccountDialog();
    
    
    
}
