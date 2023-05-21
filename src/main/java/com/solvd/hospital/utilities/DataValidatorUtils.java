package com.solvd.hospital.utilities;


import com.solvd.hospital.exceptions.AgeOutOfRangeException;
import com.solvd.hospital.exceptions.InvalidAddressException;
import com.solvd.hospital.exceptions.InvalidEmailException;
import com.solvd.hospital.exceptions.InvalidPhoneNumberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidatorUtils {

    private static final Logger logger = LogManager.getLogger("DataValidator");

    public static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            logger.error(s+" is not numeric");
            return false;
        }
        return true;
    }

    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }

    public static boolean isValidMenuOption(String s){
        if(isNumeric(s)){
            int opt = Integer.parseInt(s);

            if(opt> 6){
                return false;
            }else{
                return true;
            }

        }
        return false;
    }
    public static boolean isValidAge(String s){

        try{
            if(!isNumeric(s)){
                throw new AgeOutOfRangeException("must be an integer");
            }else{
                int i = Integer.parseInt(s);
                if(i<0){
                    throw new AgeOutOfRangeException("age can not be smaller than 0");
                }else if(i>200){
                    throw new AgeOutOfRangeException("age can not be greater than 200");
                }
            }

        }catch(AgeOutOfRangeException e){
            logger.error("Not valid age because "+e.getMessage());
            return false;
        }

        return true;

    }
    public static boolean isEmailValid(String s){
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regex);

        try{
            if(s==null){
                throw new InvalidEmailException("email can not be null");

            }else{
                Matcher matcher = pattern.matcher(s);
                if(matcher.matches()){
                    return true;
                }else{
                    throw new InvalidEmailException("incorrect format");
                }
            }
        }catch(InvalidEmailException e){
            logger.error("Not valid email because "+e.getMessage());
            return false;
        }


    }
    public static boolean isPhoneValid(String s){
        String regex="^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);

        try{
            if(s==null){
                throw new InvalidPhoneNumberException("phone number can not be null");
            }else{
                Matcher matcher = pattern.matcher(s);
                if(matcher.matches()){
                    return true;
                }else{
                    throw new InvalidPhoneNumberException("invalid format");
                }
            }
        }catch (InvalidPhoneNumberException e){
            logger.error("Not valid phone number because "+e.getMessage());
            return false;
        }

    }

    public static boolean isZIPValid(String s){
        String regex = "\\d{5}(-\\d{4})?";
        Pattern pattern = Pattern.compile(regex);

        try{
            if(s==null){
                throw new InvalidAddressException("ZIP can not be null");
            }else{
                if(isNumeric(s)){
                    Matcher matcher = pattern.matcher(s);
                    if(matcher.matches()){
                        return true;
                    }else{
                        throw new InvalidAddressException("ZIP invalid");
                    }
                }else{
                    throw new InvalidAddressException("ZIP must be numeric");
                }
            }

        }catch(InvalidAddressException ex){
            logger.error("The ZIP code is incorrect, because "+ex.getMessage());
            return false;
        }
    }

}

