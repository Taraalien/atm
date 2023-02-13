package com.software.atm.common.enums;

//حساب های قرض الحسنه
public enum LoanAccount {

    LOAN_ACCOUNT_SAVING("حساب قرض الحسنه پس انداز"),
    LOAN_ACCOUNT_CURRENT("حساب قرض الحسنه پس انداز جاری"),
    NULL(" ");

    private String persionName;
    LoanAccount (String persionName){
        this.persionName=persionName;
    }
}
