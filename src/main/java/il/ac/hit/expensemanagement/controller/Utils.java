package il.ac.hit.expensemanagement.controller;

import com.sun.istack.internal.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Utils
 */
public class Utils {
    /**
     * Error MSG KEY
     */

    static public String file = "/view/user/";

    public static final String ERR_MSG_KEY = "errMsg";

    public static void ifHaveUser(HttpServletRequest request, HttpServletResponse response){
         if(request.getSession().getAttribute("user") == null) {
             try {
                 request.getRequestDispatcher(Utils.file+"login.jsp").forward(request,response);
             } catch (ServletException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }


    /**
     * response With Error
     * @param req the servlet request object
     * @param resp  the servlet response object
     * @param errMsg string
     */
    public static void responseWithError(HttpServletRequest req, HttpServletResponse resp, String errMsg) {
        req.getSession().setAttribute(ERR_MSG_KEY, errMsg);
        try {
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * check that there is NO user logged in, otherwise forward the pag einto the index.
     * @param request the servlet request object
     * @param response the servlet response object
     */
    public static void validateLoggedOutOrForwardToIndex(HttpServletRequest request, HttpServletResponse response) {
        Object temp = request.getSession().getAttribute("CURRENT_USER_NAME");
        if (temp instanceof String) {
            if (!((String) temp).isEmpty())
                forwardToIndex(request, response);
       }
    }

    /**
     * Validates that there is an actual user taht is logged in, otherwise forward this request to the index page.
     * @param request the servlet request object
     * @param response the servlet response object
     * @return the ser name, otherwise null
     */
    @Nullable
    public static String getLoggedUserNameOrForwardToIndex(HttpServletRequest request, HttpServletResponse response) {
        Object temp = request.getSession().getAttribute("CURRENT_USER_NAME");
        String userName = "";
        try {
            userName = (temp != null) ? (String) temp : "";
            if (userName.isEmpty()) {
                forwardToIndex(request, response);
            } else {
                return userName;
            }
        } catch (ClassCastException e) {
            forwardToIndex(request, response);
        }
        return null;
    }

    /**
     * Forward the current page to the index.
     * @param request the servlet request object
     * @param response the servlet response object
     */
    private static void forwardToIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            Utils.responseWithError(request, response, "Something went wrong");
        }
    }

}
