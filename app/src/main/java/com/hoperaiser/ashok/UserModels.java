package com.hoperaiser.ashok;


public class UserModels  {


    String Material_No ,Description,Mfr_Part_Nr,Storage_Bin,Stock_Avlbl;
    public UserModels(){

    }

    public String getMaterial_No() {
        return Material_No;
    }

    public void setId(String Material_No) {
        this.Material_No = Material_No;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getMfr_Part_Nr() {
        return Mfr_Part_Nr;
    }

    public void setMfr_Part_Nr(String Mfr_Part_Nr) {
        this.Mfr_Part_Nr = Mfr_Part_Nr;
    }

    public String getStorage_Bin() {
        return Storage_Bin;
    }

    public void setStorage_Bin(String Storage_Bin) {
        this.Storage_Bin = Storage_Bin;
    }

    public String getStock_Avlbl() {
        return Stock_Avlbl;
    }

    public void setStock_Avlbl(String Stock_Avlbl) {
        this.Stock_Avlbl = Stock_Avlbl;
    }

    public UserModels(String Material_No, String Description, String Mfr_Part_Nr,String Storage_Bin, String Stock_Avlbl ) {
        this.Material_No = Material_No;
        this.Description = Description;
        this.Mfr_Part_Nr = Mfr_Part_Nr;
        this.Storage_Bin= Storage_Bin;
        this.Stock_Avlbl = Stock_Avlbl;
    }
}
