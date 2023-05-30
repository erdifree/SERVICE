package com.example.demo.Service;
import com.example.demo.exeptionManagment.ApiRequestExeption;
import com.example.demo.exeptionManagment.ExceptionErrorEnum;
import org.springframework.stereotype.Service;
@Service
public class CodiceFiscaleService {

        /**
         * Normalizes a CF by removing white spaces and converting to upper-case.
         * Useful to clean-up user's input and to save the result in the DB.
         * @param cf Raw CF, possibly with spaces.
         * @return Normalized CF.
         */
        static String normalize(String cf)
        {
            cf = cf.replaceAll("[ \t\r\n]", "");
            cf = cf.toUpperCase();
            return cf;
        }

        /**
         * Returns the formatted CF. Currently does nothing but normalization.
         * @param cf Raw CF, possibly with spaces.
         * @return Formatted CF.
         */
        static String format(String cf)
        {
            return normalize(cf);
        }

        /**
         * Validates a regular CF.
         * @param cf Normalized, 16 characters CF.
         * @return Null if valid, or string describing why this CF must be rejected.
         */
        private static boolean validate_regular(String cf)
        {
            boolean flag = false;
            if( !(cf.matches("^[0-9A-Z]{16}$") )) {
                throw new ApiRequestExeption(ExceptionErrorEnum.CARATTERI_NON_AMMESSI_CF.getMessage());
            }
            int s = 0;
            String even_map = "BAFHJNPRTVCESULDGIMOQKWZYX";
            for(int i = 0; i < 15; i++){
                int c = cf.charAt(i);
                int n;
                if( '0' <= c && c <= '9' )
                    n = c - '0';
                else
                    n = c - 'A';
                if( (i & 1) == 0 )
                    n = even_map.charAt(n) - 'A';
                s += n;
            }
            if( s%26 + 'A' != cf.charAt(15) ){
                throw new ApiRequestExeption(ExceptionErrorEnum.CF_NON_VALIDO.getMessage());
            }
            return flag=true;
        }

        /**
         * Validates a temporary CF.
         * @param cf Normalized, 11 characters CF.
         * @return Null if valid, or string describing why this CF must be rejected.
         */
        private static boolean validazione_PIVA(String cf)
        {
            boolean flag=false;
            if( ! cf.matches("^[0-9]{11}$") )
                return flag;
            int s = 0;
            for(int i = 0; i < 11; i++){
                int n = cf.charAt(i) - '0';
                if( (i & 1) == 1 ){
                    n *= 2;
                    if( n > 9 )
                        n -= 9;
                }
                s += n;
            }
            if( s % 10 != 0 )
                return flag;
            return flag==true;
        }

        /**
         * Verifies the basic syntax, length and control code of the given CF.
         * @param cf Raw CF, possibly with spaces.
         * @return Null if valid, or string describing why this CF must be rejected.
         */
        static boolean validate(String cf)
        {
            boolean flag=false;
            cf = normalize(cf);
            if( cf.length() == 0 )
                return flag;
            else if(!(cf.length() == 16))
                throw new ApiRequestExeption(ExceptionErrorEnum.CF_NON_VALIDO_LUNGHEZZA.getMessage());
            else if (cf.length() == 16)
                return validate_regular(cf);

            else if( cf.length() == 11 )
                return validazione_PIVA(cf);
            else
                return flag;
        }
}
