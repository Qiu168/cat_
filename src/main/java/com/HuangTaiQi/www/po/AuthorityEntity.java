package com.HuangTaiQi.www.po;

/**
 * @author 14629
 */
public class AuthorityEntity {
    private Integer id;
    private Integer isCharge;
    private Integer isPark;
    public static final int CHARGE_PARK = 1;
    public static final int CHARGE = 2;
    public static final int PARK = 3;
    public static final int CANT_DO_EVERYTHING = 4;
}
