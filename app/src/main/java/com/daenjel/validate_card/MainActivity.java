package com.daenjel.validate_card;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * created by daenjel 29/10/18.
 */
public class MainActivity extends AppCompatActivity {

    EditText fF,fL,sF,sL,tF,tL,f4F,f4L; //where the user enters the numbers
    TextView validate; //display text valid or invalid
    Button btnVal;
    String[] val = {"Valid Card Number","Invalid Card Number"};
    AlertDialog.Builder builder;
    String TAG = "INFO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize(); //referencing widgets with their respective id

        //AlertDialog box to capture null user exception
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Credit Card Numbers cannot be empty!!");
        builder.setTitle("Insert Values:");

        //button click to valid the credit number
        btnVal.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                isFirstValid(); //verifies if the first set of digits are valid
                isSecondValid(); //verifies if the second set of digits are valid
                isThirdValid(); //verifies if the third set of digits are valid
                isFourthValid(); //verifies if the fourth set of digits are valid
                //isValid(); //verifies if the all set of digits are valid and displays text VALID
            }
        });
    }

    private void initialize() {
        validate = findViewById(R.id.bool);
        fF = findViewById(R.id.fF);
        fL = findViewById(R.id.fL);
        sF = findViewById(R.id.sF);
        sL = findViewById(R.id.sL);
        tF = findViewById(R.id.tF);
        tL = findViewById(R.id.tL);
        f4F = findViewById(R.id.f4F);
        f4L = findViewById(R.id.f4L);
        btnVal = findViewById(R.id.btnVal);
    }

    public void isFirstValid(){
        String number = fF.getText().toString(); //getting user first 4 digits as a string
        int num ;
        //catching the null user input exception
        String str = "";
        try {
             num = Integer.parseInt(number);  //converting the string input into an integer
             str = Integer.toOctalString(num); // calling method toOctalString() converting a decimal number to octal-decimal
        }catch (NumberFormatException e){
            Log.i(TAG,""+e);
        }
        Log.i(TAG,"Decimal to Octadecimal: "+str);

        String strArray[] = str.split(""); //converting the octal values into a string array

        int mArray[]=new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            try {
                mArray[i] = Integer.parseInt(strArray[i]); //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(mArray[i]);
        }
        int sum =0;
        for( int num1  : mArray) {  //loop for the summation of the octal values
            sum = sum+ num1;
        }
        Log.i(TAG,""+Integer.toOctalString(sum)); //receiving the first summation as an octal string

        /**
         * if the sum contains multiples digits
         * we perform another summation until we get a single digit sum
         */
        String left = Integer.toOctalString(sum);  //initializing the sum remainder to string

        String remain[] = left.split(""); //converting the sum remainder to string array
        int[] array = new int[remain.length];
        for(int i=0;i<remain.length;i++){
            try {
                array[i] = Integer.parseInt(remain[i]);  //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(array[i]);
        }
        int rem =0;
        for( int num2 : array) { //loop for the summation of the sum remainder octal values
            rem = rem+num2;
        }
        Log.i(TAG,""+Integer.toOctalString(rem));//receiving the first summation as an octal string

        String FstLstRem = Integer.toOctalString(rem); //initializing the sum result to a string
        int latest = Integer.parseInt(FstLstRem); //converting the sum result to an integer to validate if the user last digit is valid

        String numberL = fL.getText().toString();//getting user fifth digit from the first set of digits as a string
        int lastDigit = 0;
        //catching the null user input exception
        try{
            lastDigit = Integer.parseInt(numberL); //convert user input to integer
            if(lastDigit == latest){  //comparing if the user fifth input and the resultant fifth digit match
                //if they match?
                validate.setText(val[0]);//set text valid number from the string array
                fF.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                fL.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                validate.setTextColor(Color.GREEN);//set text color green
            }else{
                //if they do not match?
                validate.setText(val[1]);//set text invalid number from the string array
                validate.setTextColor(Color.RED);// set text color red
                fF.setBackgroundResource(R.drawable.my_invalid_border); //set editText border red to represent invalid number
                fL.setBackgroundResource(R.drawable.my_invalid_border);//set editText border red to represent invalid number
            }
        }
        catch (NumberFormatException e){
            builder.show();//catching the exception of null input
            Log.i(TAG,""+e);}

    }
    public void isSecondValid(){
        String number = sF.getText().toString(); //getting user second 4 digits as a string
        int num ;
        //catching the null user input exception
        String str = "";
        try {
            num = Integer.parseInt(number);  //converting the string input into an integer
            str = Integer.toOctalString(num); // calling method toOctalString() converting a decimal number to octal-decimal
        }catch (NumberFormatException e){
            Log.i(TAG,""+e);
        }
        Log.i(TAG,"Decimal to Octadecimal: "+str);

        String strArray[] = str.split(""); //converting the octal values into a string array

        int mArray[]=new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            try {
                mArray[i] = Integer.parseInt(strArray[i]); //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(mArray[i]);
        }
        int sum =0;
        for( int num1  : mArray) {  //loop for the summation of the octal values
            sum = sum+ num1;
        }
        Log.i(TAG,""+Integer.toOctalString(sum)); //receiving the first summation as an octal string

        /**
         * if the sum contains multiples digits
         * we perform another summation until we get a single digit sum
         */
        String left = Integer.toOctalString(sum);  //initializing the sum remainder to string

        String remain[] = left.split(""); //converting the sum remainder to string array
        int[] array = new int[remain.length];
        for(int i=0;i<remain.length;i++){
            try {
                array[i] = Integer.parseInt(remain[i]);  //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(array[i]);
        }
        int rem =0;
        for( int num2 : array) { //loop for the summation of the sum remainder octal values
            rem = rem+num2;
        }
        Log.i(TAG,""+Integer.toOctalString(rem));//receiving the first summation as an octal string

        String FstLstRem = Integer.toOctalString(rem); //initializing the sum result to a string
        int latest = Integer.parseInt(FstLstRem); //converting the sum result to an integer to validate if the user last digit is valid

        String numberL = sL.getText().toString();//getting user fifth digit from the first set of digits as a string
        int lastDigit = 0;
        //catching the null user input exception
        try{
            lastDigit = Integer.parseInt(numberL); //convert user input to integer
            if(lastDigit == latest){  //comparing if the user fifth input and the resultant fifth digit match
                //if they match?
                validate.setText(val[0]);//set text valid number from the string array
                sF.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                sL.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                validate.setTextColor(Color.GREEN);//set text color green
            }else{
                //if they do not match?
                validate.setText(val[1]);//set text invalid number from the string array
                validate.setTextColor(Color.RED);// set text color red
                sF.setBackgroundResource(R.drawable.my_invalid_border); //set editText border red to represent invalid number
                sL.setBackgroundResource(R.drawable.my_invalid_border);//set editText border red to represent invalid number
            }
        }
        catch (NumberFormatException e){
            builder.show();//catching the exception of null input
            Log.i(TAG,""+e);}

    }
    public void isThirdValid(){
        String number = tF.getText().toString(); //getting user third 4 digits as a string
        int num ;
        //catching the null user input exception
        String str = "";
        try {
            num = Integer.parseInt(number);  //converting the string input into an integer
            str = Integer.toOctalString(num); // calling method toOctalString() converting a decimal number to octal-decimal
        }catch (NumberFormatException e){
            Log.i(TAG,""+e);
        }
        Log.i(TAG,"Decimal to Octadecimal: "+str);

        String strArray[] = str.split(""); //converting the octal values into a string array

        int mArray[]=new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            try {
                mArray[i] = Integer.parseInt(strArray[i]); //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(mArray[i]);
        }
        int sum =0;
        for( int num1  : mArray) {  //loop for the summation of the octal values
            sum = sum+ num1;
        }
        Log.i(TAG,""+Integer.toOctalString(sum)); //receiving the first summation as an octal string

        /**
         * if the sum contains multiples digits
         * we perform another summation until we get a single digit sum
         */
        String left = Integer.toOctalString(sum);  //initializing the sum remainder to string

        String remain[] = left.split(""); //converting the sum remainder to string array
        int[] array = new int[remain.length];
        for(int i=0;i<remain.length;i++){
            try {
                array[i] = Integer.parseInt(remain[i]);  //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(array[i]);
        }
        int rem =0;
        for( int num2 : array) { //loop for the summation of the sum remainder octal values
            rem = rem+num2;
        }
        Log.i(TAG,""+Integer.toOctalString(rem));//receiving the first summation as an octal string

        String FstLstRem = Integer.toOctalString(rem); //initializing the sum result to a string
        int latest = Integer.parseInt(FstLstRem); //converting the sum result to an integer to validate if the user last digit is valid

        String numberL = tL.getText().toString();//getting user fifth digit from the first set of digits as a string
        int lastDigit = 0;
        //catching the null user input exception
        try{
            lastDigit = Integer.parseInt(numberL); //convert user input to integer
            if(lastDigit == latest){  //comparing if the user fifth input and the resultant fifth digit match
                //if they match?
                validate.setText(val[0]);//set text valid number from the string array
                tF.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                tL.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                validate.setTextColor(Color.GREEN);//set text color green
            }else{
                //if they do not match?
                validate.setText(val[1]);//set text invalid number from the string array
                validate.setTextColor(Color.RED);// set text color red
                tF.setBackgroundResource(R.drawable.my_invalid_border); //set editText border red to represent invalid number
                tL.setBackgroundResource(R.drawable.my_invalid_border);//set editText border red to represent invalid number
            }
        }
        catch (NumberFormatException e){
            builder.show();//catching the exception of null input
            Log.i(TAG,""+e);}

    }
    public void isFourthValid(){
        String number = f4F.getText().toString(); //getting user fourth 4 digits as a string
        int num ;
        //catching the null user input exception
        String str = "";
        try {
            num = Integer.parseInt(number);  //converting the string input into an integer
            str = Integer.toOctalString(num); // calling method toOctalString() converting a decimal number to octal-decimal
        }catch (NumberFormatException e){
            Log.i(TAG,""+e);
        }
        Log.i(TAG,"Decimal to Octadecimal: "+str);

        String strArray[] = str.split(""); //converting the octal values into a string array

        int mArray[]=new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            try {
                mArray[i] = Integer.parseInt(strArray[i]); //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(mArray[i]);
        }
        int sum =0;
        for( int num1  : mArray) {  //loop for the summation of the octal values
            sum = sum+ num1;
        }
        Log.i(TAG,""+Integer.toOctalString(sum)); //receiving the first summation as an octal string

        /**
         * if the sum contains multiples digits
         * we perform another summation until we get a single digit sum
         */
        String left = Integer.toOctalString(sum);  //initializing the sum remainder to string

        String remain[] = left.split(""); //converting the sum remainder to string array
        int[] array = new int[remain.length];
        for(int i=0;i<remain.length;i++){
            try {
                array[i] = Integer.parseInt(remain[i]);  //convert the string array into an integer array for computation
            }catch(NumberFormatException e){
                Log.i(TAG,""+e);
            }
            System.out.println(array[i]);
        }
        int rem =0;
        for( int num2 : array) { //loop for the summation of the sum remainder octal values
            rem = rem+num2;
        }
        Log.i(TAG,""+Integer.toOctalString(rem));//receiving the first summation as an octal string

        String FstLstRem = Integer.toOctalString(rem); //initializing the sum result to a string
        int latest = Integer.parseInt(FstLstRem); //converting the sum result to an integer to validate if the user last digit is valid

        String numberL = f4L.getText().toString();//getting user fifth digit from the first set of digits as a string
        int lastDigit = 0;
        //catching the null user input exception
        try{
            lastDigit = Integer.parseInt(numberL); //convert user input to integer
            if(lastDigit == latest){  //comparing if the user fifth input and the resultant fifth digit match
                //if they match?
                validate.setText(val[0]);//set text valid number from the string array
                f4F.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                f4L.setBackgroundResource(R.drawable.my_border);//set editText border default to represent valid number
                validate.setTextColor(Color.GREEN);//set text color green
            }else{
                //if they do not match?
                validate.setText(val[1]);//set text invalid number from the string array
                validate.setTextColor(Color.RED);// set text color red
                f4F.setBackgroundResource(R.drawable.my_invalid_border); //set editText border red to represent invalid number
                f4L.setBackgroundResource(R.drawable.my_invalid_border);//set editText border red to represent invalid number
            }
        }
        catch (NumberFormatException e){
            builder.show();//catching the exception of null input
            Log.i(TAG,""+e);}

    }


    public void isValid(){
        boolean flag = false;
        int draw = R.drawable.my_border;
        String lay = String.valueOf(draw);
        if ((fF.getBackground().equals(lay))){
            System.out.println("First Leo");
            //return flag;

        }else  if (sF.equals((R.drawable.my_border)) && sL.equals(R.drawable.my_border)){
            Log.i(TAG,"yeah kesho");
           //return flag;

        }
        //return flag;
    }
}
