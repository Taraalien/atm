package com.software.atm.common.enums;

//حساب های سپرده سرمایه گذاری مدت دار

public enum InvestmentDeposits {

    LONG_TERM_DEPOSIT("سپرده بلند مدت"),
    SHORT_TERM_DEPOSIT("سپرده کوتاه مدت"),
    NULL(" ");


    private String persionName;
    InvestmentDeposits (String persionName){
        this.persionName=persionName;
    }
}
