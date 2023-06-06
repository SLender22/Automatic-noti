package pojo;

public class Fund {
//    所属地区
    private String area;
//    项目名称
    private String proName;
//    平台公司
    private String company;
//    全周期投放额
    private String fcycle_investment;
//    天数
    private String days;
//    服务费
    private String service_charge;
//    管理费
    private String management_fee;
//    增值税
    private String value_added_tax;
//   城建税
    private String urban_construction_tax;
//    附加税
    private String surcharge;
//    地方税
    private String local_tax;
//    增值税附加
    private String value_added_tax_surcharge;
//    增值税及其附加税
    private String value_added_tax_and_additional_taxes;
//    计划还本投资拨付款
    private String repay_capital;
//    款项合计
    private String sum;

    public Fund(String area, String proName, String company, String fcycle_investment, String days, String service_charge, String management_fee, String value_added_tax, String urban_construction_tax, String surcharge, String local_tax, String value_added_tax_surcharge, String value_added_tax_and_additional_taxes, String repay_capital, String sum, String sum_three) {
        this.area = area;
        this.proName = proName;
        this.company = company;
        this.fcycle_investment = fcycle_investment;
        this.days = days;
        this.service_charge = service_charge;
        this.management_fee = management_fee;
        this.value_added_tax = value_added_tax;
        this.urban_construction_tax = urban_construction_tax;
        this.surcharge = surcharge;
        this.local_tax = local_tax;
        this.value_added_tax_surcharge = value_added_tax_surcharge;
        this.value_added_tax_and_additional_taxes = value_added_tax_and_additional_taxes;
        this.repay_capital = repay_capital;
        this.sum = sum;
        this.sum_three = sum_three;
    }

    public String getSum_three() {
        return sum_three;
    }

    public void setSum_three(String sum_three) {
        this.sum_three = sum_three;
    }

    private String sum_three;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Fund() {
    }

    public Fund(String fcycle_investment, String service_charge, String management_fee, String value_added_tax_and_additional_taxes, String sum) {
        this.fcycle_investment = fcycle_investment;
        this.service_charge = service_charge;
        this.management_fee = management_fee;
        this.value_added_tax_and_additional_taxes = value_added_tax_and_additional_taxes;
        this.sum = sum;
    }

    public Fund(String area, String proName, String company, String fcycle_investment, String days, String service_charge, String management_fee, String value_added_tax, String urban_construction_tax, String surcharge, String local_tax, String value_added_tax_surcharge, String value_added_tax_and_additional_taxes, String repay_capital, String sum) {
        this.area = area;
        this.proName = proName;
        this.company = company;
        this.fcycle_investment = fcycle_investment;
        this.days = days;
        this.service_charge = service_charge;
        this.management_fee = management_fee;
        this.value_added_tax = value_added_tax;
        this.urban_construction_tax = urban_construction_tax;
        this.surcharge = surcharge;
        this.local_tax = local_tax;
        this.value_added_tax_surcharge = value_added_tax_surcharge;
        this.value_added_tax_and_additional_taxes = value_added_tax_and_additional_taxes;
        this.repay_capital = repay_capital;
        this.sum = sum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getFcycle_investment() {
        return fcycle_investment;
    }

    public void setFcycle_investment(String fcycle_investment) {
        this.fcycle_investment = fcycle_investment;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public String getManagement_fee() {
        return management_fee;
    }

    public void setManagement_fee(String management_fee) {
        this.management_fee = management_fee;
    }

    public String getValue_added_tax() {
        return value_added_tax;
    }

    public void setValue_added_tax(String value_added_tax) {
        this.value_added_tax = value_added_tax;
    }

    public String getUrban_construction_tax() {
        return urban_construction_tax;
    }

    public void setUrban_construction_tax(String urban_construction_tax) {
        this.urban_construction_tax = urban_construction_tax;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getLocal_tax() {
        return local_tax;
    }

    public void setLocal_tax(String local_tax) {
        this.local_tax = local_tax;
    }

    public String getValue_added_tax_surcharge() {
        return value_added_tax_surcharge;
    }

    public void setValue_added_tax_surcharge(String value_added_tax_surcharge) {
        this.value_added_tax_surcharge = value_added_tax_surcharge;
    }

    public String getValue_added_tax_and_additional_taxes() {
        return value_added_tax_and_additional_taxes;
    }

    public void setValue_added_tax_and_additional_taxes(String value_added_tax_and_additional_taxes) {
        this.value_added_tax_and_additional_taxes = value_added_tax_and_additional_taxes;
    }

    public String getRepay_capital() {
        return repay_capital;
    }

    public void setRepay_capital(String repay_capital) {
        this.repay_capital = repay_capital;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
